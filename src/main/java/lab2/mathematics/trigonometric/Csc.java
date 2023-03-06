package lab2.mathematics.trigonometric;

public class Csc extends TrigExecutable{

    private final TrigExecutable sin;

    public Csc(TrigExecutable sin){
        this.sin = sin;
    }

    @Override
    public double execute(double digit) {

        double out = 1 / sin.execute(digit);

        tryWriteToFile(new String[] {String.valueOf(digit), String.valueOf(out), this.getClass().getSimpleName()});

        return out;
    }
}
