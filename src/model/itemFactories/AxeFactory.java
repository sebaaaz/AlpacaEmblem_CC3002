package model.itemFactories;

import model.items.Axe;

public class AxeFactory extends AbstractEquipableItemFactory {

  @Override
  public Axe createItem() { return new Axe("Common Axe", 10, 1, 2); }

  @Override
  public Axe createFullCustomItem() { return new Axe(name, power, minRange, maxRange); }

  @Override
  public Axe createCustomPowerItem() { return new Axe(name, power, 1, 2); }
}
