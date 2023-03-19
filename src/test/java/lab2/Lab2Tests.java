package lab2;

import com.opencsv.CSVReader;
import lab2.mathematics.logarithmic.LnFunc;
import lab2.mathematics.logarithmic.LogFunc;
import lab2.mathematics.logarithmic.LogExecutable;
import lab2.mathematics.trigonometric.*;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.nio.file.Files;
import java.nio.file.Paths;

class Lab2Tests {

    final static TrigExecutable SIN_MOCK = new TrigExecutable() {
        @Override
        public double execute(final double digit) {
            return readFromCsvValue("src/test/resources/sin.csv", digit);
        }
    };

    final static TrigExecutable COS_MOCK = new TrigExecutable() {
        @Override
        public double execute(final double digit) {
            return readFromCsvValue("src/test/resources/cos.csv", digit);
        }
    };

    final static TrigExecutable CSC_MOCK = new TrigExecutable() {
        @Override
        public double execute(final double digit) {
            return readFromCsvValue("src/test/resources/csc.csv", digit);
        }
    };

    final static TrigExecutable CTG_MOCK = new TrigExecutable() {
        @Override
        public double execute(final double digit) {
            return readFromCsvValue("src/test/resources/ctg.csv", digit);
        }
    };

    final static TrigExecutable SEC_MOCK = new TrigExecutable() {
        @Override
        public double execute(final double digit) {
            return readFromCsvValue("src/test/resources/sec.csv", digit);
        }
    };

    final static TrigExecutable TG_MOCK = new TrigExecutable() {
        @Override
        public double execute(final double digit) {
            return readFromCsvValue("src/test/resources/tg.csv", digit);
        }
    };

    final static LogExecutable LOG_MOCK = new LogExecutable() {
        @Override
        public double execute(final double digit, final double base) {

            if (base == Math.E){
                return readFromCsvValue("src/test/resources/ln.csv", digit);
            }
            else if (base == 3){
                return readFromCsvValue("src/test/resources/log3.csv", digit);
            }
            else if (base == 5){
                return readFromCsvValue("src/test/resources/log5.csv", digit);
            }
            else if (base == 10){
                return readFromCsvValue("src/test/resources/log10.csv", digit);
            }
            return Double.NaN;
        }
    };

    final static double DELTA = 0.1;

    private static double readFromCsvValue(final String filename, final double value) {

        try (CSVReader reader = new CSVReader(Files.newBufferedReader(Paths.get(filename)))) {

            String[] lineInArray;
            lineInArray = reader.readNext();
            while (lineInArray != null) {
                if (Double.parseDouble(lineInArray[0]) == Math.round(value * 10.0) / 10.0) {
                    return Double.parseDouble(lineInArray[1]);
                }
                lineInArray = reader.readNext();
            }

        } catch (Exception e) {
            return 0;
//            e.printStackTrace();
        }
        return Double.NaN;
    }

    @Nested
    class TrigTests {
        @ParameterizedTest
        @ValueSource(doubles = {0, Math.PI, 2 * Math.PI, 0.5 * Math.PI})
        void sinTest(final double x) {
            final SinFunc sin = new SinFunc();

            assertEquals(sin.execute(x), SIN_MOCK.execute(x), DELTA);
            assertEquals(sin.execute(-x), SIN_MOCK.execute(-x), DELTA);
        }

        @ParameterizedTest
        @ValueSource(doubles = {0, Math.PI, 2 * Math.PI, 0.5 * Math.PI})
        void cosTest(final double x) {
            final CosFunc cos = new CosFunc(SIN_MOCK);

            assertEquals(cos.execute(x), COS_MOCK.execute(x), DELTA);
            assertEquals(cos.execute(-x), COS_MOCK.execute(-x), DELTA);
        }

        @ParameterizedTest
        @ValueSource(doubles = {0, Math.PI / 2, Math.PI, 3 * Math.PI / 2, 2 * Math.PI})
        void cscTest(final double x) {
            final CscFunc csc = new CscFunc(SIN_MOCK);

            assertEquals(csc.execute(x), CSC_MOCK.execute(x), DELTA);
            assertEquals(csc.execute(-x), CSC_MOCK.execute(-x), DELTA);
        }

