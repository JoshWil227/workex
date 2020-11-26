import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * This class tests the entry class.
 * 
 * @author zhac109
 */
public class TestEntry {

  double db;
  Symbol leftBracket;
  String string;
  Entry dbEntry;
  Entry syEntry;
  Entry stEntry;

  /**
   * Assigns the double, symbol, and string, along with their respective entries, present in each
   * test. Was included when refactoring after 6.
   */
  @BeforeEach
  public void setupEntries() {
    db = 3;
    leftBracket = Symbol.LEFT_BRACKET;
    string = "h";
    dbEntry = new Entry(db);
    syEntry = new Entry(leftBracket);
    stEntry = new Entry(string);
  }

  /**
   * Test 1. Tests whether a float Entry can be created and if the getType method works. For this I
   * created the Entry class, the Entry float constructor, and the getType method.
   */
  @Test
  void testDoubleGetType() {
    String type = dbEntry.getType();
    assertEquals(type, "Number", "The type returned should be number");
  }

  /**
   * Test 2. Tests whether a symbol Entry can be created and if the get type method returns
   * correctly. For this I created the symbol Entry constructor.
   */
  @Test
  void testSymbolGetType() {
    String type = syEntry.getType();
    assertEquals(type, "Symbol", "The type returned should be symbol");
  }

  /**
   * Test 3. Tests whether a String Entry can be created and if the type method returns correctly.
   * For this I created the String Entry constructor.
   */
  @Test
  void testStringGetType() {
    String type = stEntry.getType();
    assertEquals(type, "String", "The type returned should be String");
  }

  /**
   * Test 4. Tests whether the getString method works and if it throws a BadTypeException when
   * called from an incorrect type. For this I created the getString method.
   * 
   * @throws BadTypeException if the getString method correctly rejects calls from incorrect types.
   */

  @Test
  void testGetString() throws BadTypeException {
    assertThrows(BadTypeException.class, () -> dbEntry.getString(),
        "Trying to get String on a Entry that isn't a string should throw a BadTypeException");
    assertThrows(BadTypeException.class, () -> syEntry.getString(),
        "Trying to get String on a Entry that isn't a string should throw a BadTypeException");
    assertEquals("h", stEntry.getString(),
        "The string returned should be equal to the user-input string");
  }

  /**
   * Test 5. Tests whether the getSymbol method works and if it throws a BadTypeException when
   * called from an incorrect type. For this I created the getSymbol method
   * 
   * @throws BadTypeException if the getSymbol method correctly rejects calls from incorrect types.
   */
  @Test
  void testGetSymbol() throws BadTypeException {
    assertThrows(BadTypeException.class, () -> dbEntry.getSymbol(),
        "Trying to get symbol on a Entry that isn't a symbol should throw a BadTypeException");
    assertEquals(Symbol.LEFT_BRACKET, syEntry.getSymbol(),
        "The symbol returned should be equal to the user-input symbol");
    assertThrows(BadTypeException.class, () -> stEntry.getSymbol(),
        "Trying to get symbol on a Entry that isn't a symbol should throw a BadTypeException");
  }

  /**
   * Test 6. Tests whether the getValue method works and if it throws a BadTypeException when called
   * from an incorrect type. For this I created the getValue method.
   * 
   * @throws BadTypeException if the getValue method correctly rejects calls from incorrect types.
   */

  @Test
  void testGetValue() throws BadTypeException {
    assertThrows(BadTypeException.class, () -> dbEntry.getSymbol(),
        "Trying to get symbol on a Entry that isn't a symbol should throw a BadTypeException");
    assertEquals(3, dbEntry.getValue(),
        "The symbol returned should be equal to the user-input symbol");
    assertThrows(BadTypeException.class, () -> stEntry.getSymbol(),
        "Trying to get symbol on a Entry that isn't a symbol should throw a BadTypeException");
  }

  /**
   * Test 7. Tests whether the Equals method works and that the hashCode returns the same hash for
   * entries of the same type and value, returning true if two entries have the same type and value.
   * For this I created the Equals and hashCode methods using the autogenerate function in eclipse.
   */

  @Test
  void testEqualsAndHash() {
    Entry otherEntry = new Entry(3);
    assertTrue(dbEntry.equals(otherEntry), "Two of the same Entries should be equal");
    assertTrue(dbEntry.hashCode() == otherEntry.hashCode());
    assertFalse(dbEntry.equals(syEntry), "Entries of different types should not be equal");
    Entry difEntry = new Entry(5);
    assertFalse(dbEntry.equals(difEntry), "Entries of different values should not be equal");
  }


}
