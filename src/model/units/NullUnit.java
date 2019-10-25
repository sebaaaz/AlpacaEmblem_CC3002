package model.units;

import model.items.IEquipableItem;
import model.items.NullItem;
import model.map.InvalidLocation;
import model.map.Location;

/**
 * This class represents a Null Unit.
 * <p>
 * Null units do not affect the game in any way. They allow a better
 * handling of some item events.
 * <p>
 * Null units can't be created by a player.
 *
 * @author Sebastián Zapata Ascencio
 * @since 2.0
 */
public class NullUnit extends AbstractUnit {

  /**
   * Creates a new Null Unit
   */
  public NullUnit() {
    super(1, 0, new InvalidLocation(), 0);
  }

  /**
   * Creates a new Null unit in the location.
   * @param location
   *      the location where will be the Null Unit.
   */
  public NullUnit(Location location) {
    super(1, 0, location, 0);
  }

  @Override
  public IEquipableItem getItem(int index) {
    return new NullItem(this);
  }

  @Override
  public boolean isNull() { return true; }

  // setters empty because we can't change its parameters.

  @Override
  public void setEquippedItem(IEquipableItem item) {}

  @Override
  public void setHitPoints(int hitPoints) {}

  @Override
  public void setLocation(final Location location) {}

  @Override
  public void moveTo(final Location targetLocation) {}

  @Override
  public void equipItem(IEquipableItem item) {}

  @Override
  public void giveItemTo(IEquipableItem item, IUnit unit) {}

  @Override
  public void addItem(IEquipableItem item) {}

  @Override
  public void removeItem(IEquipableItem item) {}

  @Override
  public void useItemAgainst(IUnit unit) {}

  @Override
  public void counterAttack(IUnit unit) {}

  @Override
  public void startCombat(IUnit unit) {}

  @Override
  public boolean equals(Object obj) {
    NullUnit unit;
    if (obj instanceof NullUnit) unit = (NullUnit) obj;
    else return false;
    return unit.getLocation() instanceof InvalidLocation ||
           this.getLocation() == unit.getLocation();
  }
}
