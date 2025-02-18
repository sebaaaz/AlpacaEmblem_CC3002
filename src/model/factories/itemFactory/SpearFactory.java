package model.factories.itemFactory;

import model.items.IEquipableItem;
import model.items.Spear;

/**
 * This class represents a <i>Spear Factory</i> type factory.
 * <p>
 * They only return <i>Spear</i> items.
 *
 * @author Sebastián Zapata Ascencio
 * @since 2.0
 */
public class SpearFactory implements IEquipableItemFactory {

  @Override
  public IEquipableItem createItem() { return new Spear("Common Spear", 10, 1, 3); }

  @Override
  public IEquipableItem createFullCustomItem(String name, int power, int minRange, int maxRange) { return new Spear(name, power, minRange, maxRange); }

  @Override
  public IEquipableItem createCustomPowerItem(String name, int power) { return new Spear(name, power, 1, 3); }
}