import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CalcTest {
    Calc calc;
    @Before
    public void init(){
        calc = new Calc();
    }

    @Test
    public void test1() throws Exception {

        int[] ar = {1,2,4,4,2,3,4,1,7};
        int[] arO = {1,7};


        Assert.assertArrayEquals(arO,calc.arrWork1(ar));

    }
    @Test(expected = RuntimeException.class)
    public void test1Exception(){
        int[] arO = {1,7};
        calc.arrWork1(arO);
    }


}
