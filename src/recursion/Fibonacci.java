package recursion;

public class Fibonacci {

    public static void main(String[] args) {

        int resultado = fibonacci(6);
        System.out.println(resultado);

    }

    public static int fibonacci(int pos) {

        if (pos ==1)return 1;
        if (pos <=0)return 0;

        return fibonacci(pos-1) + fibonacci(pos-2);
    }
}
