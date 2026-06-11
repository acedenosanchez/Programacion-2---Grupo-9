package recursion;

public class exclusiveSum {
    public static void main(String[] args) {
        int resultado = askExclusiveSum(4);
        System.out.println(resultado);
    }

    public static int askExclusiveSum(int number) {
        if (number >= 1)return 0;

        return number-1 + askExclusiveSum(number-1);
    }
}
