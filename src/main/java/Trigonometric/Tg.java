package Trigonometric;

public class Tg implements TrigExecutable{

    private final TrigExecutable sin;
    private final TrigExecutable cos;

    public Tg(TrigExecutable _sin, TrigExecutable _cos){
        sin = _sin;
        cos = _cos;
    }

    @Override
    public double execute(double digit) {
        return sin.execute(digit) / cos.execute(digit);
    }
}
