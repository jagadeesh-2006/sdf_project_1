//  this class defines the addition , subtraction , mulitiplication and Division for Big integers 
package arbitraryarithmetic;
public class Ainteger {
    String val;
    boolean isNegative = false;

    public Ainteger() {
        this.val = "0";
    }

    public Ainteger(String s) {
        if (s.startsWith("-")) {
            isNegative = true;
            val = s.substring(1);
        } else {
            val = s;
        }
        normalize();
    }

    public void copy(Ainteger num){
        this.val = num.val;
        this.isNegative = num.isNegative;
    }
    
    public static Ainteger parse(String s) {
        return new Ainteger(s);
    }

    // this removes the leading zeroes in the val
    private void normalize() {
        int start = 0;
        // Strip leading zeros
        while (start < val.length() && val.charAt(start) == '0') {
            start++;
        }
        if (start == val.length()) {
            val = "0";
            isNegative = false; 
        } else {
            val = val.substring(start);
        }
    }
    
    // comparing two Big Integers (if equal -> 0 , this.val > other.val -> 1 , this.val < other.val -> -1)
    public int compareTo(Ainteger other) {
        this.normalize();
        other.normalize();
        String thisVal = this.val;
        String otherVal = other.val;
        // Compare signs
        if (this.isNegative && !other.isNegative) return -1;
        if (!this.isNegative && other.isNegative) return 1;

        boolean bothNegative = this.isNegative;

        // Compare lengths
        if (thisVal.length() > otherVal.length()) return bothNegative ? -1 : 1;
        if (thisVal.length() < otherVal.length()) return bothNegative ? 1 : -1;

        // Compare digit by digit
        for (int i = 0; i < thisVal.length(); i++) {
            char a = thisVal.charAt(i);
            char b = otherVal.charAt(i);
            if (a > b) return bothNegative ? -1 : 1;
            if (a < b) return bothNegative ? 1 : -1;
        }

        return 0;
    }

    // this is for absolute subtraction of two Big Integers
    private static Ainteger abssub(Ainteger a, Ainteger b) {
        // Assumes: a >= b and both are positive numbers (absolute subtraction)
    
        StringBuilder result = new StringBuilder();
        String s1 = a.val;  
        String s2 = b.val;  
    
        // Pad s2 with leading zeros to match the length of s1
        while (s2.length() < s1.length()) {
            s2 = "0" + s2;
        }
    
        int borrow = 0;
    
        // Start subtracting from the last digit (right to left)
        for (int i = s1.length() - 1; i >= 0; i--) {
            int d1 = s1.charAt(i) - '0';        
            int d2 = s2.charAt(i) - '0' + borrow; 
    
            if (d1 < d2) {
                d1 += 10;
                borrow = 1;
            } else {
                borrow = 0;
            }
    
            result.append(d1 - d2);  // Subtract and store the result
        }
    
        // Reverse the result string because we appended digits in reverse order
        return new Ainteger(result.reverse().toString());
    }
    
    // for subtraction of two Big Integers
    public Ainteger sub(Ainteger other) {
        Ainteger result;

        // Case 1: different signs
        if (this.isNegative != other.isNegative) {
            Ainteger temp = this.add(new Ainteger((other.isNegative ? "" : "-") + other.val));
            return temp;
        }

        // Case 2: same signs
        int cmp = this.compareTo(other);
        if (cmp == 0) {
            return new Ainteger("0");
        }

        boolean resultNegative;

        if (cmp > 0) {
            result = abssub(this, other);
            resultNegative = this.isNegative;
        } else {
            result = abssub(other, this);
            resultNegative = !this.isNegative;
        }

        result.isNegative = resultNegative;
        result.normalize();
        return result;
    }

