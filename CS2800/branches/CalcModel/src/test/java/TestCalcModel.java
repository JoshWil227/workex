import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * This class tests the CalcModel class.
 * 
 * @author zhac109
 */
class TestCalcModel {

  /**
   * Test 1. Tests that the Model correctly implements the two Calculator interfaces correctly. For
   * this I had the CalcModel initialise the two Calculator interfaces.
   */
  @Test
  void testHasCalcs() {
    CalcModel model = new CalcModel();
    assertFalse(model.revPolishCalc == null, "The calculator should be initialised.");
    assertFalse(model.standardCalc == null, "The calculator should be initialised.");
  }

  /**
   * Test 2. Tests that the evaluate method works correctly for both notations. For this I created
   * the CalcModel evaluate method, and had it call the evaluate method of one of the two Calculator
   * interfaces' methods depending on the value of the boolean.
   * 
   * @throws InvalidExpression if an invalid expression is passed in.
   * @throws BadTypeException if the method tries to pop a symbol instead of a number.
   */
  @Test
  void testEval() throws InvalidExpression, BadTypeException {
    CalcModel model = new CalcModel();
    assertEquals(model.evaluate("6 * 3", true), 18,
        "The model should evaluate the expression in the correct notation.");
    assertEquals(model.evaluate("6 3 *", false), 18,
        "The model should evaluate the expression in the correct notation.");
  }

}
