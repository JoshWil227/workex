import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * This class tests the CalcModel class.
 * 
 * @author zhac109
 */
class TestCalcModel {

  /**
   * Test 1. Tests that the Model correctly implements the two Calculator interfaces correctly.
   * For this I 
   */
  @Test
  void testHasCalcs() {
    CalcModel model = new CalcModel();
    assertFalse(model.revPolishCalc == null);
    assertFalse(model.standardCalc == null);
  }

}
