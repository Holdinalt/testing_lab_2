package Mathematics.Logarithmic;
import Mathematics.Writable;

public class Log extends Writable implements LogExecutable{

    private final LogExecutable ln;

    public Log(LogExecutable _ln){
        ln = _ln;
    }

    @Override
    public double execute(double digit, double base) {

        double out = 0;

        try {
            if(base != Math.E){
                out = ln.execute(digit, Math.E) / ln.execute(base, Math.E);
            }
            else {
                out = ln.execute(digit, Math.E);
            }
        } catch (Exception e){
            System.out.println("Ln function in Log class does not work: " + e.getMessage());
        }

        tryWriteToFile(new String[] {String.valueOf(digit), String.valueOf(out), this.getClass().getName()});

        return out;
    }
}
