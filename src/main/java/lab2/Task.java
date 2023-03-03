package lab2;

import lab2.Mathematics.Logarithmic.Ln;
import lab2.Mathematics.Logarithmic.Log;
import lab2.Mathematics.Trigonometric.*;

import java.io.FileWriter;
import java.io.IOException;

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

    public Task(FileWriter writer) {
        this.sin = new Sin();
        this.cos = new Cos(sin);
        this.tg = new Tg(sin, cos);
        this.ctg = new Ctg(sin, cos);
        this.sec = new Sec(cos);
        this.csc = new Csc(sin);
        this.log = new Log(new Ln());

        this.sin.setWriter(writer);
        this.cos.setWriter(writer);
        this.tg.setWriter(writer);
        this.ctg.setWriter(writer);
        this.sec.setWriter(writer);
        this.csc.setWriter(writer);
        this.log.setWriter(writer);
    }

    public static void main(String[] args) throws IOException {

        FileWriter writer = setupWriter();
        Task task = new Task(writer);
        for (double i = -10; i < 10; i += 0.1)
            task.sin.execute(Math.round(i * 10.0)/10.0);

        writer.flush();
        writer.close();
    }

    public double Calculate(double x){
        if (x > 0)
            return higherThanZero(x);
        else
            return lessThanZero(x);
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
