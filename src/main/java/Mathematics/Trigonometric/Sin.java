package Mathematics.Trigonometric;

import Mathematics.Writable;

public class Sin extends Writable implements TrigExecutable{


    @Override
    public double execute(double digit) {

        double out = 1.0; // сделать аут человеком

        tryWriteToFile(new String[] {String.valueOf(digit), String.valueOf(out), this.getClass().getName()});

        return out;
    }
}
