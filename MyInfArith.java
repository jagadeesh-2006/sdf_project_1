import arbitraryarithmetic.Afloat;
import arbitraryarithmetic.Ainteger;

public class MyInfArith {
    public static void main(String[] args) {
        if (args.length != 4) {
            System.out.println("Usage: java MyInfArith <int/float> <add/sub/mul/div> <operand1> <operand2>");
            return;
        }

        String type = args[0];
        String operation = args[1];
        String op1 = args[2];
        String op2 = args[3];

        try {
            switch (type.toLowerCase()) {
                case "int":
                    Ainteger int1 = new Ainteger(op1);
                    Ainteger int2 = new Ainteger(op2);
                    Ainteger intResult = null;

                    switch (operation.toLowerCase()) {
                        case "add": intResult = int1.add(int2); break;
                        case "sub": intResult = int1.sub(int2); break;
                        case "mul": intResult = int1.mul(int2); break;
                        case "div": intResult = int1.div(int2); break;
                        default: System.out.println("Invalid operation: " + operation); return;
                    }

                    System.out.println("Result: " + intResult);
                    break;

                case "float":
                    Afloat float1 = new Afloat(op1);
                    Afloat float2 = new Afloat(op2);
                    Afloat floatResult = null;

                    switch (operation.toLowerCase()) {
                        case "add": floatResult = float1.add(float2); break;
                        case "sub": floatResult = float1.sub(float2); break;
                        case "mul": floatResult = float1.mul(float2); break;
                        case "div": floatResult = float1.div(float2); break;
                        default: System.out.println("Invalid operation: " + operation); return;
                    }

                    System.out.println("Result: " + floatResult);
                    break;

                default:
                    System.out.println("Invalid type: " + type + ". Must be 'int' or 'float'");
            }
        } catch (Exception e) {
            System.err.println("Error during calculation: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
