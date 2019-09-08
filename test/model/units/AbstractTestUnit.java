package model.units;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import model.items.*;
import model.map.Field;
import model.map.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public abstract class AbstractTestUnit implements ITestUnit {

  protected Alpaca targetAlpaca;
  protected Bow bow;
  protected Field field;
  protected Axe axe;
  protected Sword sword;
  protected Staff staff;
  protected Spear spear;
  protected DarkBook darkBook;
  protected LightBook lightBook;
  protected SoulBook soulBook;

  @Override
  public void setTargetAlpaca() {
    targetAlpaca = new Alpaca(50, 2, field.getCell(1, 0));
  }

  /**
   * Sets up the units and weapons to be tested
   */
  @BeforeEach
  public void setUp() {
    setField();
    setTestUnit();
    setTargetAlpaca();
    setWeapons();
  }

  /**
   * Set up the game field
   */
  @Override
  public void setField() {
    this.field = new Field();
    this.field.addCells(true, new Location(0, 0), new Location(0, 1), new Location(0, 2),
        new Location(1, 0), new Location(1, 1), new Location(1, 2), new Location(2, 0),
        new Location(2, 1), new Location(2, 2));
  }

  /**
   * Set up the main unit that's going to be tested in the test set
   */
  @Override
  public abstract void setTestUnit();

  /**
   * Creates a set of testing weapons
   */
  @Override
  public void setWeapons() {
    this.axe = new Axe("Axe", 10, 1, 2);
    this.sword = new Sword("Sword", 10, 1, 2);
    this.spear = new Spear("Spear", 10, 1, 2);
    this.staff = new Staff("Staff", 10, 1, 2);
    this.bow = new Bow("Bow", 10, 2, 3);
    this.darkBook = new DarkBook("DarkBook", 12, 2, 4);
    this.lightBook = new LightBook("LightBook", 12, 2, 4);
    this.soulBook = new SoulBook("SoulBook", 12, 2, 4);
  }

  /**
   * Checks that the constructor works properly.
   */
  @Override
  @Test
  public void constructorTest() {
    assertEquals(50, getTestUnit().getCurrentHitPoints());
    assertEquals(50, getTestUnit().getMaxHitPoints());
    assertEquals(2, getTestUnit().getMovement());
    assertEquals(new Location(0, 0), getTestUnit().getLocation());
    assertTrue(getTestUnit().getItems().isEmpty());
  }

  /**
   * @return the current unit being tested
   */
  @Override
  public abstract IUnit getTestUnit();

  /**
   * Tries to equip a weapon to the alpaca and verifies that it was not equipped
   *
   * @param item
   *     to be equipped
   */
  @Override
  public void checkEquippedItem(IEquipableItem item) {
    assertNull(getTestUnit().getEquippedItem());
    getTestUnit().equipItem(item);
    assertNull(getTestUnit().getEquippedItem());
    getTestUnit().unequipItem();
    assertNull(getTestUnit().getEquippedItem());
  }

  @Override
  @Test
  public void equipAxeTest() { checkEquippedItem(getAxe()); }

  /**
   * @return the test axe
   */
  @Override
  public Axe getAxe() {
    return axe;
  }

  @Override
  @Test
  public void equipSwordTest() {
    checkEquippedItem(getSword());
  }

  /**
   * @return the test sword
   */
  @Override
  public Sword getSword() {
    return sword;
  }

  @Override
  @Test
  public void equipSpearTest() { checkEquippedItem(getSpear()); }

  /**
   * @return the test spear
   */
  @Override
  public Spear getSpear() {
    return spear;
  }

  @Override
  @Test
  public void equipStaffTest() { checkEquippedItem(getStaff()); }

  /**
   * @return the test staff
   */
  @Override
  public Staff getStaff() {
    return staff;
  }

  @Override
  @Test
  public void equipBowTest() { checkEquippedItem(getBow()); }

  /**
   * @return the test bow
   */
  @Override
  public Bow getBow() { return bow; }

  @Override
  @Test
  public void equipDarkBookTest() { checkEquippedItem(getDarkBook()); }

  /**
   * @return the test dark book
   */
  @Override
  public DarkBook getDarkBook() {return darkBook; }

  @Override
  @Test
  public void equipLightBookTest() { checkEquippedItem(getLightBook()); }

  /**
   * @return the test light book
   */
  @Override
  public LightBook getLightBook() { return lightBook; }

  @Override
  @Test
  public void equipSoulBookTest() { checkEquippedItem(getSoulBook()); }

  /**
   * @return the test soul book
   */
  @Override
  public SoulBook getSoulBook() { return soulBook; }

  /**
   * Checks if the unit moves correctly
   */
  @Override
  @Test
  public void testMovement() {
    getTestUnit().moveTo(getField().getCell(2, 2));
    assertEquals(new Location(0, 0), getTestUnit().getLocation());

    getTestUnit().moveTo(getField().getCell(0, 2));
    assertEquals(new Location(0, 2), getTestUnit().getLocation());

    getField().getCell(0, 1).setUnit(getTargetAlpaca());
    getTestUnit().moveTo(getField().getCell(0, 1));
    assertEquals(new Location(0, 2), getTestUnit().getLocation());
  }

  /**
   * @return the test field
   */
  @Override
  public Field getField() {
    return field;
  }

  /**
   * @return the target Alpaca
   */
  @Override
  public Alpaca getTargetAlpaca() {
    return targetAlpaca;
  }

  @Override
  @Test
  public void testAddRemoveItem() {
    IUnit unit = getTestUnit();
    assertTrue(unit.getItems().isEmpty());
    IEquipableItem item1 = getAxe();
    IEquipableItem item2 = getBow();
    IEquipableItem item3 = getSpear();
    IEquipableItem item4 = getStaff();
    unit.addItem(item1);
    unit.addItem(item2);
    unit.addItem(item3);
    assertTrue(unit.getItems().contains(item1));
    assertTrue(unit.getItems().contains(item2));
    assertTrue(unit.getItems().contains(item3));
    assertEquals(unit.getItems().size(), 3);
    if (unit.getMaxItems() == 3) {
      unit.addItem(item4);
      assertEquals(unit.getItems().size(), 3);
    }
    unit.removeItem(item1);
    assertEquals(unit.getItems().size(), 2);
    assertFalse(unit.getItems().contains(item1));
  }

  @Override
  @Test
  public void giveItemTest() {
    IUnit giverUnit = getTestUnit();
    IEquipableItem item = getAxe();
    giverUnit.addItem(item);
    giverUnit.equipItem(item);

    setTargetAlpaca();
    assertTrue(targetAlpaca.getItems().isEmpty());
    giverUnit.giveItemTo(item, targetAlpaca);

    assertEquals(targetAlpaca.getItems().size(), 1);
    assertEquals(item.getOwner(), targetAlpaca);
    assertTrue(targetAlpaca.getItems().contains(item));

    assertFalse(giverUnit.getItems().contains(item));
    assertEquals(giverUnit.getItems().size(), 0);
  }
}