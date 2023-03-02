package Trigonometric;

public class Cos implements TrigExecutable{

    private final TrigExecutable sin;

    public Cos(TrigExecutable _sin){
        this.sin = _sin;
    }

    @Override
    public double execute(double digit) {

        return Math.sqrt(Math.pow(sin.execute(digit), 2) - 1);
    }
}
