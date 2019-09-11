package model.units;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

/**
 * @author Ignacio Slater Muñoz
 */
public class ClericTest extends AbstractTestUnit {

  private Cleric cleric;

  /**
   * Set up the main unit that's going to be tested in the test set
   */
  @Override
  public void setTestUnit() {
    cleric = new Cleric(50, 2, field.getCell(0, 0));
  }

  /**
   * @return the current unit being tested
   */
  @Override
  public IUnit getTestUnit() {
    return cleric;
  }

  @Test
  @Override
  public void equipStaffTest() {
    assertNull(cleric.getEquippedItem());
    cleric.equipItem(staff);
    assertNull(cleric.getEquippedItem());
    cleric.addItem(staff);
    cleric.equipItem(staff);
    assertEquals(staff, cleric.getEquippedItem());
    cleric.unequipItem();
    assertNull(cleric.getEquippedItem());
  }

  @Override
  @Test
  public void receiveAttacksTest() {
    IUnit unit = cleric;
    assertEquals(unit.getCurrentHitPoints(), 50);
    getDarkBook().useAgainst(unit);
    assertEquals(unit.getCurrentHitPoints(), 40);
    getStaff().useAgainst(unit);
    assertEquals(unit.getCurrentHitPoints(), 50);

    getDarkBook().useAgainst(unit);
    assertEquals(unit.getCurrentHitPoints(), 40);
    unit.addItem(getStaff());
    unit.equipStaff(getStaff());
    getDarkBook().useAgainst(unit);
    assertEquals(unit.getCurrentHitPoints(), 25);
  }
}