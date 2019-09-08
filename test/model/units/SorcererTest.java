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
    assertEquals(darkBook, sorcerer.getEquippedItem());
    sorcerer.unequipItem();
    assertNull(sorcerer.getEquippedItem());
  }

  @Test
  @Override
  public void equipLightBookTest() {
    assertNull(sorcerer.getEquippedItem());
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
    assertEquals(soulBook, sorcerer.getEquippedItem());
    sorcerer.unequipItem();
    assertNull(sorcerer.getEquippedItem());
  }
}