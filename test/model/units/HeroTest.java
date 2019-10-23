package model.units;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
    assertTrue(hero.getEquippedItem().isNull());
    hero.equipItem(spear);
    assertTrue(hero.getEquippedItem().isNull());
    hero.addItem(spear);
    hero.equipItem(spear);
    assertEquals(spear, hero.getEquippedItem());
    hero.unequipItem();
    assertTrue(hero.getEquippedItem().isNull());
  }

  @Override
  @Test
  public void receiveAttacksTest() {
    IUnit unit = hero;
    unit.addItem(getSpear());
    unit.equipSpear(getSpear());
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

    getSword().useAgainst(unit);
    assertEquals(unit.getHitPoints(), 50);

    getAxe().useAgainst(unit);
    assertEquals(unit.getHitPoints(), 35);
  }
}