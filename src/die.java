public class die {
    private int intValue;
    private double doubleValue;

    public die() {
        intValue = 0;
        doubleValue = 0;
    }

    public int getIntValue() {
        return intValue;
    }

    public double getDoubleValue() {
        return doubleValue;
    }

    public void rollDie() {
        intValue = (int) (Math.random() * 6) + 1;
    }

    public void rollDie(boolean isDouble) {
        if (isDouble) {
            doubleValue = Math.random() * 6 + 1;
        }
    }

    public static void main(String[] args) {
        //expected result should be approaching 3.5 e(x) = Σ x * p(x) = 3.5
        double sum = 0;
        double testCase = 10000;
        die die = new die();
        for (int i = 0; i < testCase; i++) {
            die.rollDie(true);
            sum += die.getDoubleValue();
        }
        System.out.println(sum / testCase);
    }
}