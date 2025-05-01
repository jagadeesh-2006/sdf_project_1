//  this class defines the addition , subtraction , mulitiplication and Division for Big integers 

package arbitraryarithmetic;  

public class Afloat {
    String val; 
    boolean isNegative;

    public Afloat(){
        this.val = "0";
    }
    public Afloat(String input) {
        if (input.startsWith("-")) {
            isNegative = true;
            input = input.substring(1);
        } else {
            isNegative = false;
        }
        val = input;
        normalize();
    }
    // copy constructor
    public void copy(Afloat num){
        this.val = num.val;
        this.isNegative = num.isNegative;
    }

    public static Afloat parse(String s){
        return new Afloat(s);
    }

    // removes the leading and trailing zeroes of the value
    private void normalize() {
        int i = 0;
        while(val.endsWith(" ")){
            val = val.substring(0, val.length()-1);
        }
        while (i < val.length() && (val.charAt(i) == '0' || val.charAt(i) == '.')) {
            if (val.charAt(i) == '.') break;
            i++;
        }
        val = val.substring(i);

        
        if (val.startsWith(".")) val = "0" + val;

        // Remove trailing zeros after decimal
        if (val.contains(".")) {
            while (val.endsWith("0")) {
                val = val.substring(0, val.length() - 1);
            }
            if (val.endsWith(".")) {
                val = val.substring(0, val.length() - 1);
            }
        }

        if (val.isEmpty()) val = "0";
    }

    // add the two big numbers
    public Afloat add(Afloat other) {
        this.normalize();
        other.normalize();
    
        String s1 = this.val;
        String s2 = other.val;
    
        // Find how many digits each number has after the decimal point
        int decLen1 = s1.contains(".") ? s1.length() - s1.indexOf(".") - 1 : 0;
        int decLen2 = s2.contains(".") ? s2.length() - s2.indexOf(".") - 1 : 0;
    
        // Get the maximum decimal length so we can align both numbers to it
        int decLen = Math.max(decLen1, decLen2);
    
        // Pad the shorter number with zeros so both have equal decimal digits
        if (decLen1 < decLen) s1 += "0".repeat(decLen - decLen1);
        if (decLen2 < decLen) s2 += "0".repeat(decLen - decLen2);
    
        String str1 = s1.replace(".", "");
        String str2 = s2.replace(".", "");
    
        Ainteger a1 = new Ainteger(this.isNegative ? "-" + str1 : str1);
        Ainteger a2 = new Ainteger(other.isNegative ? "-" + str2 : str2);
    
        Ainteger result = a1.add(a2);
        String resultStr = result.val;
    
        // Pad with leading zeros if the result is shorter than required decimal places
        while (resultStr.length() <= decLen) {
            resultStr = "0" + resultStr;
        }
    
        // insert the decimal point at the correct place
        String finalVal = resultStr.substring(0, resultStr.length() - decLen) + "." +
                          resultStr.substring(resultStr.length() - decLen);
    
        Afloat res = new Afloat(finalVal);
        res.normalize(); 
    
        // Set the sign if the result is negative and not zero
        if (result.isNegative && !finalVal.equals("0")) {
            res.isNegative = true;
        }
    
        res.val = fixTo30DecimalPlaces(res.val);
    
        return res;
    }
    
    
   // for subtraction of two Big Float Numbers
    public Afloat sub(Afloat other) {
        this.normalize();
        other.normalize();
        String s1 = this.val;
        String s2 = other.val;
    
        // Find how many digits each number has after the decimal point
        int decLen1 = s1.contains(".") ? s1.length() - s1.indexOf(".") - 1 : 0;
        int decLen2 = s2.contains(".") ? s2.length() - s2.indexOf(".") - 1 : 0;

        
        // Get the maximum decimal length so we can align both numbers to it
        int decLen = Math.max(decLen1, decLen2);
    
        if (decLen1 < decLen) s1 += "0".repeat(decLen - decLen1);
        if (decLen2 < decLen) s2 += "0".repeat(decLen - decLen2);
    
        String str1 = s1.replace(".", "");
        String str2 = s2.replace(".", "");
    
        Ainteger a1 = new Ainteger(this.isNegative? "-" + str1 : str1);
        Ainteger a2 = new Ainteger(other.isNegative? "-" + str2 : str2);
    
        Ainteger result = a1.sub(a2);
        String resultStr = result.val;

        // Pad with leading zeros if the result is shorter than required decimal places
        while (resultStr.length() <= decLen) resultStr = "0" + resultStr;
    
        String finalVal = resultStr.substring(0, resultStr.length() - decLen) + "." +
                          resultStr.substring(resultStr.length() - decLen);
        if (finalVal.contains(".")){
            finalVal += "0";
        }
        Afloat res = new Afloat(finalVal);
        if (result.isNegative && !finalVal.equals("0.0") && !finalVal.replace(".", "").equals("0")) {
            res.isNegative = true;
        }
        res.val = fixTo30DecimalPlaces(res.val);
    
        return res;
    }
    
    

