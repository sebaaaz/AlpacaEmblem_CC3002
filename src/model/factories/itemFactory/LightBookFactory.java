package model.factories.itemFactory;

import model.items.IEquipableItem;
import model.items.LightBook;

/**
 * This class represents a <i>LightBook Factory</i> type factory.
 * <p>
 * They only return <i>LightBook</i> items.
 *
 * @author Sebastián Zapata Ascencio
 * @since 2.0
 */
public class LightBookFactory implements IEquipableItemFactory {

  @Override
  public IEquipableItem createItem() { return new LightBook("Common Light Book", 5, 3, 5); }

  @Override
  public IEquipableItem createFullCustomItem(String name, int power, int minRange, int maxRange) { return new LightBook(name, power, minRange, maxRange); }

  @Override
  public IEquipableItem createCustomPowerItem(String name, int power) { return new LightBook(name, power, 3, 5); }
}