    // for addition of two Big Integers
    public Ainteger add(Ainteger other) {
        // Handle opposite signs via subion
        if (this.isNegative != other.isNegative) {
            if (this.isNegative) {
                return other.sub(new Ainteger(this.val));
            } else {
                return this.sub(new Ainteger(other.val));
            }
        }

        String a = new StringBuilder(this.val).reverse().toString();
        String b = new StringBuilder(other.val).reverse().toString();

        StringBuilder result = new StringBuilder();
        int carry = 0;
        int maxLen = Math.max(a.length(), b.length());

        for (int i = 0; i < maxLen; i++) {
            int digitA = i < a.length() ? a.charAt(i) - '0' : 0;
            int digitB = i < b.length() ? b.charAt(i) - '0' : 0;

            int sum = digitA + digitB + carry;
            result.append(sum % 10);
            carry = sum / 10;
        }

        if (carry > 0) {
            result.append(carry);
        }

        Ainteger res = new Ainteger(result.reverse().toString());
        res.isNegative = this.isNegative;
        res.normalize();
        return res;
    }

    @Override
    public String toString() {
        return (isNegative ? "-" : "") + val;
    }

    // for mutliplication of two Big Integers
    public Ainteger mul(Ainteger other) {
        String num1 = this.val;
        String num2 = other.val;
        int len1 = num1.length();
        int len2 = num2.length();
    
        // Initialize result to zero
        Ainteger result = new Ainteger("0"); 
    
        for (int i = len1 - 1; i >= 0; i--) {
            int carry = 0; // Carry for the current digit multiplication
            StringBuilder partial = new StringBuilder(); // Holds the intermediate partial product
    
            int d1 = num1.charAt(i) - '0';
    
            // Add trailing zeros corresponding to the position of the digit in num1
            for (int k = 0; k < len1 - 1 - i; k++) {
                partial.append('0');
            }
    
            // Multiply the current digit of num1 with all digits of num2
            for (int j = len2 - 1; j >= 0; j--) {
                int d2 = num2.charAt(j) - '0'; 
                int mul = d1 * d2 + carry; // Multiply digits and add carry
                partial.append(mul % 10); 
                carry = mul / 10; // Update carry
            }
    
            // If there's any remaining carry, append it
            if (carry > 0) {
                partial.append(carry);
            }
    
            // Reverse the partial result as we built it backwards
            partial.reverse();
    
            // Convert partial result string to Ainteger and add it to result
            Ainteger partialResult = new Ainteger(partial.toString());
            result = result.add(partialResult); // Accumulate the result
        }
    
        // Set the sign of the result: negative if operands have opposite signs
        // and the result is not zero
        result.isNegative = (this.isNegative != other.isNegative) && !result.val.equals("0");
    
        return result;
    }
    

    // for divison for two Big Integers
    public Ainteger div(Ainteger other) {
        if (other.val.equals("0")) {
            throw new ArithmeticException("Division by zero");
        }
    
        boolean isNegativeResult = this.isNegative != other.isNegative;
    
        Ainteger dividend = this;
        Ainteger divisor = other;
    
        // If dividend is smaller than divisor, return 0
        if (dividend.compareTo(divisor) < 0) {
            return new Ainteger("0");
        }
    
        StringBuilder quotient = new StringBuilder();
        Ainteger remainder = new Ainteger("0");
    
        // go through each digit of the dividend
        for (int i = 0; i < dividend.val.length(); i++) {
            // Bring down the next digit and adjust remainder
            remainder = remainder.mul(new Ainteger("10"));
            remainder = remainder.add(new Ainteger(Character.toString(dividend.val.charAt(i))));
            remainder.normalize();
    
            int count = 0;
            
            // sub the divisor from remainder while it's larger
            while (remainder.compareTo(divisor) >= 0) {
                remainder = remainder.sub(divisor);
                remainder.normalize();
                count++;
            }
    
            // Append the count (the quotient digit) to the result
            quotient.append(count);
        }
    
        // Remove leading zeros if any
        String resultVal = quotient.toString();
    
        Ainteger result = new Ainteger(resultVal);
        // If the result is empty (i.e., quotient is 0), set it to "0"
        if (resultVal.isEmpty()) {
            resultVal = "0";
        }
    
        // Create the result and apply the correct sign
        result.isNegative = isNegativeResult;
    
        return result;
    }
    
    
    
}
    