        @ParameterizedTest
        @ValueSource(doubles = {Math.PI / 2, 3 * Math.PI / 4, Math.PI / 4})
        void ctgTest(final double x) {
            final CtgFunc ctg = new CtgFunc(SIN_MOCK, COS_MOCK);

            assertEquals(ctg.execute(x), CTG_MOCK.execute(x), DELTA);
            assertEquals(ctg.execute(-x), CTG_MOCK.execute(-x), DELTA);
        }

        @ParameterizedTest
        @ValueSource(doubles = {0, Math.PI, -Math.PI, Math.PI / 2, 3 * Math.PI / 2})
        void secTest(final double x) {

            final SecFunc sec = new SecFunc(COS_MOCK);

            assertEquals(sec.execute(x), SEC_MOCK.execute(x), DELTA);
        }

        @ParameterizedTest
        @ValueSource(doubles = {0, Math.PI / 4, -Math.PI / 4})
        void tgTest(final double x) {

            final TgFunc tg = new TgFunc(SIN_MOCK, COS_MOCK);
            assertEquals(tg.execute(x), TG_MOCK.execute(x), DELTA);
        }
    }

    @Nested
    class LogTests {

        @ParameterizedTest
        @ValueSource(doubles = {1, 3, 9, -3})
        void log3Test(final double x) {
            final LogFunc log = new LogFunc(LOG_MOCK);
            assertEquals(log.execute(x, 3), LOG_MOCK.execute(x, 3), DELTA);
        }

        @ParameterizedTest
        @ValueSource(doubles = {1, 5, 25, -1})
        void log5Test(final double x) {
            final LogFunc log = new LogFunc(LOG_MOCK);
            assertEquals(log.execute(x, 5), LOG_MOCK.execute(x, 5), DELTA);
        }

        @ParameterizedTest
        @ValueSource(doubles = {1, 4, 10, -0.4, -1})
        void log10Test(final double x) {
            final LogFunc log = new LogFunc(LOG_MOCK);
            assertEquals(log.execute(x, 10), LOG_MOCK.execute(x, 10), DELTA);
        }

        @ParameterizedTest
        @ValueSource(doubles = {1, Math.E, 3, 5})
        void lnTest(final double x) {
            final LnFunc ln = new LnFunc();

            assertEquals(ln.execute(x, Math.E), LOG_MOCK.execute(x, Math.E), DELTA);
        }
    }

    @Test
    void mainFunctionTest() {
        final LabTask task = new LabTask(SIN_MOCK, COS_MOCK, TG_MOCK, CTG_MOCK, SEC_MOCK, CSC_MOCK, LOG_MOCK);

        assertEquals(task.calculate(0.5), -0.149_738, 0.1);
        assertEquals(task.calculate(0.2), -8.831_66, 0.1);
        assertEquals(task.calculate(1), 0, 0.1);
        assertEquals(task.calculate(2), 0, 0.1);
        assertEquals(task.calculate(5), -0.642_5, 0.1);

        assertEquals(task.calculate(-0.1), 8.449_9, 0.1);
        assertEquals(task.calculate(-0.2), 3.087_31, 0.1);
        assertEquals(task.calculate(-0.5), 0.091_291_8, 0.1);
        assertEquals(task.calculate(-0.6), 0.002, 0.1);
        assertEquals(task.calculate(-0.5), 0.091_291_8, 0.1);
        assertEquals(task.calculate(-0.7), -0.010_3, 0.1);
        assertEquals(task.calculate(-0.8), -0.330_9, 0.1);
        assertEquals(task.calculate(-0.9), -27.586_1, 0.1);
        assertEquals(task.calculate(-1.0), 2.897_563_6, 0.1);
        assertEquals(task.calculate(-1.5), 1.286_8, 0.1);
        assertEquals(task.calculate(-2.0), 0.022_7, 0.1);
        assertEquals(task.calculate(-3.0), -5.799_57, 0.1);

    }
}
