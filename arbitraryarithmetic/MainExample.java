package arbitraryarithmetic;

public class MainExample {
    public static void main(String[] args) {
        Ainteger num1 = new Ainteger("25000000");
        Ainteger num2 = new Ainteger("2");

        // Addition
        Ainteger sum = num1.add(num2);
        System.out.println("Sum: " + sum);

        // // Subtraction
        // Ainteger diff = num1.subtract(num2);
        // System.out.println("Difference: " + diff);

        // // Multiplication
        // Ainteger product = num1.multiply(num2);
        // System.out.println("Product: " + product);

        // // Division
        Ainteger quotient = num1.div(num2);
        System.out.println("Quotient: " + quotient);
    }
}
