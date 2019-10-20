package model.itemFactories;

import model.items.Sword;

public class SwordFactory extends AbstractEquipableItemFactory {

  @Override
  public Sword createItem() { return new Sword("Common Sword", 10, 1, 1); }

  @Override
  public Sword createFullCustomItem() { return new Sword(name, power, minRange, maxRange); }

  @Override
  public Sword createCustomPowerItem() { return new Sword(name, power, 1, 1); }
}
