import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * The test class for the RevPolishCalc class.
 * 
 * @author zhac109
 */
class TestRevPolishCalc {

  private RevPolishCalc calc = new RevPolishCalc();
  
  @Test
  void testOneNum() {
    String s = "5";
    double d = calc.evaluate(s);
    assertEquals(d, 5, "The single number returned should be the same as the one entered.");
  }

}
