package model.units;

import model.items.IEquipableItem;
import model.map.Location;

/**
 * This class represents a <i>fighter</i> type unit.
 * <p>
 * A <i>Fighter</i> is a unit that can only use axe type weapons.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class Fighter extends AbstractUnit {

  /**
   * Creates a new Fighter
   *
   * @param hitPoints
   *     maximum hit points of the unit
   * @param movement
   *     the amount of cells this unit can move
   * @param location
   *     the initial position of this unit
   * @param items
   *     the items carried by this unit
   */
  public Fighter(final int hitPoints, final int movement, final Location location,
      IEquipableItem... items) {
    super(hitPoints, movement, location, 3, items);
  }

  @Override
  public void equipAxe(IEquipableItem item) {
    this.setEquippedItem(item);
    item.setOwner(this);
  }

  @Override
  public void receiveSpearAttack(IEquipableItem item) {

  }
}
