package lab2.Mathematics.Trigonometric;

import lab2.Mathematics.Writable;

public class Csc extends Writable implements TrigExecutable{

    private final TrigExecutable sin;

    public Csc(TrigExecutable _sin){
        sin = _sin;
    }

    @Override
    public double execute(double digit) {

        double out = 1 / sin.execute(digit);

        tryWriteToFile(new String[] {String.valueOf(digit), String.valueOf(out), this.getClass().getName()});

        return out;
    }
}
