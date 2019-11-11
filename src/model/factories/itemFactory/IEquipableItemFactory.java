package model.factories.itemFactory;

import model.items.IEquipableItem;

/**
 * This interface represents all item factories in the game.
 * <p>
 * The signature of all the common methods that a factory can execute are defined here. All
 * item factories can create only items that inherit from <i>IEquipableItem</i>.
 *
 * @author Sebastián Zapata Ascencio
 * @since 2.0
 */
public interface IEquipableItemFactory {

  /**
   * Creates an item with default parameters.
   * @return a default item.
   */
  IEquipableItem createItem();

  /**
   * Creates an item with all parameters setted by the player.
   *
   * @param name
   *     the name that identifies the sword
   * @param power
   *     the base damage of the sword
   * @param minRange
   *     the minimum range of the sword
   * @param maxRange
   *     the maximum range of the sword
   *
   * @return a custom item.
   */
  IEquipableItem createFullCustomItem(String name, int power, int minRange, int maxRange);

  /**
   * Creates an item with custom name and power parameters.
   *
   * @param name
   *     the name that identifies the sword
   * @param power
   *     the base damage of the sword
   *
   * @return a custom item.
   */
  IEquipableItem createCustomPowerItem(String name, int power);
}
