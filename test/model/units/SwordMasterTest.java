package model.units;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
    assertTrue(swordMaster.getEquippedItem().isNull());
    swordMaster.equipItem(sword);
    assertTrue(swordMaster.getEquippedItem().isNull());
    swordMaster.addItem(sword);
    swordMaster.equipItem(sword);
    assertEquals(sword, swordMaster.getEquippedItem());
    swordMaster.unequipItem();
    assertTrue(swordMaster.getEquippedItem().isNull());
  }

  @Override
  @Test
  public void receiveAttacksTest() {
    IUnit unit = swordMaster;
    unit.addItem(getSword());
    unit.equipSword(getSword());
    assertEquals(unit.getHitPoints(), 50);

    getBow().useAgainst(unit);
    assertEquals(unit.getHitPoints(), 40);
    getStaff().useAgainst(unit);
    assertEquals(unit.getHitPoints(), 50);

    getDarkBook().useAgainst(unit);
    assertEquals(unit.getHitPoints(), 35);

    getLightBook().useAgainst(unit);
    assertEquals(unit.getHitPoints(), 20);

    godStaff.useAgainst(unit);

    getSoulBook().useAgainst(unit);
    assertEquals(unit.getHitPoints(), 35);

    godStaff.useAgainst(unit);

    getAxe().useAgainst(unit);
    assertEquals(unit.getHitPoints(), 50);

    getSpear().useAgainst(unit);
    assertEquals(unit.getHitPoints(), 35);
  }
}