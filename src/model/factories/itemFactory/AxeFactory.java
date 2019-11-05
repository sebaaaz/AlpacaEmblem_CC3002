package model.factories.itemFactory;

import model.items.Axe;
import model.items.IEquipableItem;

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
