package model.factories.itemFactory;

import model.items.IEquipableItem;
import model.items.SoulBook;

/**
 * This class represents a <i>SoulBook Factory</i> type factory.
 * <p>
 * They only return <i>SoulBook</i> items.
 *
 * @author Sebastián Zapata Ascencio
 * @since 2.0
 */
public class SoulBookFactory implements IEquipableItemFactory {

  @Override
  public IEquipableItem createItem() { return new SoulBook("Common Soul Book", 5, 3, 5); }

  @Override
  public IEquipableItem createFullCustomItem(String name, int power, int minRange, int maxRange) { return new SoulBook(name, power, minRange, maxRange); }

  @Override
  public IEquipableItem createCustomPowerItem(String name, int power) { return new SoulBook(name, power, 3, 5); }
}
