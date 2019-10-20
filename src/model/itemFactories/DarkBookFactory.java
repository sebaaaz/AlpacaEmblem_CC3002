package model.itemFactories;

import model.items.DarkBook;

public class DarkBookFactory extends AbstractEquipableItemFactory {

  @Override
  public DarkBook createItem() { return new DarkBook("Common Dark Book", 5, 3, 5); }

  @Override
  public DarkBook createFullCustomItem() { return new DarkBook(name, power, minRange, maxRange); }

  @Override
  public DarkBook createCustomPowerItem() { return new DarkBook(name, power, 3, 5); }
}
