package model.units;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import model.items.Spear;
import model.items.Staff;
import org.junit.jupiter.api.Test;

/**
 * Test set for the Archer unit.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class ArcherTest extends AbstractTestUnit {

  private Archer archer;

  /**
   * Set up the main unit that's going to be tested in the test set
   */
  @Override
  public void setTestUnit() {
    archer = new Archer(50, 2, field.getCell(0, 0));
  }

  /**
   * @return the current unit being tested
   */
  @Override
  public IUnit getTestUnit() {
    return archer;
  }

  /**
   * Checks if the bow is equipped correctly to the unit
   */
  @Test
  @Override
  public void equipBowTest() {
    assertNull(archer.getEquippedItem());
    archer.equipItem(bow);
    assertNull(archer.getEquippedItem());
    archer.addItem(bow);
    archer.equipItem(bow);
    assertEquals(bow, archer.getEquippedItem());
    archer.unequipItem();
    assertNull(archer.getEquippedItem());
  }



  @Override
  @Test
  public void receiveAttacksTest() {
    IUnit unit = archer;
    assertEquals(unit.getCurrentHitPoints(), 50);
    getBow().useAgainst(unit);
    assertEquals(unit.getCurrentHitPoints(), 40);
    getStaff().useAgainst(unit);
    assertEquals(unit.getCurrentHitPoints(), 50);

    getDarkBook().useAgainst(unit);
    assertEquals(unit.getCurrentHitPoints(), 40);
    unit.addItem(getBow());
    unit.equipBow(getBow());
    getDarkBook().useAgainst(unit);
    assertEquals(unit.getCurrentHitPoints(), 25);
  }
}