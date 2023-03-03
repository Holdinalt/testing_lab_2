package lab2;

import lab2.Mathematics.Trigonometric.Csc;
import lab2.Mathematics.Trigonometric.TrigExecutable;
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

    private static double readFromCsvValue(String filename, double value)
    {
        try (CSVReader reader = new CSVReader(new FileReader(filename)))
        {
            String[] lineInArray;
            while ((lineInArray = reader.readNext()) != null) {
                if (Double.parseDouble(lineInArray[0]) == value)
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
}
