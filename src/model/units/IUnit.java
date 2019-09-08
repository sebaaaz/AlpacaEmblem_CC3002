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
}