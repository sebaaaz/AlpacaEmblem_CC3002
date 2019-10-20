package model.itemFactories;

import model.items.LightBook;

public class LightBookFactory extends AbstractEquipableItemFactory {

  @Override
  public LightBook createItem() { return new LightBook("Common Light Book", 5, 3, 5); }

  @Override
  public LightBook createFullCustomItem() { return new LightBook(name, power, minRange, maxRange); }

  @Override
  public LightBook createCustomPowerItem() { return new LightBook(name, power, 3, 5); }
}
