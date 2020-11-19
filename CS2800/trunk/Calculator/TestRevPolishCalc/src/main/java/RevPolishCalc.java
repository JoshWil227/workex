
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
    char symbol;
    boolean hasSymb = false;
    String[] inputs = what.split(" ");
    if (inputs.length == 1) {
      return Double.parseDouble(inputs[0]);
    }
    for (int i = 0; i < inputs.length; i++) {
      char token = inputs[i].charAt(0);
      if (Character.isDigit(token)) {
        double d = Double.parseDouble(inputs[i]);
        values.push(d);
      } else {
        if (values.size <= 1) { // if not enough numbers in stack to perform an operation
          throw new InvalidExpression(null);
        }
        symbol = inputs[i].charAt(0);
        hasSymb = true;
        double num1 = values.pop();
        double num2 = values.pop();
        switch (symbol) {
          case '+':
            ans = num1 + num2;
            break;
          case '-':
            ans = num2 - num1;
            break;
          case '/':
            if (num1 == 0) {
              throw new InvalidExpression(null);
            }
            ans = num2 / num1;
            break;
          case '*':
            ans = num1 * num2;
            break;
          default:
            throw new InvalidExpression(null);
        }
        values.push(ans);
      }
    }
    if (!hasSymb) {
      throw new InvalidExpression(null);
    }
    ans = values.pop();
    return ans;
  }
}
