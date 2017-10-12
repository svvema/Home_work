import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class CalcMassAddTest {
    private int[] a;
    private int[] b;
    public CalcMassAddTest(int[]a, int[]b){
        this.a = a;
        this.b = b;
    }

    @Parameterized.Parameters
    public static Collection data(){
        return Arrays.asList(new int[][][]{
                {{1,2,3,4,5,6}, {5,6}},
                {{1,2,3,4},{}},
                {{1,2,4,1,5},{3,1}}
        });
    }


    Calc calc;
    @Before
    public void init(){
        calc = new Calc();
    }
    @Test
    public void massTessAdd(){
    Assert.assertArrayEquals(b,calc.arrWork1(a));
    }
}