package model.itemFactories;

import model.items.Bow;

public class BowFactory extends AbstractEquipableItemFactory {

  @Override
  public Bow createItem() { return new Bow("Common Bow", 8, 2, 4); }

  @Override
  public Bow createFullCustomItem() { return new Bow(name, power, minRange, maxRange); }

  @Override
  public Bow createCustomPowerItem() { return new Bow(name, power, 2, 4); }
}
