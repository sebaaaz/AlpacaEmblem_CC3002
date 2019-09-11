package model.units;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * @author Ignacio Slater Muñoz
 */
public class SwordMasterTest extends AbstractTestUnit {

  private SwordMaster swordMaster;

  /**
   * Set up the main unit that's going to be tested in the test set
   */
  @Override
  public void setTestUnit() {
    swordMaster = new SwordMaster(50, 2, field.getCell(0, 0));
  }

  /**
   * @return the current unit being tested
   */
  @Override
  public IUnit getTestUnit() {
    return swordMaster;
  }

  @Override
  public void equipSwordTest() {
    assertNull(swordMaster.getEquippedItem());
    swordMaster.equipItem(sword);
    assertNull(swordMaster.getEquippedItem());
    swordMaster.addItem(sword);
    swordMaster.equipItem(sword);
    assertEquals(sword, swordMaster.getEquippedItem());
    swordMaster.unequipItem();
    assertNull(swordMaster.getEquippedItem());
  }

  @Override
  @Test
  public void receiveAttacksTest() {
    IUnit unit = swordMaster;
    unit.addItem(getSword());
    unit.equipSword(getSword());
    assertEquals(unit.getCurrentHitPoints(), 50);

    getBow().useAgainst(unit);
    assertEquals(unit.getCurrentHitPoints(), 40);
    getStaff().useAgainst(unit);
    assertEquals(unit.getCurrentHitPoints(), 50);

    getDarkBook().useAgainst(unit);
    assertEquals(unit.getCurrentHitPoints(), 35);

    getLightBook().useAgainst(unit);
    assertEquals(unit.getCurrentHitPoints(), 20);

    godStaff.useAgainst(unit);

    getSoulBook().useAgainst(unit);
    assertEquals(unit.getCurrentHitPoints(), 35);

    godStaff.useAgainst(unit);

    getAxe().useAgainst(unit);
    assertEquals(unit.getCurrentHitPoints(), 50);

    getSpear().useAgainst(unit);
    assertEquals(unit.getCurrentHitPoints(), 35);
  }
}