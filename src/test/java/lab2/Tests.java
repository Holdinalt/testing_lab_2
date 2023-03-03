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

    private double readFromCsvValue(String filename, double value)
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
    void createMocks()
    {
        SinMock = new TrigExecutable() {
            @Override
            public double execute(double digit) {
                return readFromCsvValue("src/test/resources/sin.csv", digit);
            }
        };
    }

    @Test
    void CscTest(){

        Csc csc = new Csc(SinMock);

        assertEquals(csc.execute(1), 0.1);
        assertEquals(csc.execute(0), 0);
    }
}
