package model.items;

import model.map.Location;
import model.units.IUnit;
import model.units.Sorcerer;

/**
 * Test set for Dark Books
 *
 * @author Sebastián Zapata Ascencio
 * @since 1.0
 */
public class DarkBookTest extends AbstractTestItem {

  private DarkBook darkBook;
  private DarkBook wrongDarkBook;
  private Sorcerer sorcerer;

  @Override
  public void setTestItem() {
    expectedName = "Common dark book";
    expectedPower = 15;
    expectedMinRange = 3;
    expectedMaxRange = 5;
    darkBook = new DarkBook(expectedName, expectedPower, expectedMinRange, expectedMaxRange);
  }

  @Override
  public void setWrongRangeItem() { wrongDarkBook = new DarkBook("Wrong dark book", 0, -1, -2); }

  @Override
  public void setTestUnit() { sorcerer = new Sorcerer(10, 5, new Location(0, 0)); }

  @Override
  public IEquipableItem getWrongTestItem() { return wrongDarkBook; }

  @Override
  public IEquipableItem getTestItem() { return darkBook; }

  @Override
  public IUnit getTestUnit() { return sorcerer; }
}
