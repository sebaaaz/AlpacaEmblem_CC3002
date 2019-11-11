package model.factories.itemFactory;

import model.items.Bow;
import model.items.IEquipableItem;

/**
 * This class represents a <i>Bow Factory</i> type factory.
 * <p>
 * They only return <i>Bow</i> items.
 *
 * @author Sebastián Zapata Ascencio
 * @since 2.0
 */
public class BowFactory implements IEquipableItemFactory {

  @Override
  public IEquipableItem createItem() { return new Bow("Common Bow", 8, 2, 4); }

  @Override
  public IEquipableItem createFullCustomItem(String name, int power, int minRange, int maxRange) {
    return new Bow(name, power, minRange, maxRange);
  }

  @Override
  public IEquipableItem createCustomPowerItem(String name, int power) {
    return new Bow(name, power, 2, 4);
  }
}
