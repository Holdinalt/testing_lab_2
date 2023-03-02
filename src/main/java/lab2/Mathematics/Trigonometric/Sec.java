package lab2.Mathematics.Trigonometric;

import lab2.Mathematics.Writable;

public class Sec extends Writable implements TrigExecutable{

    private final TrigExecutable cos;

    public Sec(TrigExecutable _cos) {
        cos = _cos;
    }

    @Override
    public double execute(double digit) {

        double out = 1 / cos.execute(digit);

        tryWriteToFile(new String[] {String.valueOf(digit), String.valueOf(out), this.getClass().getName()});

        return out;
    }
}
