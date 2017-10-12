import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CalcTest2 {
    Calc calc;
    @Before
    public void init(){
        calc = new Calc();
    }

    @Test
    public void test2() throws Exception{
        int[] ar = {1,2,4,4,2,3,4,1,7};
        int[] arR = {1,4,4,4,1};
        Assert.assertTrue(calc.arrWork2(arR));

    }

}
