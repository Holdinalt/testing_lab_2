package Trigonometric;

public class Sec implements TrigExecutable{

    private final TrigExecutable cos;

    public Sec(TrigExecutable _cos) {
        cos = _cos;
    }

    @Override
    public double execute(double digit) {
        return 1 / cos.execute(digit);
    }
}
