package lab2;

import lab2.mathematics.logarithmic.LnFunc;
import lab2.mathematics.logarithmic.LogFunc;
import lab2.mathematics.logarithmic.LogExecutable;
import lab2.mathematics.trigonometric.*;

import java.io.FileWriter;
import java.io.IOException;

public class LabTask {

    private final TrigExecutable sin;
    private final TrigExecutable cos;
    private final TrigExecutable tg;
    private final TrigExecutable ctg;
    private final TrigExecutable sec;
    private final TrigExecutable csc;
    private final LogExecutable log;

//    public Task() {
//        this.sin = new Sin();
//        this.cos = new Cos(sin);
//        this.tg = new Tg(sin, cos);
//        this.ctg = new Ctg(sin, cos);
//        this.sec = new Sec(cos);
//        this.csc = new Csc(sin);
//        this.log = new Log(new Ln());
//    }

    public LabTask(final FileWriter writer) {
        this.sin = new SinFunc();
        this.cos = new CosFunc(sin);
        this.tg = new TgFunc(sin, cos);
        this.ctg = new CtgFunc(sin, cos);
        this.sec = new SecFunc(cos);
        this.csc = new CscFunc(sin);
        this.log = new LogFunc(new LnFunc());

        this.sin.setWriter(writer);
        this.cos.setWriter(writer);
        this.tg.setWriter(writer);
        this.ctg.setWriter(writer);
        this.sec.setWriter(writer);
        this.csc.setWriter(writer);
        this.log.setWriter(writer);
    }

    public LabTask(final TrigExecutable sin,
                   final TrigExecutable cos,
                   final TrigExecutable tg,
                   final TrigExecutable ctg,
                   final TrigExecutable sec,
                   final TrigExecutable csc,
                   final LogExecutable log
    ) {
        this.sin = sin;
        this.cos = cos;
        this.tg = tg;
        this.ctg = ctg;
        this.sec = sec;
        this.csc = csc;
        this.log = log;
    }

//    void FillFunctionMockTrig(double min, double max, double step, TrigExecutable func)
//    {
//        FileWriter writer = setupWriter();
//        func.setWriter(writer);
//
//        for (double i = min; i <= max; i += step)
//            func.execute(Math.round(i * 10.0) / 10.0);
//    }
//
//    void FillFunctionMockLog(double min, double max, double step, LogExecutable func, double base)
//    {
//        FileWriter writer = setupWriter();
//        func.setWriter(writer);
//
//        for (double i = min; i <= max; i += step)
//            func.execute(Math.round(i * 10.0) / 10.0, base);
//    }

    public static void main(final String[] args) throws IOException {

//        final FileWriter writer = new FileWriter("out.txt", false);
//
//        final LabTask task = new LabTask(writer);
//
//        for(int i = -10; i <= 10; i++){
//            writer.write(i + "," + task.calculate(i) + ",func\n");
//        }
//
////        writer.flush();
//        writer.close();
    }

    public double calculate(final double x){
        if (x <= 0){
            return lessThanZero(x);
        }
        else{
            return moreThanZero(x);
        }
    }

    private double lessThanZero(final double x){
        final double firstBlank = Math.pow(tg.execute(x) + sin.execute(x) - ctg.execute(x), 3);
        final double secondBlank = csc.execute(x) / sin.execute(x) - sec.execute(x);
        final double thirdBlank = Math.pow(sec.execute(x), 2) - sin.execute(x);

        return firstBlank / secondBlank / thirdBlank;
    }

    private double moreThanZero(final double x){
        final double temp =  (log.execute(x, 5) - log.execute(x, 10)
                + log.execute(x, 5) - log.execute(x, 10))
                - (log.execute(x, 5) * log.execute(x, 3));

        return Math.pow(temp, 3);
    }
}
