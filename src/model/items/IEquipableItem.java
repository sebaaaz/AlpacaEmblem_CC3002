package model.items;

import model.units.IUnit;

/**
 * This interface represents the <i>items</i> that the units of the game can use.
 * <p>
 * The signature for all the common methods of the items are defined here. Every item have a
 * base damage and is strong or weak against other type of items.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public interface IEquipableItem {

  /**
   * @return the unit that has currently equipped this item
   */
  IUnit getOwner();

  /**
   * @return the name of the item
   */
  String getName();

  /**
   * @return the power of the item
   */
  int getPower();

  /**
   * @return the minimum range of the item
   */
  int getMinRange();

  /**
   * @return the maximum range of the item
   */
  int getMaxRange();

  /**
   * @param owner
   *      Sets a new owner for this item
   */
  void setOwner(IUnit owner);

  /**
   * @param minRange
   *      Sets the minimum range of the item
   */
  void setMinRange(int minRange);

  /**
   * @param maxRange
   *      Sets the maximum range of the item
   */
  void setMaxRange(int maxRange);

  /**
   * Equips this item to a unit.
   *
   * @param unit
   *     the unit that will be equipped with the item
   */
  void equipTo(IUnit unit);

  /**
   * Uses this item against an unit
   *
   * @param unit
   *      the unit that will receive the effect of this item
   */
  void useAgainst(IUnit unit);

  /**
   * Sends an axe attack to an unit
   *
   * @param unit
   *      the unit that will receive the axe attack
   */
//  void sendAxeAttack(IUnit unit);
}
