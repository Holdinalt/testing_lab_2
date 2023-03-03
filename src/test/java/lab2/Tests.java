package lab2;

import lab2.Mathematics.Logarithmic.LogExecutable;
import lab2.Mathematics.Trigonometric.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.opencsv.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Tests {
    static TrigExecutable SinMock;
    static TrigExecutable CosMock;
    static TrigExecutable CscMock;
    static TrigExecutable CtgMock;
    static TrigExecutable SecMock;
    static TrigExecutable TgMock;
    static LogExecutable LogMock;

    private static double readFromCsvValue(String filename, double value)
    {
        try (CSVReader reader = new CSVReader(new FileReader(filename)))
        {
            String[] lineInArray;
            while ((lineInArray = reader.readNext()) != null) {
                if (Double.parseDouble(lineInArray[0]) == Math.round(value * 10.0)/10.0)
                {
                    return Double.parseDouble(lineInArray[1]);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Double.NaN;
    }

    @BeforeAll
    static void createMocks()
    {
        SinMock = new TrigExecutable() {
            @Override
            public double execute(double digit) {
                return readFromCsvValue("src/test/resources/sin.csv", digit);
            }
        };

        CosMock = new TrigExecutable() {
            @Override
            public double execute(double digit) {
                return readFromCsvValue("src/test/resources/cos.csv", digit);
            }
        };

        CscMock = new TrigExecutable() {
            @Override
            public double execute(double digit) {
                return readFromCsvValue("src/test/resources/csc.csv", digit);
            }
        };

        CtgMock = new TrigExecutable() {
            @Override
            public double execute(double digit) {
                return readFromCsvValue("src/test/resources/ctg.csv", digit);
            }
        };

        SecMock = new TrigExecutable() {
            @Override
            public double execute(double digit) {
                return readFromCsvValue("src/test/resources/sec.csv", digit);
            }
        };

        TgMock = new TrigExecutable() {
            @Override
            public double execute(double digit) {
                return readFromCsvValue("src/test/resources/tg.csv", digit);
            }
        };

        LogMock = new LogExecutable() {
            @Override
            public double execute(double digit, double base) {
                if (base == Math.E)
                    return readFromCsvValue("src/test/resources/ln.csv", digit);
                else if (base == 3)
                    return readFromCsvValue("src/test/resources/log3.csv", digit);
                else if (base == 5)
                    return readFromCsvValue("src/test/resources/log5.csv", digit);
                else if (base == 10)
                    return readFromCsvValue("src/test/resources/log10.csv", digit);
                return Double.NaN;
            }
        };
    }

    @Test
    void CosTest(){
        Cos cos = new Cos(SinMock);

        assertEquals(cos.execute(0),1, 0.1);

        assertEquals(cos.execute(1), 0.5403, 0.1);
        assertEquals(cos.execute(1.5), 0.0707, 0.1);
        assertEquals(cos.execute(3), -0.99, 0.1);

        assertEquals(cos.execute(-1), 0.5403, 0.1);
        assertEquals(cos.execute(-1.5), 0.0707, 0.1);
        assertEquals(cos.execute(-3), -0.99, 0.1);

        assertEquals(cos.execute(3.3), -0.987, 0.1);
        assertEquals(cos.execute(4), -0.6536, 0.1);
        assertEquals(cos.execute(6.2), 0.9965, 0.1);

        assertEquals(cos.execute(-3.3), -0.987, 0.1);
        assertEquals(cos.execute(-4), -0.6536, 0.1);
        assertEquals(cos.execute(-6.2), 0.9965, 0.1);

        assertEquals(cos.execute(-12), 0.84385, 0.1);
        assertEquals(cos.execute(25), 0.9912, 0.1);
    }

    @Test
    void CscTest(){
        Csc csc = new Csc(SinMock);

        assertEquals(csc.execute(0), Double.POSITIVE_INFINITY);

        assertEquals(csc.execute(1), 1.188395, 0.1);
        assertEquals(csc.execute(1.5), 1.002511, 0.1);
        assertEquals(csc.execute(3), 7.085, 0.1);

        assertEquals(csc.execute(-1), -1.188395, 0.1);
        assertEquals(csc.execute(-1.5), -1.002511, 0.1);
        assertEquals(csc.execute(-3), -7.085, 0.1);

        assertEquals(csc.execute(-4), 1.3213487, 0.1);
        assertEquals(csc.execute(4), -1.3213487, 0.1);
    }

    @Test
    void mainFunctionTest()
    {
        Task task = new Task(SinMock, CosMock, TgMock, CtgMock, SecMock, CscMock, LogMock);

        assertEquals(task.Calculate(0.5), -0.149738, 0.1);
        assertEquals(task.Calculate(0.2), -8.83166, 0.1);
        assertEquals(task.Calculate(1), 0, 0.1);
        assertEquals(task.Calculate(2), 0, 0.1);
        assertEquals(task.Calculate(5), -0.6425, 0.1);

        assertEquals(task.Calculate(-0.1), 8.4499, 0.1);
        assertEquals(task.Calculate(-0.2), 3.08731, 0.1);
        assertEquals(task.Calculate(-0.5), 0.0912918, 0.1);
        assertEquals(task.Calculate(-0.6), 0.002, 0.1);
        assertEquals(task.Calculate(-0.5), 0.0912918, 0.1);
        assertEquals(task.Calculate(-0.7), -0.0103, 0.1);
        assertEquals(task.Calculate(-0.8), -0.3309, 0.1);
        assertEquals(task.Calculate(-0.9), -27.5861, 0.1);
        assertEquals(task.Calculate(-1.0), 2.8975636, 0.1);
        assertEquals(task.Calculate(-2.0), 0.0227, 0.1);
        assertEquals(task.Calculate(-1.0), 2.8975636, 0.1);
    }
}
