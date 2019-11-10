package model.units;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
    assertTrue(sorcerer.getEquippedItem().isNull());
    sorcerer.equipItem(darkBook);
    assertTrue(sorcerer.getEquippedItem().isNull());
    sorcerer.addItem(darkBook);
    sorcerer.equipItem(darkBook);
    assertEquals(darkBook, sorcerer.getEquippedItem());
    sorcerer.unequipItem();
    assertTrue(sorcerer.getEquippedItem().isNull());
  }

  @Test
  @Override
  public void equipLightBookTest() {
    assertTrue(sorcerer.getEquippedItem().isNull());
    sorcerer.equipItem(lightBook);
    assertTrue(sorcerer.getEquippedItem().isNull());
    sorcerer.addItem(lightBook);
    sorcerer.equipItem(lightBook);
    assertEquals(lightBook, sorcerer.getEquippedItem());
    sorcerer.unequipItem();
    assertTrue(sorcerer.getEquippedItem().isNull());
  }

  @Test
  @Override
  public void equipSoulBookTest() {
    assertTrue(sorcerer.getEquippedItem().isNull());
    sorcerer.equipItem(soulBook);
    assertTrue(sorcerer.getEquippedItem().isNull());
    sorcerer.addItem(soulBook);
    sorcerer.equipItem(soulBook);
    assertEquals(soulBook, sorcerer.getEquippedItem());
    sorcerer.unequipItem();
    assertTrue(sorcerer.getEquippedItem().isNull());
  }

  @Override
  @Test
  public void receiveAttacksTest() {
    IUnit unit = sorcerer;
    assertEquals(unit.getHitPoints(), 50);
    unit.addItem(getDarkBook());
    unit.addItem(getLightBook());
    unit.addItem(getSoulBook());

    unit.equipMagicBook(getDarkBook());

    getBow().sendItemTypeAttack(unit);
    assertEquals(unit.getHitPoints(), 35);
    getStaff().sendItemTypeAttack(unit);
    assertEquals(unit.getHitPoints(), 45);
    godStaff.sendItemTypeAttack(unit);

    getDarkBook().sendItemTypeAttack(unit);
    assertEquals(unit.getHitPoints(), 40);
    getSoulBook().sendItemTypeAttack(unit);
    assertEquals(unit.getHitPoints(), 40);
    getLightBook().sendItemTypeAttack(unit);
    assertEquals(unit.getHitPoints(), 25);
    godStaff.sendItemTypeAttack(unit);

    unit.equipMagicBook(getLightBook());
    getDarkBook().sendItemTypeAttack(unit);
    assertEquals(unit.getHitPoints(), 50);
    getSoulBook().sendItemTypeAttack(unit);
    assertEquals(unit.getHitPoints(), 35);
    getLightBook().sendItemTypeAttack(unit);
    assertEquals(unit.getHitPoints(), 25);
    godStaff.sendItemTypeAttack(unit);

    unit.equipMagicBook(getSoulBook());
    getDarkBook().sendItemTypeAttack(unit);
    assertEquals(unit.getHitPoints(), 35);
    getSoulBook().sendItemTypeAttack(unit);
    assertEquals(unit.getHitPoints(), 25);
    getLightBook().sendItemTypeAttack(unit);
    assertEquals(unit.getHitPoints(), 25);
  }
}