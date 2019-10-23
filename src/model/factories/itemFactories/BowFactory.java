package model.factories.itemFactories;

import model.items.Bow;
import model.items.IEquipableItem;

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
