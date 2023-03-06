package lab2.mathematics.logarithmic;

public class Log extends LogExecutable{

    private final LogExecutable ln;

    public Log(LogExecutable _ln){
        ln = _ln;
    }

    @Override
    public double execute(double digit, double base) {

        double out = 0;

        if(base != Math.E){
            out = ln.execute(digit, Math.E) / ln.execute(base, Math.E);
        }
        else {
            out = ln.execute(digit, Math.E);
        }

        tryWriteToFile(new String[] {String.valueOf(digit), String.valueOf(out), this.getClass().getSimpleName() + base});

        return out;
    }
}
