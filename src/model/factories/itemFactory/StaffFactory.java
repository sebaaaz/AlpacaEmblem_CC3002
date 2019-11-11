package model.factories.itemFactory;

import model.items.IEquipableItem;
import model.items.Staff;

/**
 * This class represents an <i>Staff Factory</i> type factory.
 * <p>
 * They only return <i>Staff</i> items.
 *
 * @author Sebastián Zapata Ascencio
 * @since 2.0
 */
public class StaffFactory implements IEquipableItemFactory {

  @Override
  public IEquipableItem createItem() {
    return new Staff("Common Staff", 12, 1, 2);
  }

  @Override
  public IEquipableItem createFullCustomItem(String name, int power, int minRange, int maxRange) {
    return new Staff(name, power, minRange, maxRange);
  }

  @Override
  public IEquipableItem createCustomPowerItem(String name, int power) {
    return new Staff(name, power, 1, 2);
  }
}
