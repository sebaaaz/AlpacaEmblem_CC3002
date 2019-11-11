package model.units;

import model.items.IEquipableItem;
import model.map.Location;

/**
 * This class represents a <i>SwordMaster</i> type unit.
 * <p>
 * A <i>SwordMaster</i> is a unit that <b>can only use sword type weapons</b>.
 *
 * @author Ignacio Slater Muñoz
 * @author Sebastián Zapata Ascencio
 * @since 1.0
 */
public class SwordMaster extends AbstractUnit {

  /**
   * Creates a new SwordMaster
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
  public SwordMaster(int maxHitPoints, int movement, Location location,
      IEquipableItem... items) {
    super(maxHitPoints, movement, location, 3, items);
    setName("SwordMaster");
  }

  @Override
  public void equipSword(final IEquipableItem item) {
    this.setEquippedItem(item);
  }
}
