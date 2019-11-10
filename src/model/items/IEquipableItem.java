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
   * Sets this item as equipped by the owner.
   */
  void beEquippedByOwner();

  /**
   * @return true if the item is a null item, false otherwise.
   */
  boolean isNull();

  /**
   * Receives damage from a magic item attack
   *
   * @param weapon
   *      the weapon that does the damage
   */
  void receiveMagicalAttack(IMagicWeapon weapon);

  /**
   * Receives damage from a physical item attack
   *
   * @param weapon
   *      the weapon that does the damage
   */
  void receivePhysicalAttack(IPhysicWeapon weapon);

  /**
   * Receives damage from an axe attack
   *
   * @param weapon
   *      the item that does the damage
   */
  void receiveAxeAttack(IPhysicWeapon weapon);

  /**
   * Receives damage from a bow attack
   *
   * @param weapon
   *      the item that does the damage
   */
  void receiveBowAttack(IPhysicWeapon weapon);

  /**
   * Receives damage from a dark book attack
   *
   * @param weapon
   *      the item that does the damage
   */
  void receiveDarkBookAttack(IMagicWeapon weapon);

  /**
   * Receives damage from a light book attack
   *
   * @param weapon
   *      the item that does the damage
   */
  void receiveLightBookAttack(IMagicWeapon weapon);

  /**
   * Receives damage from a soul book attack
   *
   * @param weapon
   *      the item that does the damage
   */
  void receiveSoulBookAttack(IMagicWeapon weapon);

  /**
   * Receives damage from a spear attack
   *
   * @param weapon
   *      the item that does the damage
   */
  void receiveSpearAttack(IPhysicWeapon weapon);

  /**
   * Receives healing from a staff attack
   *
   * @param item
   *      the item that does the healing
   */
  void receiveStaffHealing(INonWeaponItem item);

  /**
   * Receives damage from an sword attack
   *
   * @param weapon
   *      the item that does the damage
   */
  void receiveSwordAttack(IPhysicWeapon weapon);

  /**
   * The owner of this item receives positive healing.
   * <p>
   * The owner will sum the power, to its <i>hitPoints</i>.
   *
   * @param power
   *      the power of the heal
   */
  void receiveHealing(int power);

  /**
   * The owner of this item receives negative damage from an attack.
   * <p>
   * The owner will discount the power, to its <i>hitPoints</i>.
   *
   * @param power
   *      the power of the damage
   */
  void receiveNormalAttack(int power);

   /**
   * The owner of this item receives negative damage from an attack.
   * <p>
   * The owner will discount the integer part of the (power*1.5), to its <i>hitPoints</i>.
   *
   * @param power
   *      the power of the damage
   */
  void receiveWeaknessAttack(int power);

  /**
   * The owner of this item receives negative damage from an attack.
   * <p>
   * The owner will discount the (power - 20), to its <i>hitPoints</i>.
   *
   * @param power
   *      Item which attacks this unit
   */
  void receiveResistantAttack(int power);

  /**
   * Gets the initiative and does the first contact, sending the effect of this
   * weapon to the unit.
   *
   * @param unit
   *      the unit to be affected by this item
   */
  void initUseOn(IUnit unit);

  /**
   * Triggers an event in response to an attack received.
   *
   * @param item
   *      the unit that attacked this item
   */
  void beAttacked(IEquipableItem item);

  /**
   * Sends the type weapon/non-weapon of this item to an unit.
   *
   * @param unit
   *      the unit that will receive the attack type of this item.
   */
  void sendItemTypeAttack(IUnit unit);

  /**
   * Sends the specific effect of this item to an unit.
   *
   * @param item
   *      the item that will receive the specific effect of this item.
   */
  void sendSpecificEffect(IEquipableItem item);

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
