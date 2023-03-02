package Mathematics.Trigonometric;
import Mathematics.Writable;

public class Cos extends Writable implements TrigExecutable{

    private final TrigExecutable sin;

    public Cos(TrigExecutable _sin){
        this.sin = _sin;
    }

    @Override
    public double execute(double digit) {

        double out = Math.sqrt(Math.pow(sin.execute(digit), 2) - 1);

        tryWriteToFile(new String[] {String.valueOf(digit), String.valueOf(out), this.getClass().getName()});

        return out;
    }
}
