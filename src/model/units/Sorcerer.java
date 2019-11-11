package model.units;

import model.items.IEquipableItem;
import model.items.IMagicWeapon;
import model.map.Location;

/**
 * A <i>Sorcerer</i> is a special kind of unit.
 * <p>
 * This class <b>can only use magic books</b> and to attack with them.
 *
 * @author Sebastián Zapata Ascencio
 * @since 1.0
 */

public class Sorcerer extends AbstractUnit {

  /**
   * Creates a new Sorcerer
   *
   * @param maxHitPoints
   *     maximum hit points of the unit
   * @param movement
   *     the amount of cells this unit can move
   * @param location
   *     the initial position of this unit
   * @param items
   *     the items carried by this unit
   */
  public Sorcerer(int maxHitPoints, int movement, Location location, IEquipableItem... items) {
    super(maxHitPoints, movement, location, 3, items);
    setName("Sorcerer");
  }

  @Override
  public void equipMagicBook(IMagicWeapon weapon) {
    weapon.beEquippedByOwner();
  }
}
