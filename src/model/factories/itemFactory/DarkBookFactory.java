package model.factories.itemFactory;

import model.items.DarkBook;
import model.items.IEquipableItem;

/**
 * This class represents a <i>DarkBook Factory</i> type factory.
 * <p>
 * They only return <i>DarkBook</i> items.
 *
 * @author Sebastián Zapata Ascencio
 * @since 2.0
 */
public class DarkBookFactory implements IEquipableItemFactory{

  @Override
  public IEquipableItem createItem() {
    return new DarkBook("Common Dark Book", 5, 3, 5);
  }

  @Override
  public IEquipableItem createFullCustomItem(String name, int power, int minRange, int maxRange) {
    return new DarkBook(name, power, minRange, maxRange);
  }

  @Override
  public IEquipableItem createCustomPowerItem(String name, int power) {
    return new DarkBook(name, power, 3, 5);
  }
}
