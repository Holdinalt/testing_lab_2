package lab2.Mathematics.Trigonometric;

import lab2.Mathematics.Writable;

public class Ctg extends TrigExecutable{

    private final TrigExecutable sin;
    private final TrigExecutable cos;

    public Ctg(TrigExecutable _sin, TrigExecutable _cos){
        sin = _sin;
        cos = _cos;
    }

    @Override
    public double execute(double digit) {

        double out = cos.execute(digit) / sin.execute(digit);

        tryWriteToFile(new String[] {String.valueOf(digit), String.valueOf(out), this.getClass().getSimpleName()});

        return out;
    }
}
