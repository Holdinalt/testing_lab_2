package Mathematics.Trigonometric;

import Mathematics.Writable;

public class Ctg extends Writable implements TrigExecutable{

    private final TrigExecutable sin;
    private final TrigExecutable cos;

    public Ctg(TrigExecutable _sin, TrigExecutable _cos){
        sin = _sin;
        cos = _cos;
    }

    @Override
    public double execute(double digit) {

        double out = cos.execute(digit) / sin.execute(digit);

        tryWriteToFile(new String[] {String.valueOf(digit), String.valueOf(out), this.getClass().getName()});

        return out;
    }
}
