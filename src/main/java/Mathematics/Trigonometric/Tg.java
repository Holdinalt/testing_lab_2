package Mathematics.Trigonometric;

import Mathematics.Writable;

public class Tg extends Writable implements TrigExecutable{

    private final TrigExecutable sin;
    private final TrigExecutable cos;

    public Tg(TrigExecutable _sin, TrigExecutable _cos){
        sin = _sin;
        cos = _cos;
    }

    @Override
    public double execute(double digit) {

        double out = sin.execute(digit) / cos.execute(digit);

        tryWriteToFile(new String[] {String.valueOf(digit), String.valueOf(out), this.getClass().getName()});

        return out;
    }
}
