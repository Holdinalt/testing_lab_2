package lab2.mathematics.logarithmic;

public class LnFunc extends LogExecutable{

    @Override
    public double execute(double digit, final double base) {

        if(base != Math.E){
            return 0;
//            throw new Error("Class Ln can count only E base");
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

        final double out = summ; // Сделать out человеком

        tryWriteToFile(new String[] {String.valueOf(digit), String.valueOf(out), this.getClass().getSimpleName()});

        return out;
    }
}
