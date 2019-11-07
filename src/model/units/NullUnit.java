package model.units;

import model.Tactician;
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

  public static final NullUnit NULL_UNIT = new NullUnit();

  /**
   * Creates a new Null Unit
   */
  public NullUnit() {
    super(1, 0, new InvalidLocation(), 0);
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
  public void setLocation(Location location) {}

  @Override
  public void moveTo(Location targetLocation) {}

  @Override
  public void equipItem(IEquipableItem item) {}

  @Override
  public void giveItemTo(IEquipableItem item, IUnit targetUnit) {}

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
    return (obj instanceof NullUnit && (NullUnit) obj == this);
  }

  @Override
  public void beSelectedBy(Tactician tactician) {tactician.selectUnit(this);}
}