    @Override
    public String toString() {
        return (isNegative && !val.equals("0") ? "-" : "") + val;
    }

    // for multiplication of two Big Float numbers
    public Afloat mul(Afloat other) {
        String s1 = this.val;
        String s2 = other.val;
    
        // Find how many digits each number has after the decimal point
        int decLen1 = s1.contains(".") ? s1.length() - s1.indexOf(".") - 1 : 0;
        int decLen2 = s2.contains(".") ? s2.length() - s2.indexOf(".") - 1 : 0;
        int totalDecimalPlaces = decLen1 + decLen2;
    
        s1 = s1.replace(".", "");
        s2 = s2.replace(".", "");
    
        Ainteger a1 = new Ainteger(this.isNegative ? "-" + s1 : s1);
        Ainteger a2 = new Ainteger(other.isNegative? "-" + s2 : s2);
    
        Ainteger result = a1.mul(a2);
        String resultStr = result.val;
        while (resultStr.length() <= totalDecimalPlaces) resultStr = "0" + resultStr;
    
        String finalVal = resultStr.substring(0, resultStr.length() - totalDecimalPlaces) + "." +
                          resultStr.substring(resultStr.length() - totalDecimalPlaces);
        if (finalVal.contains(".")){
            finalVal += ".0";
        }
        Afloat res = new Afloat(finalVal);
        if (result.isNegative && !finalVal.equals("0.0") && !finalVal.replace(".", "").equals("0")) {
            res.isNegative = true;
        }
        res.val = fixTo30DecimalPlaces(res.val);
        return res;

    }
    
    // for divison of two Big Float numbers
    public Afloat div(Afloat other) {
        // Check for division by zero
        if (other.val.replace(".", "").equals("0")) {
            throw new ArithmeticException("Division by zero");
        }
    
        // Remove sign for calculation, handle sign at the end
        boolean resultNegative = this.isNegative && other.isNegative;
        String s1 = this.val.replace("-", "");
        String s2 = other.val.replace("-", "");
    
        // Find decimal point positions
        int decPos1 = s1.indexOf('.') == -1 ? s1.length() : s1.indexOf('.');
        int decPos2 = s2.indexOf('.') == -1 ? s2.length() : s2.indexOf('.');
    
        
        String num1 = s1.replace(".", "");
        String num2 = s2.replace(".", "");
    
        // Calculate decimal places
        int decPlaces1 = s1.length() - decPos1 - (s1.contains(".") ? 1 : 0);
        int decPlaces2 = s2.length() - decPos2 - (s2.contains(".") ? 1 : 0);
    
        
        int precision = 30; 
    
        String scaledNum1 = num1 + "0".repeat(precision);
    
        Ainteger a1 = new Ainteger(scaledNum1);
        Ainteger a2 = new Ainteger(num2);
        Ainteger resultInt = a1.div(a2);
    
        String resultStr = resultInt.val;
    
        // Calculate the position to insert the decimal point
        int resultDecimalPlaces = decPlaces1 + precision - decPlaces2;
    
        // Ensure resultStr has enough digits
        while (resultStr.length() <= resultDecimalPlaces) {
            resultStr = "0" + resultStr;
        }
    
        int pointPos = resultStr.length() - resultDecimalPlaces;
        StringBuilder sb = new StringBuilder(resultStr);
        sb.insert(pointPos, ".");
    
        
        String finalVal = sb.toString();
        
        // Create result
        Afloat result = new Afloat(finalVal);
        result.normalize();
        result.isNegative = resultNegative && !finalVal.equals("0.0");

        String s = result.val;
        result.val = fixTo30DecimalPlaces(s);
        return result;
    }

    // for ensuring the result is upto 30 decimal places
    private static String fixTo30DecimalPlaces(String result) {
        if (!result.contains(".")) {
            // If there's no decimal point, add one followed by 30 zeroes
            return result + "." + "000000000000000000000000000000";
        }
    
        int decimalIndex = result.indexOf('.');
        int digitsAfterDecimal = result.length() - decimalIndex - 1;
    
        if (digitsAfterDecimal < 30) {
            StringBuilder sb = new StringBuilder(result);
            for (int i = 0; i < 30 - digitsAfterDecimal; i++) {
                sb.append('0');
            }
            return sb.toString();
        } else if (digitsAfterDecimal > 30) {
            // If more than 30 digits, truncate
            return result.substring(0, decimalIndex + 1 + 30);
        } else {
            // Exactly 30 digits — return as is
            return result;
        }
    }
    
    
    
}


