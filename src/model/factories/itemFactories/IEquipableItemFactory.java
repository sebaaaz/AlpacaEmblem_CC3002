package model.factories.itemFactories;

import model.items.IEquipableItem;

public interface IEquipableItemFactory {

  /**
   * Creates an item with default parameters.
   * @return a default item.
   */
  IEquipableItem createItem();

  /**
   * Creates an item with all parameters setted by the player.
   * @return a custom item.
   */
  IEquipableItem createFullCustomItem(String name, int power, int minRange, int maxRange);

  /**
   * Creates an item with custom name and power parameters.
   * @return a custom item.
   */
  IEquipableItem createCustomPowerItem(String name, int power);
}
