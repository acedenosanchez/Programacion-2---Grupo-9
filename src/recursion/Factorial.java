package recursion;

public class Factorial {
    public static void main(String[] args) {
        int valor = askFactorial(5);
        System.out.println(valor);
    }


    public static int askFactorial(int number) {
        if (number == 1) {
            return  1;
        }
            return number * askFactorial(number - 1);
    }
}
