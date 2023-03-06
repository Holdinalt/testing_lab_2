package lab2.mathematics.trigonometric;

public class Ctg extends TrigExecutable{

    private final TrigExecutable sin;
    private final TrigExecutable cos;

    public Ctg(TrigExecutable sin, TrigExecutable cos){
        this.sin = sin;
        this.cos = cos;
    }

    @Override
    public double execute(double digit) {

        double out = cos.execute(digit) / sin.execute(digit);

        tryWriteToFile(new String[] {String.valueOf(digit), String.valueOf(out), this.getClass().getSimpleName()});

        return out;
    }
}
