public class die {
    public static int rollDie() {
        return (int) (Math.random() * 6) + 1;
    }

    public static double rollDie(boolean isDouble) {
        return Math.random() * 6 + 1;
    }

    public static void main(String[] args) {
        //expected result should be approaching 3.5 e(x) = Σ x * p(x) = 3.5
        int sum = 0;
        double testCase = 100;
        for (int i = 0; i < testCase; i++) {
            sum += rollDie(true);
        }
        System.out.println(sum / testCase);
    }
}