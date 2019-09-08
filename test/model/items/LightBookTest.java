package model.items;

import model.map.Location;
import model.units.IUnit;
import model.units.Sorcerer;

/**
 * Test set for Light Books
 *
 * @author Sebastián Zapata Ascencio
 * @since 1.0
 */
public class LightBookTest extends AbstractTestItem {

  private LightBook lightBook;
  private LightBook wrongLightBook;
  private Sorcerer sorcerer;

  /**
   * Sets which item is going to be tested
   */
  @Override
  public void setTestItem() {
    expectedName = "Common light book";
    expectedPower = 15;
    expectedMinRange = 3;
    expectedMaxRange = 5;
    lightBook = new LightBook(expectedName, expectedPower, expectedMinRange, expectedMaxRange);
  }

  @Override
  public void setWrongRangeItem() { wrongLightBook = new LightBook("Wrong light book", 0, -1, -2); }

  @Override
  public void setTestUnit() { sorcerer = new Sorcerer(10, 5, new Location(0, 0)); }

  @Override
  public IEquipableItem getWrongTestItem() { return wrongLightBook; }

  @Override
  public IEquipableItem getTestItem() { return lightBook; }

  @Override
  public IUnit getTestUnit() { return sorcerer; }
}
