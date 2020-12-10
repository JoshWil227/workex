
/**
 * Evaluates expressions in Infix notation.
 * 
 * @author zhac109
 */
public class StandardCalc implements Calculator {

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
    OpStack stack = new OpStack();
    StrStack revStack = new StrStack();
    String revPol = "";
    if (inputs.length == 1) {
      return Double.parseDouble(what);
    }
    for (int a = 0; a < inputs.length; a++) { // reverses stack so right to left read works
      String temp = inputs[a];
      revStack.push(temp);
    }
    System.out.println();
    for (int i = revStack.size; i > 0;) {
      String token = revStack.pop();
      switch (token) {
        case "+":
          stack.push(Symbol.PLUS);
          break;
        case "-":
          stack.push(Symbol.MINUS);
          break;
        case "/":
          stack.push(Symbol.DIVIDE);
          break;
        case "*":
          stack.push(Symbol.TIMES);
          break;
        default:
          revPol = revPol + token + " ";
      }
      i = revStack.size;
    }
    for (int i = stack.size; i > 0;) {
      revPol = revPol + stack.pop() + " ";
      i = stack.size;
    }
    double ans = calc.evaluate(revPol);
    return ans;
  }
}
