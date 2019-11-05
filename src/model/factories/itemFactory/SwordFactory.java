package model.factories.itemFactory;

import model.items.IEquipableItem;
import model.items.Sword;

public class SwordFactory implements IEquipableItemFactory {

  @Override
  public IEquipableItem createItem() {
    return new Sword("Common Sword", 10, 1, 1);
  }

  @Override
  public IEquipableItem createFullCustomItem(String name, int power, int minRange, int maxRange) {
    return new Sword(name, power, minRange, maxRange);
  }

  @Override
  public IEquipableItem createCustomPowerItem(String name, int power) {
    return new Sword(name, power, 1, 1);
  }
}
