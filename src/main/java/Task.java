import Logarithmic.Ln;
import Logarithmic.Log;
import Trigonometric.*;

import java.io.FileWriter;
import java.io.Writer;

public class Task {

    private final Sin sin;
    private final Cos cos;
    private final Tg tg;
    private final Ctg ctg;
    private final Sec sec;
    private final Csc csc;
    private final Log log;

    public Task() {
        this.sin = new Sin();
        this.cos = new Cos(sin);
        this.tg = new Tg(sin, cos);
        this.ctg = new Ctg(sin, cos);
        this.sec = new Sec(cos);
        this.csc = new Csc(sin);
        this.log = new Log(new Ln());
    }

    public static void main(String[] args) {

        FileWriter writer = setupWriter();

    }

    private double higherThanZero(double x){
        double firstBlank = Math.pow((tg.execute(x) + sin.execute(x)) - ctg.execute(x), 3);
        double secondBlank = (csc.execute(x) / sin.execute(x)) - sec.execute(x);
        double thirdBlank = (Math.pow(sec.execute(x), 2)) - sin.execute(x);

        return firstBlank / secondBlank / thirdBlank;
    }

    private double lessThanZero(double x){
        double temp =  ((((log.execute(x, 5) - log.execute(x, 10)) + log.execute(x, 5)) - log.execute(x, 10)) -
                - (log.execute(x, 5) * log.execute(x, 3)));

        return Math.pow(temp, 3);
    }

    private static FileWriter setupWriter(){
        FileWriter writer;
        try {
            writer = new FileWriter("out.txt", false);
            return writer;
        } catch (Exception e){
            System.out.println("Проблема с записью в фаил out.txt");
            return null;
        }
    }
}
