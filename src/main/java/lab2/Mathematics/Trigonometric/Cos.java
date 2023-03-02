package lab2.Mathematics.Trigonometric;
import lab2.Mathematics.Writable;

public class Cos extends Writable implements TrigExecutable{

    private final TrigExecutable sin;

    public Cos(TrigExecutable _sin){
        this.sin = _sin;
    }

    @Override
    public double execute(double digit) {
        double x = digit;
        x %= Math.PI * 2;
        double out = Math.sqrt(Math.pow(sin.execute(x), 2) - 1);

        tryWriteToFile(new String[] {String.valueOf(digit), String.valueOf(out), this.getClass().getName()});

        return out;
    }
}
