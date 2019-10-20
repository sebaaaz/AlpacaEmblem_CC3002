package model.itemFactories;

import model.items.SoulBook;

public class SoulBookFactory extends AbstractEquipableItemFactory {

  @Override
  public SoulBook createItem() { return new SoulBook("Common Soul Book", 5, 3, 5); }

  @Override
  public SoulBook createFullCustomItem() { return new SoulBook(name, power, minRange, maxRange); }

  @Override
  public SoulBook createCustomPowerItem() { return new SoulBook(name, power, 3, 5); }
}
