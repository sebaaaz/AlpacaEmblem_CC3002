package model.units;


import model.items.*;
import model.map.Field;
import org.junit.jupiter.api.Test;

/**
 * Interface that defines the common behaviour of all the test for the units classes
 *
 * @author Ignacio Slater Muñoz
 * @author Sebastián Zapata Ascencio
 * @since 1.0
 */
public interface ITestUnit {

  /**
   * Set up the game field
   */
  void setField();

  /**
   * Sets up the main unit that's going to be tested in the test set
   */
  void setTestUnit();

  /**
   * Sets up a targetAlpaca for interaction with other objects.
   */
  void setTargetAlpaca();
  /**
   * Sets up a second targetAlpaca for interaction with other objects.
   */
  void setTargetAlpaca2();

  /**
   * Creates a set of testing weapons
   */
  void setWeapons();

  /**
   * Checks that the constructor works properly.
   */
  @Test
  void constructorTest();

  /**
   * @return the current unit being tested
   */
  IUnit getTestUnit();

  /**
   * Checks if the axe is equipped correctly to the unit
   */
  @Test
  void equipAxeTest();

  /**
   * Tries to equip a weapon to the alpaca and verifies that it was not equipped
   *
   * @param item
   *     to be equipped
   */
  void checkEquippedItem(IEquipableItem item);

  /**
   * @return the test axe
   */
  Axe getAxe();

  /**
   * Tests the equipment of a <i>sword</i> item.
   */
  @Test
  void equipSwordTest();

  /**
   * @return the test sword
   */
  Sword getSword();

  /**
   * Tests the equipment of a <i>spear</i> item.
   */
  @Test
  void equipSpearTest();

  /**
   * @return the test spear
   */
  Spear getSpear();

  /**
   * Tests the equipment of a <i>Staff</i> item.
   */
  @Test
  void equipStaffTest();

  /**
   * @return the test staff
   */
  Staff getStaff();

  /**
   * Tests the equipment of a <i>Bow</i> item.
   */
  @Test
  void equipBowTest();

  /**
   * @return the test bow
   */
  Bow getBow();

  /**
   * Tests the equipment of a <i>DarkBook</i> item.
   */
  @Test
  void equipDarkBookTest();

  /**
   * @return the test dark book
   */
  DarkBook getDarkBook();

  /**
   * Tests the equipment of a <i>LightBook</i> item.
   */
  @Test
  void equipLightBookTest();

  /**
   * @return the test light book
   */
  LightBook getLightBook();

  /**
   * Tests the equipment of a <i>SoulBook</i> item.
   */
  @Test
  void equipSoulBookTest();

  /**
   * @return the test soul book
   */
  SoulBook getSoulBook();

  /**
   * Checks if the unit moves correctly
   */
  @Test
  void testMovement();

  /**
   * @return the test field
   */
  Field getField();

  /**
   * @return the target Alpaca
   */
  Alpaca getTargetAlpaca();

  /**
   * @return the target Alpaca 2
   */
  Alpaca getTargetAlpaca2();

  /**
   * Checks if the item was added correctly to the item list.
   */
  @Test
  void testAddRemoveItem();

  /**
   * Checks if the item will be given to the unit. The receiver
   * unit will be targetAlpaca.
   */
  @Test
  void giveItemTest();

  /**
   * Checks if the alpaca received all objects.
   *
   * @param giverUnit
   *      the unit that will give some item to the alpaca.
   * @param item
   *      the item to be given.
   */
  void alpacaReceivesItemTest(IUnit giverUnit, IEquipableItem item);

  /**
   * Checks if the item was not given to the target alpaca because
   * both units were more than 1 of distance.
   */
  @Test
  void wrongDistanceGiveItemTest();

  /**
   * Checks if the unit received the correct damage or heal.
   */
  @Test
  void receiveAttacksTest();

  /**
   * Checks if the limit of the current life of a unit is setted
   * 50 for its maximum (which is 50 for test units).
   */
  @Test
  void bigAttackTest();

  /**
   * Checks the unit receives normal damage because it does not have
   * equipped any item
   */
  @Test
  void receiveAttackWithoutEquippedItem();

  /**
   * Tests of simple combat with counter attack
   */
  @Test
  void simpleCombatTest();

  /**
   * Tests some methods of Null unit and Null item.
   */
  @Test
  void nullTest();

  /**
   * Tests the death of an unit.
   */
  @Test
  void deathTest();
}
