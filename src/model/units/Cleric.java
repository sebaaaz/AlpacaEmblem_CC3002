package model.units;

import model.items.IEquipableItem;
import model.map.Location;

/**
 * This class represents a cleric type unit. A cleric can only use staff type weapons, which means
 * that it can receive attacks but can't counter attack any of those.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class Cleric extends AbstractUnit {

  /**
   * Creates a new Unit.
   *
   * @param maxHitPoints
   *     the maximum amount of damage a unit can sustain
   * @param movement
   *     the number of panels a unit can move
   * @param location
   *     the initial position of this unit
   * @param items
   *     the items carried by this unit
   */
  public Cleric(int maxHitPoints, int movement, Location location,
      IEquipableItem... items) {
    super(maxHitPoints, movement, location, 3, items);
  }

  @Override
  public void equipStaff(final IEquipableItem item) {
    this.setEquippedItem(item);
    item.setOwner(this);
  }
}
