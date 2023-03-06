package lab2.mathematics.trigonometric;

public class Sec extends TrigExecutable{

    private final TrigExecutable cos;

    public Sec(TrigExecutable _cos) {
        cos = _cos;
    }

    @Override
    public double execute(double digit) {

        double out = 1 / cos.execute(digit);

        tryWriteToFile(new String[] {String.valueOf(digit), String.valueOf(out), this.getClass().getSimpleName()});

        return out;
    }
}
