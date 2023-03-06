package lab2.mathematics.trigonometric;

public class Tg extends TrigExecutable{

    private final TrigExecutable sin;
    private final TrigExecutable cos;

    public Tg(TrigExecutable sin, TrigExecutable cos){
        this.sin = sin;
        this.cos = cos;
    }

    @Override
    public double execute(double digit) {

        double out = sin.execute(digit) / cos.execute(digit);

        tryWriteToFile(new String[] {String.valueOf(digit), String.valueOf(out), this.getClass().getSimpleName()});

        return out;
    }
}
