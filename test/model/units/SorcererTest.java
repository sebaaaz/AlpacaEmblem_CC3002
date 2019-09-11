package model.units;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

/**
 * Test set for the Archer unit.
 *
 * @author Sebastián Zapata Ascencio
 * @since 1.0
 */
public class SorcererTest extends AbstractTestUnit {

  private Sorcerer sorcerer;

  @Override
  public void setTestUnit() { sorcerer = new Sorcerer(50, 2, field.getCell(0,0)); }

  @Override
  public IUnit getTestUnit() { return sorcerer; }

  @Test
  @Override
  public void equipDarkBookTest() {
    assertNull(sorcerer.getEquippedItem());
    sorcerer.equipItem(darkBook);
    assertNull(sorcerer.getEquippedItem());
    sorcerer.addItem(darkBook);
    sorcerer.equipItem(darkBook);
    assertEquals(darkBook, sorcerer.getEquippedItem());
    sorcerer.unequipItem();
    assertNull(sorcerer.getEquippedItem());
  }

  @Test
  @Override
  public void equipLightBookTest() {
    assertNull(sorcerer.getEquippedItem());
    sorcerer.equipItem(lightBook);
    assertNull(sorcerer.getEquippedItem());
    sorcerer.addItem(lightBook);
    sorcerer.equipItem(lightBook);
    assertEquals(lightBook, sorcerer.getEquippedItem());
    sorcerer.unequipItem();
    assertNull(sorcerer.getEquippedItem());
  }

  @Test
  @Override
  public void equipSoulBookTest() {
    assertNull(sorcerer.getEquippedItem());
    sorcerer.equipItem(soulBook);
    assertNull(sorcerer.getEquippedItem());
    sorcerer.addItem(soulBook);
    sorcerer.equipItem(soulBook);
    assertEquals(soulBook, sorcerer.getEquippedItem());
    sorcerer.unequipItem();
    assertNull(sorcerer.getEquippedItem());
  }

  @Override
  @Test
  public void receiveAttacksTest() {
    IUnit unit = sorcerer;
    assertEquals(unit.getCurrentHitPoints(), 50);
    unit.addItem(getDarkBook());
    unit.addItem(getLightBook());
    unit.addItem(getSoulBook());

    unit.equipDarkBook(getDarkBook());

    getBow().useAgainst(unit);
    assertEquals(unit.getCurrentHitPoints(), 35);
    getStaff().useAgainst(unit);
    assertEquals(unit.getCurrentHitPoints(), 45);
    godStaff.useAgainst(unit);

    getDarkBook().useAgainst(unit);
    assertEquals(unit.getCurrentHitPoints(), 40);
    getSoulBook().useAgainst(unit);
    assertEquals(unit.getCurrentHitPoints(), 40);
    getLightBook().useAgainst(unit);
    assertEquals(unit.getCurrentHitPoints(), 25);
    godStaff.useAgainst(unit);

    unit.equipLightBook(getLightBook());
    getDarkBook().useAgainst(unit);
    assertEquals(unit.getCurrentHitPoints(), 50);
    getSoulBook().useAgainst(unit);
    assertEquals(unit.getCurrentHitPoints(), 35);
    getLightBook().useAgainst(unit);
    assertEquals(unit.getCurrentHitPoints(), 25);
    godStaff.useAgainst(unit);

    unit.equipSoulBook(getSoulBook());
    getDarkBook().useAgainst(unit);
    assertEquals(unit.getCurrentHitPoints(), 35);
    getSoulBook().useAgainst(unit);
    assertEquals(unit.getCurrentHitPoints(), 25);
    getLightBook().useAgainst(unit);
    assertEquals(unit.getCurrentHitPoints(), 25);
  }
}