
/**
 * Evaluates expressions in reverse polish notation.
 * 
 * @author zhac109
 */

public class RevPolishCalc {

  NumStack values = new NumStack();

  double ans;

  /**
   * Evaluates the expression passed into it in reverse polish notation.
   * 
   * @param what the expression.
   * @return the answer of the expression.
   * @throws InvalidExpression if an invalid expression is passed in.
   * @throws BadTypeException if the method tries to pop a symbol instead of a number.
   */
  public double evaluate(String what) throws InvalidExpression, BadTypeException {
    String symbols = "";
    if (what.length() == 1) {
      return Double.parseDouble(what);
    }
    if (what.length() == 3) {
      throw new InvalidExpression(null);
    }
    String[] inputs = what.split(" ");
    for (int i = 0; i < inputs.length; i++) {
      char token = inputs[i].charAt(0);
      if (Character.isDigit(token)) {
        double d = (double) token - '0';
        values.push(d);
      } else {
        symbols = symbols + inputs[i];
      }
    }
    double num1 = values.pop();
    double num2 = values.pop();
    switch (symbols.charAt(0)) {
      case '+':
        ans = num1 + num2;
        break;
      case '-':
        ans = num2 - num1;
        break;
      case '/':
        ans = num2 / num1;
        break;
      case '*':
        ans = num1 * num2;
        break;
      default:
        throw new InvalidExpression(null);
    }
    return ans;
  }
}
