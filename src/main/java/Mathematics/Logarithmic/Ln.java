package Mathematics.Logarithmic;
import Mathematics.Writable;

public class Ln extends Writable implements LogExecutable{

    @Override
    public double execute(double digit, double base) throws Exception {

        if(base != Math.E){
            throw new Exception("Class Ln can count only E base");
        }

        double out = 1; // Сделать out человеком

        tryWriteToFile(new String[] {String.valueOf(digit), String.valueOf(out), this.getClass().getName()});

        return out;
    }
}
