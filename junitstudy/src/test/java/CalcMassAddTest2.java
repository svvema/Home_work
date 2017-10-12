import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class CalcMassAddTest2 {
    private boolean a;
    private int[] b;

    public CalcMassAddTest2(int[] b) {

        this.b = b;
    }

    @Parameterized.Parameters
    public static Collection<int[]> data() {
        return Arrays.asList(new int[][]{
                {1, 4, 1, 1},
                {1, 2, 4, 1},
                {1, 1, 1, 1},
                {4, 4, 4, 4},
                {4, 3, 1, 4}
        });
    }

    Calc calc;

    @Before
    public void init() {
        calc = new Calc();
    }

    @Test
    public void massTessAdd() {

        Assert.assertTrue(calc.arrWork2(b));


    }
}