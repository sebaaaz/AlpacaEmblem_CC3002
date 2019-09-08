package model.items;

import model.map.Location;
import model.units.IUnit;
import model.units.Sorcerer;

/**
 * Test set for Soul Books
 *
 * @author Sebastián Zapata Ascencio
 * @since 1.0
 */
public class SoulBookTest extends AbstractTestItem {

  private SoulBook soulBook;
  private SoulBook wrongSoulBook;
  private Sorcerer sorcerer;

  /**
   * Sets which item is going to be tested
   */
  @Override
  public void setTestItem() {
    expectedName = "Common soul book";
    expectedPower = 15;
    expectedMinRange = 3;
    expectedMaxRange = 5;
    soulBook = new SoulBook(expectedName, expectedPower, expectedMinRange, expectedMaxRange);
  }

  @Override
  public void setWrongRangeItem() { wrongSoulBook = new SoulBook("Wrong soul book", 0, -1, -2); }

  @Override
  public void setTestUnit() { sorcerer = new Sorcerer(10, 5, new Location(0, 0)); }

  @Override
  public IEquipableItem getWrongTestItem() { return wrongSoulBook; }

  @Override
  public IEquipableItem getTestItem() { return soulBook; }

  @Override
  public IUnit getTestUnit() { return sorcerer; }
}
