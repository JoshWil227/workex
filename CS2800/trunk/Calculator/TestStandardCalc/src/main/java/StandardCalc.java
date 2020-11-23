
/**
 * Evaluates expressions in Infix notation.
 * 
 * @author zhac109
 */
public class StandardCalc {

  RevPolishCalc calc = new RevPolishCalc();

  /**
   * Evaluates the user-entered expression passed into it in infix notation.
   * 
   * @param what the user-entered expression.
   * @return the answer to the expression.
   * @throws InvalidExpression if an invalid expression is passed in by the user.
   * @throws BadTypeException if the method tries to push the wrong type onto a stack.
   */
  public double evaluate(String what) throws InvalidExpression, BadTypeException {
    String[] inputs = what.split(" ");
    String op = "";
    String revPol = "";
    if (inputs.length == 1) {
      return Double.parseDouble(what);
    }
    for (int i = 0; i < inputs.length; i++) {
      String token = inputs[i];
      if (token.equals("+")) {
        op = "+";
      } else {
        revPol = revPol + token + " ";
      }
    }
    revPol = revPol + op;
    double ans = calc.evaluate(revPol);
    return ans;
  }
}
