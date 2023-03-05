package lab2;

import lab2.Mathematics.Logarithmic.Ln;
import lab2.Mathematics.Logarithmic.Log;
import lab2.Mathematics.Logarithmic.LogExecutable;
import lab2.Mathematics.Trigonometric.*;
import lab2.Mathematics.Writable;

import java.io.FileWriter;
import java.io.IOException;

public class Task {

    private final TrigExecutable sin;
    private final TrigExecutable cos;
    private final TrigExecutable tg;
    private final TrigExecutable ctg;
    private final TrigExecutable sec;
    private final TrigExecutable csc;
    private final LogExecutable log;

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

    public Task(TrigExecutable _sin, TrigExecutable _cos, TrigExecutable _tg, TrigExecutable _ctg, TrigExecutable _sec, TrigExecutable _csc, LogExecutable _log) {
        this.sin = _sin;
        this.cos = _cos;
        this.tg = _tg;
        this.ctg = _ctg;
        this.sec = _sec;
        this.csc = _csc;
        this.log = _log;
    }

    void FillFunctionMockTrig(double min, double max, double step, TrigExecutable func)
    {
        FileWriter writer = setupWriter();
        func.setWriter(writer);

        for (double i = min; i <= max; i += step)
            func.execute(Math.round(i * 10.0) / 10.0);
    }

    void FillFunctionMockLog(double min, double max, double step, LogExecutable func, double base)
    {
        FileWriter writer = setupWriter();
        func.setWriter(writer);

        for (double i = min; i <= max; i += step)
            func.execute(Math.round(i * 10.0) / 10.0, base);
    }

    public static void main(String[] args) throws IOException {

        FileWriter writer = setupWriter();
        Task task = new Task(writer);
        System.out.println(task.Calculate(16));

        writer.flush();
        writer.close();
    }

    public double Calculate(double x){
        if (x <= 0)
            return lessThanZero(x);
        else
            return moreThanZero(x);
    }

    private double lessThanZero(double x){
        double firstBlank = Math.pow((tg.execute(x) + sin.execute(x)) - ctg.execute(x), 3);
        double secondBlank = (csc.execute(x) / sin.execute(x)) - sec.execute(x);
        double thirdBlank = (Math.pow(sec.execute(x), 2)) - sin.execute(x);

        return firstBlank / secondBlank / thirdBlank;
    }

    private double moreThanZero(double x){
        double temp =  ((((log.execute(x, 5) - log.execute(x, 10))
                + log.execute(x, 5)) - log.execute(x, 10))
                - (log.execute(x, 5) * log.execute(x, 3)));

        return Math.pow(temp, 3);
    }

    private static FileWriter setupWriter(){
        FileWriter writer;
        try {
            writer = new FileWriter("out.txt", false);
            return writer;
        } catch (Exception e){
            System.out.println("Проблема с записью в файл out.txt");
            return null;
        }
    }
}
