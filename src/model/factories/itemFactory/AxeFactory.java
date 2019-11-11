package model.factories.itemFactory;

import model.items.Axe;
import model.items.IEquipableItem;

/**
 * This class represents an <i>Axe Factory</i> type factory.
 * <p>
 * They only return <i>Axe</i> items.
 *
 * @author Sebastián Zapata Ascencio
 * @since 2.0
 */
public class AxeFactory implements IEquipableItemFactory {

  @Override
  public IEquipableItem createItem() {
    return new Axe("Common Axe", 12, 1, 2);
  }

  @Override
  public IEquipableItem createFullCustomItem(String name, int power, int minRange, int maxRange) {
    return new Axe(name, power, minRange, maxRange);
  }

  @Override
  public IEquipableItem createCustomPowerItem(String name, int power) {
    return new Axe(name, power, 1, 2);
  }
}
