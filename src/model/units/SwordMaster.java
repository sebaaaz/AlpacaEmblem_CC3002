package model.units;

import model.items.IEquipableItem;
import model.map.Location;

/**
 * This class represents a <i>SwordMaster</i> type unit.
 * <p>
 * A <i>SwordMaster</i> is a unit that <b>can only use sword type weapons</b>.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class SwordMaster extends AbstractUnit {

  /**
   * Creates a new SwordMaster
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
  public SwordMaster(final int hitPoints, final int movement, final Location location,
      IEquipableItem... items) {
    super(hitPoints, movement, location, 3, items);
  }

  @Override
  public void equipSword(final IEquipableItem item) {
    this.setEquippedItem(item);
    item.setOwner(this);
  }

  @Override
  public void receiveAxeAttack(IEquipableItem item) {
    receiveResistantAttack(item);
  }

  @Override
  public void receiveSpearAttack(IEquipableItem item) {
    receiveWeaknessAttack(item);
  }

  @Override
  public void receiveMagicalAttack(IEquipableItem item) {
    if (getEquippedItem() != null) {
      receiveWeaknessAttack(item);
    }
  }
}
