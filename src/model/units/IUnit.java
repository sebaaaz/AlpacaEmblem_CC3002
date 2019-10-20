package model.units;

import java.util.List;
import model.items.IEquipableItem;
import model.map.Location;

/**
 * This interface represents all units in the game.
 * <p>
 * The signature of all the common methods that a unit can execute are defined here. All units
 * except some special ones can carry at most 3 weapons.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public interface IUnit {

  /**
   * Sets the currently equipped item of this unit.
   *
   * @param item
   *     the item to equip
   */
  void equipItem(IEquipableItem item);

  /**
   * @return max hit points of the unit
   */
  int getMaxHitPoints();

  /**
   * @return hit points of the unit
   */
  int getCurrentHitPoints();

  /**
   * @return the items carried by this unit
   */
  List<IEquipableItem> getItems();

  /**
   * Given an index, return the item in that position.
   *
   * @param index
   *      the index of the item list
   * @return the item in that position of the item list. Return null if
   *      index out of bounds.
   */
  IEquipableItem getItem(int index);

  /**
   * @return the maximum amount of items a unit can carry
   */
  int getMaxItems();

  /**
   * @return the currently equipped item
   */
  IEquipableItem getEquippedItem();

  /**
   * @param item
   *     the item to be equipped
   */
  void setEquippedItem(IEquipableItem item);

  /**
   * @return the current location of the unit
   */
  Location getLocation();

  /**
   * Sets a new location for this unit
   *
   * @param location
   *      the location for the unit
   */
  void setLocation(final Location location);

  /**
   * @return the number of cells this unit can move
   */
  int getMovement();

  /**
   * Moves this unit to another location.
   * <p>
   * If the other location is out of this unit's movement range, the unit doesn't move.
   */
  void moveTo(Location targetLocation);

  /**
   * Equips an Axe item.
   *
   * @param item
   *     the item to be equipped
   */
  void equipAxe(IEquipableItem item);

  /**
   * Equips an Bow item.
   *
   * @param item
   *     the item to be equipped
   */
  void equipBow(IEquipableItem item);

  /**
   * Equips an Spear item.
   *
   * @param item
   *     the item to be equipped
   */
  void equipSpear(IEquipableItem item);

  /**
   * Equips an Staff item.
   *
   * @param item
   *     the item to be equipped
   */
  void equipStaff(IEquipableItem item);

  /**
   * Equips an Sword item.
   *
   * @param item
   *     the item to be equipped
   */
  void equipSword(IEquipableItem item);

  /**
   * Equips a Light Book item.
   *
   * @param item
   *      the item to be equipped
   */
  void equipLightBook(IEquipableItem item);

  /**
   * Equips a Dark Book item.
   *
   * @param item
   *      the item to be equipped
   */
  void equipDarkBook(IEquipableItem item);

  /**
   * Equips a Soul Book item.
   *
   * @param item
   *      the item to be equipped
   */
  void equipSoulBook(IEquipableItem item);

  /**
   * Gives the item to the unit. The item will no longer be in the
   * item list and therefore will no longer be equipped.
   * <p>
   * This method only works if both units are in neighbour locations,
   * the giver unit has the item and the receiver unit has slots
   * available in its item list.
   *
   * @param item
   *      the item to be given
   * @param unit
   *      the unit that will receive the item
   */
  void giveItemTo(IEquipableItem item, IUnit unit);

  /**
   * Unequips current equipped item
   */
  void unequipItem();

  /**
   * Adds an item to the item list
   *
   * @param item
   *      the item to be added
   */
  void addItem(IEquipableItem item);

  /**
   * Removes an item of the item list
   *
   * @param item
   *      the item to be removed
   */
  void removeItem(IEquipableItem item);

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
   * Uses the item equipped against other unit.
   *
   * @param unit
   *      the unit who will receive the effect of the equipped item
   */
  void useItemAgainst(IUnit unit);

  /**
   * If someone attacked this unit, this method allows to do
   * a counter attack to the same unit who attacked first.
   *
   * @param unit
   *      the unit to be attacked
   */
  void counterAttack(IUnit unit);

  /**
   * Starts a combat against the other unit
   *
   * @param unit
   *      the unit to be combated
   */
  void startCombat(IUnit unit);
}
