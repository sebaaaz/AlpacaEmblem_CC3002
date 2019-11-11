package model.units;

import model.items.IEquipableItem;
import model.map.InvalidLocation;
import model.map.Location;

/**
 * A <i>Hero</i> is a special kind of unit, the player that defeats this unit wins the game.
 * <p>
 * This unit <b>can only use spear weapons</b>.
 *
 * @author Ignacio Slater Muñoz
 * @author Sebastián Zapata Ascencio
 * @since 1.0
 */
public class Hero extends AbstractUnit {

  /**
   * Creates a new Hero
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
  public Hero(final int maxHitPoints, final int movement, final Location location, IEquipableItem... items) {
    super(maxHitPoints, movement, location, 3, items);
    setName("Hero");
  }

  @Override
  public void equipSpear(final IEquipableItem item) {
    this.setEquippedItem(item);
  }

  @Override
  public void toBeDefeated() {
    this.setLocation(new InvalidLocation());
    if (getOwner() != null) {
    getOwner().removeUnit(this);
    getOwner().notifyHeroDefeated();
    }
  }

}
