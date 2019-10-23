package model.factories.itemFactories;

import model.items.IEquipableItem;
import model.items.LightBook;

public class LightBookFactory implements IEquipableItemFactory {

  @Override
  public IEquipableItem createItem() { return new LightBook("Common Light Book", 5, 3, 5); }

  @Override
  public IEquipableItem createFullCustomItem(String name, int power, int minRange, int maxRange) { return new LightBook(name, power, minRange, maxRange); }

  @Override
  public IEquipableItem createCustomPowerItem(String name, int power) { return new LightBook(name, power, 3, 5); }
}
