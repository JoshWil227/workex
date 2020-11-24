import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

/**
 * This class tests that the Enum Type has different string outputs for each different type.
 * 
 * @author zhac109
 */
public class TestType {

  
  /**
   * Test 1. Tests to see if each Type has a different string when calling the toString method.
   * For this I set up the Type enum, its constructor, and its toString method.
   */
  @Test
  void testTypeString() {
    Set<String> typeSet = new HashSet<String>();
    for (Type type : Type.values()) {
      typeSet.add(type.toString());
    }
    assertEquals(typeSet.size(), 4, "Each Type should output a different string");
  }
}
