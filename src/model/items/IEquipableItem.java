package model.items;

import model.units.IUnit;

/**
 * This interface represents the <i>items</i> that the units of the game can use.
 * <p>
 * The signature for all the common methods of the items are defined here. Every item have a
 * base damage and is strong or weak against other type of items.
 *
 * @author Ignacio Slater Muñoz
 * @author Sebastián Zapata Ascencio
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
   * @param name
   *      Sets a new name for this item
   */
  void setName(String name);

  /**
   * @param power
   *      Sets the new power of this item
   */
  void setPower(int power);

  /**
   * @param minRange
   *      Sets the minimum range of this item
   */
  void setMinRange(int minRange);

  /**
   * @param maxRange
   *      Sets the maximum range of this item
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
   * Sends the specific attack of this item
   *
   * @param item
   *      the item that will receive the specific attack
   */
  void sendAttack(IEquipableItem item);

  /**
   * Sends an attack in the context of counter attack
   *
   * @param unit
   *      the unit that will receive the attack
   */
  void counterAttackTo(IUnit unit);

  /**
   * Decides if the enemy unit can counter attack. It depends
   * on the item who use this method.
   *
   * @param unit
   *      the unit that will be allowed to counter attack
   */
  void motivateCounterAttack(IUnit unit);

  /**
   * @return true if the item is a null item, false otherwise.
   */
  boolean isNull();

  /**
   * Receives damage from a magic item attack
   *
   * @param item
   *      the item that does the damage
   */
  void receiveMagicalAttack(IEquipableItem item);

  /**
   * Receives damage from a physical item attack
   *
   * @param item
   *      the item that does the damage
   */
  void receivePhysicalAttack(IEquipableItem item);

  /**
   * Receives damage from an axe attack
   *
   * @param item
   *      the item that does the damage
   */
  void receiveAxeAttack(IEquipableItem item);

  /**
   * Receives damage from a bow attack
   *
   * @param item
   *      the item that does the damage
   */
  void receiveBowAttack(IEquipableItem item);

  /**
   * Receives damage from a dark book attack
   *
   * @param item
   *      the item that does the damage
   */
  void receiveDarkBookAttack(IEquipableItem item);

  /**
   * Receives damage from a light book attack
   *
   * @param item
   *      the item that does the damage
   */
  void receiveLightBookAttack(IEquipableItem item);

  /**
   * Receives damage from a soul book attack
   *
   * @param item
   *      the item that does the damage
   */
  void receiveSoulBookAttack(IEquipableItem item);

  /**
   * Receives damage from a spear attack
   *
   * @param item
   *      the item that does the damage
   */
  void receiveSpearAttack(IEquipableItem item);

  /**
   * Receives healing from a staff attack
   *
   * @param item
   *      the item that does the healing
   */
  void receiveStaffHealing(IEquipableItem item);

  /**
   * Receives damage from an sword attack
   *
   * @param item
   *      the item that does the damage
   */
  void receiveSwordAttack(IEquipableItem item);

  /**
   * Receives positive healing from an attack with the item
   *
   * @param item
   *      Item which heals this unit
   */
  void receiveHealing(IEquipableItem item);

  /**
   * Receives negative damage from an attack with the item
   *
   * @param item
   *      Item which attacks this unit
   */
  void receiveNormalAttack(IEquipableItem item);

  /**
   * Receives negative increased damage from an attack with the item
   *
   * @param item
   *      Item which attacks this unit
   */
  void receiveWeaknessAttack(IEquipableItem item);

  /**
   * Receives negative reduced damage from an attack with the item
   *
   * @param item
   *      Item which attacks this unit
   */
  void receiveResistantAttack(IEquipableItem item);

  /**
   * Decides if return this item (only non Null items will do it) or the passed item.
   *
   * @param item
   *      the item to analyze
   *
   * @return this, if this item is not the Null item. Returns the item passed if so.
   */
  IEquipableItem itemOrThis(IEquipableItem item);
}
