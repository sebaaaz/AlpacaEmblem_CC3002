package model.itemFactories;

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
  IEquipableItem createFullCustomItem();

  /**
   * Creates an item with custom name and power parameters.
   * @return a custom item.
   */
  IEquipableItem createCustomPowerItem();

  /**
   * Sets the name of the item
   *
   * @param name
   *      the name of the item.
   */
  void setName(String name);

  /**
   * Sets the power of the item
   *
   * @param power
   *      the power of the item
   */
  void setPower(int power);

  /**
   * Sets the min range of the item.
   *
   * @param minRange
   *      the min range of the item.
   */
  void setMinRange(int minRange);

  /**
   * Sets the max range of the item
   *
   * @param maxRange
   *      the max range of the item
   */
  void setMaxRange(int maxRange);
}
