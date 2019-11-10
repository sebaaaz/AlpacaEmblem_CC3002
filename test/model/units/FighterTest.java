package model.units;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Ignacio Slater Muñoz
 */
public class FighterTest extends AbstractTestUnit {

  private Fighter fighter;

  /**
   * Set up the main unit that's going to be tested in the test set
   */
  @Override
  public void setTestUnit() {
    fighter = new Fighter(50, 2, field.getCell(0, 0));
  }

  /**
   * @return the current unit being tested
   */
  @Override
  public IUnit getTestUnit() {
    return fighter;
  }

  /**
   * Checks if the axe is equipped correctly to the unit
   */
  @Test
  @Override
  public void equipAxeTest() {
    assertTrue(fighter.getEquippedItem().isNull());
    fighter.equipItem(axe);
    assertTrue(fighter.getEquippedItem().isNull());
    fighter.addItem(axe);
    fighter.equipItem(axe);
    assertEquals(axe, fighter.getEquippedItem());
    fighter.unequipItem();
    assertTrue(fighter.getEquippedItem().isNull());
  }

  @Override
  @Test
  public void receiveAttacksTest() {
    IUnit unit = fighter;
    unit.addItem(getAxe());
    unit.equipAxe(getAxe());
    assertEquals(unit.getHitPoints(), 50);

    getBow().sendItemTypeAttack(unit);
    assertEquals(unit.getHitPoints(), 40);
    getStaff().sendItemTypeAttack(unit);
    assertEquals(unit.getHitPoints(), 50);

    getDarkBook().sendItemTypeAttack(unit);
    assertEquals(unit.getHitPoints(), 35);

    getLightBook().sendItemTypeAttack(unit);
    assertEquals(unit.getHitPoints(), 20);

    godStaff.sendItemTypeAttack(unit);

    getSoulBook().sendItemTypeAttack(unit);
    assertEquals(unit.getHitPoints(), 35);

    godStaff.sendItemTypeAttack(unit);

    getSword().sendItemTypeAttack(unit);
    assertEquals(unit.getHitPoints(), 35);

    getSpear().sendItemTypeAttack(unit);
    assertEquals(unit.getHitPoints(), 35);
  }
}