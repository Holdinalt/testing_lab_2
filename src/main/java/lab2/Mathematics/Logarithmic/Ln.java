package lab2.Mathematics.Logarithmic;
import lab2.Mathematics.Writable;

public class Ln extends Writable implements LogExecutable{

    @Override
    public double execute(double digit, double base) throws Exception {

        if(base != Math.E){
            throw new Exception("Class Ln can count only E base");
        }


        double summ = 0;
        if (digit >= 0 && digit <= 2)
        {
            digit -= 1;

            for (int i = 1; i < 1000000; i += 2)
            {
                summ += (Math.pow(digit, i)/(double)i - Math.pow(digit, i+1)/(double)(i+1));
            }
        }
        else if (digit > 2)
        {
            digit = (digit / (digit - 1));

            for ( int i = 1; i < 500000; i ++ )
            {
                summ += 1.0 / (i * Math.pow(digit, i));
            }
        }

        double out = summ; // Сделать out человеком

        tryWriteToFile(new String[] {String.valueOf(digit), String.valueOf(out), this.getClass().getSimpleName()});

        return out;
    }
}
