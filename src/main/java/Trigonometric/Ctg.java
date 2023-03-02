package Trigonometric;

public class Ctg implements TrigExecutable{

    private final TrigExecutable sin;
    private final TrigExecutable cos;

    public Ctg(TrigExecutable _sin, TrigExecutable _cos){
        sin = _sin;
        cos = _cos;
    }

    @Override
    public double execute(double digit) {
        return cos.execute(digit) / sin.execute(digit);
    }
}
