package lab2.Mathematics.Trigonometric;
import lab2.Mathematics.Writable;

public class Cos extends TrigExecutable{

    private final TrigExecutable sin;

    public Cos(TrigExecutable _sin){
        this.sin = _sin;
    }

    @Override
    public double execute(double digit) {
        double x = Math.abs(digit);
        x %= Math.PI * 2;

        double out;
        if ((x >= 0 && x <= Math.PI/2) || (x >= (3.0/2.0) * Math.PI && x <= 2 * Math.PI))
            out = Math.sqrt(1 - Math.pow(sin.execute(x), 2));
        else
            out = -Math.sqrt(1 - Math.pow(sin.execute(x), 2));

        tryWriteToFile(new String[] {String.valueOf(digit), String.valueOf(out), this.getClass().getSimpleName()});

        return out;
    }
}
