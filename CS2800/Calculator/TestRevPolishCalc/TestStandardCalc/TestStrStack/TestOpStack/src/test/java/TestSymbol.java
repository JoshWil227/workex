import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

/**
 * This class tests that the Enum Type has different string outputs for each different type.
 * 
 * @author zhac109
 */
public class TestSymbol {

  
  /**
   * Test 1. Tests to see if each Symbol has a different string when calling the toString method.
   * For this I set up the Symbol enum, its constructor, and its toString method.
   */
  @Test
  void testSymbolString() {
    Set<String> symbolSet = new HashSet<String>();
    for (Symbol symbol : Symbol.values()) {
      symbolSet.add(symbol.toString());
    }
    assertEquals(symbolSet.size(), 7, "Each Symbol should output a different string");
  }
}
