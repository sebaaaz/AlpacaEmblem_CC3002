package model.units;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

/**
 * Test set for the Hero unit.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class HeroTest extends AbstractTestUnit {

  private Hero hero;

  /**
   * Set up the main unit that's going to be tested in the test set
   */
  @Override
  public void setTestUnit() {
    hero = new Hero(50, 2, field.getCell(0, 0));
  }

  /**
   * @return the current unit being tested
   */
  @Override
  public IUnit getTestUnit() {
    return hero;
  }

  @Override
  @Test
  public void equipSpearTest() {
    assertNull(hero.getEquippedItem());
    hero.equipItem(spear);
    assertNull(hero.getEquippedItem());
    hero.addItem(spear);
    hero.equipItem(spear);
    assertEquals(spear, hero.getEquippedItem());
    hero.unequipItem();
    assertNull(hero.getEquippedItem());
  }

  @Override
  @Test
  public void receiveAttacksTest() {
    IUnit unit = hero;
    unit.addItem(getSpear());
    unit.equipSpear(getSpear());
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

    getSword().useAgainst(unit);
    assertEquals(unit.getCurrentHitPoints(), 50);

    getAxe().useAgainst(unit);
    assertEquals(unit.getCurrentHitPoints(), 35);
  }
}