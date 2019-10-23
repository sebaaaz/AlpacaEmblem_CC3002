package model.units;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
    assertTrue(cleric.getEquippedItem().isNull());
    cleric.equipItem(staff);
    assertTrue(cleric.getEquippedItem().isNull());
    cleric.addItem(staff);
    cleric.equipItem(staff);
    assertEquals(staff, cleric.getEquippedItem());
    cleric.unequipItem();
    assertTrue(cleric.getEquippedItem().isNull());
  }

  @Override
  @Test
  public void receiveAttacksTest() {
    IUnit unit = cleric;
    assertEquals(50, unit.getHitPoints());
    getDarkBook().useAgainst(unit);
    assertEquals(40, unit.getHitPoints());
    getStaff().useAgainst(unit);
    assertEquals(50, unit.getHitPoints());

    getDarkBook().useAgainst(unit);
    assertEquals(40, unit.getHitPoints());
    unit.addItem(getStaff());
    unit.equipStaff(getStaff());
    getDarkBook().useAgainst(unit);
    assertEquals(25, unit.getHitPoints());
  }
}