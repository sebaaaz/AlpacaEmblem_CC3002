package model.itemFactories;

import model.items.Spear;

public class SpearFactory extends AbstractEquipableItemFactory {

  @Override
  public Spear createItem() { return new Spear("Common Spear", 10, 1, 3); }

  @Override
  public Spear createFullCustomItem() { return new Spear(name, power, minRange, maxRange); }

  @Override
  public Spear createCustomPowerItem() { return new Spear(name, power, 1, 3); }
}
