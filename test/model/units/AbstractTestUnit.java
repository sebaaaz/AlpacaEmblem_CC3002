package model.units;

import static model.units.NullUnit.NULL_UNIT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import model.items.*;
import model.map.Field;
import model.map.InvalidLocation;
import model.map.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Ignacio Slater Muñoz
 * @author Sebastián Zapata Ascencio
 * @since 1.0
 */
public abstract class AbstractTestUnit implements ITestUnit {

  protected Field field;
  private Alpaca targetAlpaca;
  private Alpaca targetAlpaca2;
  private Bow mortalBow;
  Bow bow;
  Axe axe;
  Sword sword;
  Staff staff;
  Spear spear;
  DarkBook darkBook;
  LightBook lightBook;
  SoulBook soulBook;
  Staff godStaff;
  private NullItem nullItem;
  private NullUnit nullUnit;

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
    nullUnit = NULL_UNIT;
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
    this.nullItem = new NullItem(nullUnit);
  }

  /**
   * Checks that the constructor works properly.
   */
  @Override
  @Test
  public void constructorTest() {
    assertEquals(50, getTestUnit().getHitPoints());
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
    assertTrue(getTestUnit().getEquippedItem().isNull());
    getTestUnit().equipItem(item);
    assertTrue(getTestUnit().getEquippedItem().isNull());
    getTestUnit().unequipItem();
    assertTrue(getTestUnit().getEquippedItem().isNull());
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
    unit.setHitPoints(1);
    assertEquals(1, unit.getHitPoints());
    godStaff.sendItemTypeAttack(unit); // 10000 of power
    assertEquals(50, unit.getHitPoints()); // max hp
  }

  @Override
  @Test
  public void receiveAttackWithoutEquippedItem() {
    IUnit unit = getTestUnit();
    assertTrue(unit.getEquippedItem().isNull());
    getAxe().sendItemTypeAttack(unit);
    assertEquals(40, unit.getHitPoints());
    getBow().sendItemTypeAttack(unit);
    assertEquals(30, unit.getHitPoints());
    getDarkBook().sendItemTypeAttack(unit);
    assertEquals(20, unit.getHitPoints());
    getLightBook().sendItemTypeAttack(unit);
    assertEquals(10, unit.getHitPoints());
    godStaff.sendItemTypeAttack(unit);
    assertEquals(50, unit.getHitPoints());
    getSpear().sendItemTypeAttack(unit);
    assertEquals(40, unit.getHitPoints());
    getSword().sendItemTypeAttack(unit);
    assertEquals(30, unit.getHitPoints());
    getSoulBook().sendItemTypeAttack(unit);
    assertEquals(20, unit.getHitPoints());
  }

  @Override
  @Test
  public void simpleCombatTest() {
    Hero hero = new Hero(50, 2, field.getCell(0, 0));
    SwordMaster swordMaster = new SwordMaster(50, 2, field.getCell(0, 1));
    Cleric cleric = new Cleric(50, 2, field.getCell(1, 0));
    assertEquals(50, hero.getHitPoints());
    assertEquals(50, swordMaster.getHitPoints());
    assertEquals(50, cleric.getHitPoints());

    hero.addItem(getSpear());
    hero.equipSpear(getSpear());
    swordMaster.addItem(getSword());
    swordMaster.equipSword(getSword());
    cleric.addItem(getStaff());
    cleric.equipStaff(getStaff());

    hero.attack(swordMaster);
    assertEquals(35, swordMaster.getHitPoints());
    assertEquals(50, hero.getHitPoints());

    swordMaster.attack(cleric);
    assertEquals(35, swordMaster.getHitPoints());
    assertEquals(40, cleric.getHitPoints());

    cleric.attack(swordMaster);
    assertEquals(45, swordMaster.getHitPoints());
    assertEquals(40, cleric.getHitPoints());
  }

  @Override
  @Test
  public void nullTest() {
    // Null Unit
    IUnit testUnit = new SwordMaster(50, 1, new InvalidLocation());
    testUnit.addItem(sword);
    testUnit.giveItemTo(sword, nullUnit);
    testUnit.equipItem(sword);

    assertTrue(nullUnit.isNull());

    assertEquals(0, nullUnit.getItems().size());
    nullUnit.addItem(sword);
    assertEquals(0, nullUnit.getItems().size());
    assertEquals(1, testUnit.getItems().size());

    assertTrue(nullUnit.getEquippedItem().isNull());
    nullUnit.setEquippedItem(nullItem);
    nullUnit.equipItem(nullItem);
    assertTrue(nullUnit.getEquippedItem().isNull());

    nullUnit.setHitPoints(42);
    assertEquals(1, nullUnit.getHitPoints());

    nullUnit.moveTo(new Location(0,0));
    nullUnit.setLocation(new Location(0,0));
    assertEquals(-1, nullUnit.getLocation().getRow());
    assertEquals(-1, nullUnit.getLocation().getColumn());

    testUnit.attack(nullUnit);
    assertEquals(1, nullUnit.getHitPoints());

    assertEquals(50, testUnit.getHitPoints());
    nullUnit.attack(getTestUnit());
    assertEquals(50, testUnit.getHitPoints());

    // Null Item
    getTestUnit().unequipItem();
    getTestUnit().attack(testUnit);
    assertEquals(50, testUnit.getHitPoints());

    assertEquals(sword, testUnit.getEquippedItem());
    assertTrue(getTestUnit().getEquippedItem().isNull());

    testUnit.attack(getTestUnit());
    assertEquals(40, getTestUnit().getHitPoints());
    assertEquals(50, testUnit.getHitPoints());

    nullItem.equipTo(testUnit);
    assertTrue(testUnit.getEquippedItem().isNull());

    IUnit nullUnit1 = NULL_UNIT;
    IUnit nullUnit2 = NULL_UNIT;
    assertEquals(nullUnit1, nullUnit2);
  }

  @Test
  @Override
  public void deathTest() {
    IUnit unit = getTestUnit();
    assertEquals(50, unit.getHitPoints());
    assertEquals(0, unit.getLocation().getColumn());
    assertEquals(0, unit.getLocation().getRow());
    mortalBow.sendItemTypeAttack(unit);
    assertEquals(0, unit.getHitPoints());
    assertEquals(-1, unit.getLocation().getRow());
    assertEquals(-1, unit.getLocation().getColumn());
  }
}