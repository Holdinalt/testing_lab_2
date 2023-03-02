package Trigonometric;

public class Csc implements TrigExecutable{

    private final TrigExecutable sin;

    public Csc(TrigExecutable _sin){
        sin = _sin;
    }

    @Override
    public double execute(double digit) {

        return 1 / sin.execute(digit);
    }
}
