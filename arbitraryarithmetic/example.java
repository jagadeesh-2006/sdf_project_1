package arbitraryarithmetic;
class example{
    public static void main(String[] args) {
        Afloat a = new Afloat("2.5");
        Afloat b = new Afloat("2");

        Afloat sum = a.add(b);
        // Afloat diff = a.sub(b); 
        // Afloat mul = a.multi(b);
        

        // System.out.println("a       = " + a);
        // System.out.println("b       = " + b);
        System.out.println("a + b   = " + sum);
        // System.out.println("a - b   = " + diff);
        // System.out.println("a * b   = " + mul);
        Afloat div = a.div(b);
        System.out.println("a%b = "+ div);

    }
}