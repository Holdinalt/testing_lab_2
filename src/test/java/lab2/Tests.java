package lab2;

import lab2.Mathematics.Trigonometric.Csc;
import lab2.Mathematics.Trigonometric.TrigExecutable;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Tests {

    class TrigMock implements TrigExecutable{

        @Override
        public double execute(double digit) {
            return digit * 10;
        }
    }

    @Test
    void CscTest(){

        Csc csc = new Csc(new TrigMock());

        assertEquals(csc.execute(1), 0.1);
        assertEquals(csc.execute(0), 0);
    }
}
