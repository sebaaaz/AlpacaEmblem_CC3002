package model.factories.itemFactories;

import model.items.IEquipableItem;
import model.items.SoulBook;

public class SoulBookFactory implements IEquipableItemFactory {

  @Override
  public IEquipableItem createItem() { return new SoulBook("Common Soul Book", 5, 3, 5); }

  @Override
  public IEquipableItem createFullCustomItem(String name, int power, int minRange, int maxRange) { return new SoulBook(name, power, minRange, maxRange); }

  @Override
  public IEquipableItem createCustomPowerItem(String name, int power) { return new SoulBook(name, power, 3, 5); }
}
