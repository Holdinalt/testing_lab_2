package lab2;

import com.opencsv.CSVReader;
import lab2.mathematics.logarithmic.Log;
import lab2.mathematics.logarithmic.LogExecutable;
import lab2.mathematics.trigonometric.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.FileReader;

public class Lab2Tests {
    static TrigExecutable SinMock;
    static TrigExecutable CosMock;
    static TrigExecutable CscMock;
    static TrigExecutable CtgMock;
    static TrigExecutable SecMock;
    static TrigExecutable TgMock;
    static LogExecutable LogMock;

    static double delta = 0.1;

    private static double readFromCsvValue(String filename, double value) {
        try (CSVReader reader = new CSVReader(new FileReader(filename))) {
            String[] lineInArray;
            while ((lineInArray = reader.readNext()) != null) {
                if (Double.parseDouble(lineInArray[0]) == Math.round(value * 10.0) / 10.0) {
                    return Double.parseDouble(lineInArray[1]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Double.NaN;
    }

    @BeforeAll
    static void createMocks() {
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

    @Nested
    class TrigTests {

        @ParameterizedTest
        @ValueSource(doubles = {0, Math.PI, 2 * Math.PI, 0.5 * Math.PI})
        void CosTest(double x) {
            Cos cos = new Cos(SinMock);

            assertEquals(cos.execute(x), CosMock.execute(x), delta);
            assertEquals(cos.execute(-x), CosMock.execute(-x), delta);
        }

        @ParameterizedTest
        @ValueSource(doubles = {0, Math.PI / 2, Math.PI, 3 * Math.PI / 2, 2 * Math.PI})
        void CscTest(double x) {
            Csc csc = new Csc(SinMock);

            assertEquals(csc.execute(x), CscMock.execute(x), delta);
            assertEquals(csc.execute(-x), CscMock.execute(-x), delta);
        }

        @ParameterizedTest
        @ValueSource(doubles = {Math.PI / 2, 3 * Math.PI / 4, Math.PI / 4})
        void CtgTest(double x) {
            Ctg ctg = new Ctg(SinMock, CosMock);

            assertEquals(ctg.execute(x), CtgMock.execute(x), delta);
            assertEquals(ctg.execute(-x), CtgMock.execute(-x), delta);
        }

        @ParameterizedTest
        @ValueSource(doubles = {0, Math.PI, -Math.PI, Math.PI / 2, 3 * Math.PI / 2})
        void Sec(double x) {

            Sec sec = new Sec(CosMock);

            assertEquals(sec.execute(x), SecMock.execute(x), delta);
        }

        @ParameterizedTest
        @ValueSource(doubles = {0, Math.PI / 4, -Math.PI / 4})
        void Tg(double x) {

            Tg tg = new Tg(SinMock, CosMock);
            assertEquals(tg.execute(x), TgMock.execute(x), delta);
        }
    }

    @Nested
    class LogTests {

        @ParameterizedTest
        @ValueSource(doubles = {1, 3, 9, -3})
        void log3Test(double x) {
            Log log = new Log(LogMock);
            assertEquals(log.execute(x, 3), LogMock.execute(x, 3), delta);
        }

        @ParameterizedTest
        @ValueSource(doubles = {1, 5, 25, -1})
        void Log5Test(double x) {
            Log log = new Log(LogMock);
            assertEquals(log.execute(x, 5), LogMock.execute(x, 5), delta);
        }

        @ParameterizedTest
        @ValueSource(doubles = {1, 4, 10, -0.4, -1})
        void Log10Test(double x) {
            Log log = new Log(LogMock);
            assertEquals(log.execute(x, 10), LogMock.execute(x, 10), delta);
        }

        @ParameterizedTest
        @ValueSource(doubles = {1, 4.5, 7.5, 12, -1.5})
        void LnTest(double x) {
            Log log = new Log(LogMock);
            assertEquals(log.execute(x, Math.E), LogMock.execute(x, Math.E), delta);
        }
    }

    @Test
    void mainFunctionTest() {
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
        assertEquals(task.Calculate(-1.5), 1.2868, 0.1);
        assertEquals(task.Calculate(-2.0), 0.0227, 0.1);
        assertEquals(task.Calculate(-3.0), -5.79957, 0.1);

    }
}