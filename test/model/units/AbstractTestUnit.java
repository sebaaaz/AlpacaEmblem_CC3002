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
  protected Alpaca targetAlpaca2;
  protected Bow bow;
  protected Field field;
  protected Axe axe;
  protected Sword sword;
  protected Staff staff;
  protected Spear spear;
  protected DarkBook darkBook;
  protected LightBook lightBook;
  protected SoulBook soulBook;
  protected Bow mortalBow;
  protected Staff godStaff;

  @Override
  public void setTargetAlpaca() {
    targetAlpaca = new Alpaca(50, 2, field.getCell(1, 0));
  }
  public void setTargetAlpaca2() {
    targetAlpaca2 = new Alpaca(50, 2, field.getCell(2, 0));
  }

  /**
   * Sets up the units and weapons to be tested
   */
  @BeforeEach
  public void setUp() {
    setField();
    setTestUnit();
    setTargetAlpaca();
    setTargetAlpaca2();
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
    this.darkBook = new DarkBook("DarkBook", 10, 2, 4);
    this.lightBook = new LightBook("LightBook", 10, 2, 4);
    this.soulBook = new SoulBook("SoulBook", 10, 2, 4);
    this.mortalBow = new Bow("Mortal Bow", 10000, 1, 2);
    this.godStaff = new Staff("Staff of gods", 10000, 1, 2);
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

  /**
   * @return the target Alpaca 2
   */
  @Override
  public Alpaca getTargetAlpaca2() {
    return targetAlpaca2;
  }

  @Override
  @Test
  public void testAddRemoveItem() {
    IUnit unit = getTestUnit();
    assertTrue(unit.getItems().isEmpty());
    unit.addItem(getAxe());
    unit.addItem(getBow());
    unit.addItem(getSpear());
    assertTrue(unit.getItems().contains(getAxe()));
    assertTrue(unit.getItems().contains(getBow()));
    assertTrue(unit.getItems().contains(getSpear()));
    assertEquals(unit.getItems().size(), 3);
    if (unit.getMaxItems() == 3) {
      unit.addItem(getStaff());
      assertEquals(unit.getItems().size(), 3);
    }
    unit.removeItem(getAxe());
    assertEquals(unit.getItems().size(), 2);
    assertFalse(unit.getItems().contains(getAxe()));
  }

  @Override
  public void alpacaReceivesItemTest(IUnit giverUnit, IEquipableItem item) {
    setTargetAlpaca();
    giverUnit.giveItemTo(item, targetAlpaca);
    assertEquals(targetAlpaca.getItems().size(), 1);
    assertEquals(item.getOwner(), targetAlpaca);
    assertTrue(targetAlpaca.getItems().contains(item));
    assertFalse(giverUnit.getItems().contains(item));
    assertEquals(giverUnit.getItems().size(), 0);
  }

  @Override
  @Test
  public void giveItemTest() {
    IUnit giverUnit = getTestUnit();

    giverUnit.addItem(getAxe());
    giverUnit.equipItem(getAxe());
    alpacaReceivesItemTest(giverUnit, getAxe());

    giverUnit.addItem(getBow());
    giverUnit.equipItem(getBow());
    alpacaReceivesItemTest(giverUnit, getBow());

    giverUnit.addItem(getDarkBook());
    giverUnit.equipItem(getDarkBook());
    alpacaReceivesItemTest(giverUnit, getDarkBook());

    giverUnit.addItem(getLightBook());
    giverUnit.equipItem(getLightBook());
    alpacaReceivesItemTest(giverUnit, getLightBook());

    giverUnit.addItem(getSoulBook());
    giverUnit.equipItem(getSoulBook());
    alpacaReceivesItemTest(giverUnit, getSoulBook());

    giverUnit.addItem(getSpear());
    giverUnit.equipItem(getSpear());
    alpacaReceivesItemTest(giverUnit, getSpear());

    giverUnit.addItem(getStaff());
    giverUnit.equipItem(getStaff());
    alpacaReceivesItemTest(giverUnit, getStaff());

    giverUnit.addItem(getSword());
    giverUnit.equipItem(getSword());
    alpacaReceivesItemTest(giverUnit, getSword());
  }

  @Override
  @Test
  public void wrongDistanceGiveItemTest() {
    IUnit giverUnit = getTestUnit();
    setTargetAlpaca();
    assertFalse(getTargetAlpaca().getItems().contains(getBow()));
    getTargetAlpaca().moveTo(getField().getCell(1, 1));
    giverUnit.addItem(getBow());
    giverUnit.giveItemTo(getBow(), getTargetAlpaca());
    assertFalse(getTargetAlpaca().getItems().contains(getBow()));
  }

  @Override
  public void receiveAttacksTest() {}

  @Override
  @Test
  public void bigAttackTest() {
    IUnit unit = getTestUnit();
    mortalBow.useAgainst(unit);
    assertEquals(unit.getCurrentHitPoints(), 0);
    godStaff.useAgainst(unit);
    assertEquals(unit.getCurrentHitPoints(), 50);
  }

  @Override
  @Test
  public void receiveAttackWithoutEquippedItem() {
    IUnit unit = getTestUnit();
    unit.equipItem(null);
    getAxe().useAgainst(unit);
    assertEquals(40, unit.getCurrentHitPoints());
    getBow().useAgainst(unit);
    assertEquals(30, unit.getCurrentHitPoints());
    getDarkBook().useAgainst(unit);
    assertEquals(20, unit.getCurrentHitPoints());
    getLightBook().useAgainst(unit);
    assertEquals(10, unit.getCurrentHitPoints());
    getSoulBook().useAgainst(unit);
    assertEquals(0, unit.getCurrentHitPoints());
    godStaff.useAgainst(unit);
    getSpear().useAgainst(unit);
    assertEquals(40, unit.getCurrentHitPoints());
    getSword().useAgainst(unit);
    assertEquals(30, unit.getCurrentHitPoints());
  }

  @Override
  @Test
  public void simpleCombatTest() {
    Hero hero = new Hero(50, 2, field.getCell(0, 0));
    SwordMaster swordMaster = new SwordMaster(50, 2, field.getCell(0, 1));
    Cleric cleric = new Cleric(50, 2, field.getCell(1, 0));
    assertEquals(hero.getCurrentHitPoints(), 50);
    assertEquals(swordMaster.getCurrentHitPoints(), 50);
    assertEquals(cleric.getCurrentHitPoints(), 50);

    hero.addItem(getSpear());
    hero.equipSpear(getSpear());
    swordMaster.addItem(getSword());
    swordMaster.equipSword(getSword());
    cleric.addItem(getStaff());
    cleric.equipStaff(getStaff());

    hero.startCombat(swordMaster);
    assertEquals(swordMaster.getCurrentHitPoints(), 35);
    assertEquals(hero.getCurrentHitPoints(), 50);

    swordMaster.startCombat(cleric);
    assertEquals(cleric.getCurrentHitPoints(), 40);
    assertEquals(swordMaster.getCurrentHitPoints(), 35);

    cleric.startCombat(swordMaster);
    assertEquals(swordMaster.getCurrentHitPoints(), 45);
    assertEquals(cleric.getCurrentHitPoints(), 40);
  }
}