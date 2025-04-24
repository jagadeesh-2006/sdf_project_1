package arbitraryarithmetic;  // This assumes Arithmetic is a class or package in your project.

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

    public void copy(Afloat num){
        this.val = num.val;
        this.isNegative = num.isNegative;
    }

    public static Afloat parse(String s){
        return new Afloat(s);
    }
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

    public Afloat add(Afloat other) {
        this.normalize();
        other.normalize();
        String s1 = this.val;
        String s2 = other.val;
      
        // Determine number of decimal places
        int decLen1 = s1.contains(".") ? s1.length() - s1.indexOf(".") - 1 : 0;
        int decLen2 = s2.contains(".") ? s2.length() - s2.indexOf(".") - 1 : 0;
        int decLen = Math.max(decLen1, decLen2);
        
        // Pad decimal places with zeros
        if (decLen1 < decLen) s1 += "0".repeat(decLen - decLen1);
        if (decLen2 < decLen) s2 += "0".repeat(decLen - decLen2);
    
        // Remove decimal points for integer-style addition
        String str1 = s1.replace(".", "");
        String str2 = s2.replace(".", "");
        
        // Create Aintegers with sign handled
        Ainteger a1 = new Ainteger(this.isNegative ? "-" + str1 : str1);
        Ainteger a2 = new Ainteger(other.isNegative ? "-" + str2 : str2);
    
        // Add the numbers
        Ainteger result = a1.add(a2);
        String resultStr = result.val;
    
        // Ensure the result has enough digits to place the decimal point
        while (resultStr.length() <= decLen) {
            resultStr = "0" + resultStr;
        }
    
        // Insert the decimal point
        String finalVal = resultStr.substring(0, resultStr.length() - decLen) + "." +
                          resultStr.substring(resultStr.length() - decLen);
    
        // // Remove any trailing ".0" if it's unnecessary (optional)
        // finalVal = finalVal.replaceFirst("\\.?0+$", "");
    
        // Create the Afloat result
        Afloat res = new Afloat(finalVal);
        res.normalize();
    
        // Set sign correctly
        if (result.isNegative && !finalVal.equals("0")) {
            res.isNegative = true;
        }
    
        return res;
    }
    
   
    public Afloat sub(Afloat other) {
        this.normalize();
        other.normalize();
        String s1 = this.val;
        String s2 = other.val;
    
        int decLen1 = s1.contains(".") ? s1.length() - s1.indexOf(".") - 1 : 0;
        int decLen2 = s2.contains(".") ? s2.length() - s2.indexOf(".") - 1 : 0;
        int decLen = Math.max(decLen1, decLen2);
    
        if (decLen1 < decLen) s1 += "0".repeat(decLen - decLen1);
        if (decLen2 < decLen) s2 += "0".repeat(decLen - decLen2);
    
        String str1 = s1.replace(".", "");
        String str2 = s2.replace(".", "");
    
        Ainteger a1 = new Ainteger(this.isNegative? "-" + str1 : str1);
        Ainteger a2 = new Ainteger(other.isNegative? "-" + str2 : str2);
    
        Ainteger result = a1.sub(a2);
        String resultStr = result.val;
        while (resultStr.length() <= decLen) resultStr = "0" + resultStr;
    
        String finalVal = resultStr.substring(0, resultStr.length() - decLen) + "." +
                          resultStr.substring(resultStr.length() - decLen);
        if (finalVal.contains(".")){
            finalVal += ".0";
        }
        Afloat res = new Afloat(finalVal);
        if (result.isNegative && !finalVal.equals("0.0") && !finalVal.replace(".", "").equals("0")) {
            res.isNegative = true;
        }
    
        return res;
    }
    
    

    @Override
    public String toString() {
        return (isNegative && !val.equals("0") ? "-" : "") + val;
    }

    public Afloat mul(Afloat other) {
        String s1 = this.val;
        String s2 = other.val;
    
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
        
        return res;
    }
    
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
    
        // Remove decimal points
        String num1 = s1.replace(".", "");
        String num2 = s2.replace(".", "");
    
        // Calculate decimal places
        int decPlaces1 = s1.length() - decPos1 - (s1.contains(".") ? 1 : 0);
        int decPlaces2 = s2.length() - decPos2 - (s2.contains(".") ? 1 : 0);
    
        // Set precision (number of decimal digits in the result)
        int precision = 1000; 
    
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
    
        // Remove trailing zeros after decimal point
        String finalVal = sb.toString().replaceAll("0+$", "").replaceAll("\\.$", ".0");
    
        // Create result
        Afloat result = new Afloat(finalVal);
        result.isNegative = resultNegative && !finalVal.equals("0.0");
        return result;
    }
    
    
}


