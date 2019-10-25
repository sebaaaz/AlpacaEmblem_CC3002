package model.units;

import static java.lang.Math.min;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import model.items.IEquipableItem;
import model.items.NullItem;
import model.map.InvalidLocation;
import model.map.Location;

/**
 * This class represents an abstract unit.
 * <p>
 * An abstract unit is a unit that cannot be used in the
 * game, but that contains the implementation of some of the methods that are common for most
 * units.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public abstract class AbstractUnit implements IUnit {

  private List<IEquipableItem> items = new ArrayList<>();
  private IEquipableItem equippedItem;
  private final int maxItems;
  private final int maxHitPoints;
  private int hitPoints;
  private final int movement;
  private Location location;

  /**
   * Creates a new Unit.
   *
   * @param maxHitPoints the maximum amount of damage a unit can sustain
   * @param movement  the number of panels a unit can move
   * @param location  the initial position of this unit on the map
   * @param maxItems  maximum amount of items this unit can carry
   * @param items     the initial items that the unit has
   */
  protected AbstractUnit(final int maxHitPoints, final int movement,
                         final Location location, final int maxItems, final IEquipableItem... items) {
    this.maxHitPoints = maxHitPoints;
    this.hitPoints = this.maxHitPoints;
    this.movement = movement;
    this.location = location;
    this.items.addAll(Arrays.asList(items).subList(0, min(maxItems, items.length)));
    this.maxItems = maxItems;
    this.equippedItem = new NullItem(this);
  }

  @Override
  public boolean isNull() { return false; }

  @Override
  public int getMaxHitPoints() {
    return maxHitPoints;
  }

  @Override
  public int getHitPoints() {
    return hitPoints;
  }

  @Override
  public List<IEquipableItem> getItems() {
    return List.copyOf(items);
  }

  @Override
  public IEquipableItem getItem(int index) {
    return (index >= 0 && index < getItems().size()) ? getItems().get(index) : new NullItem(this);
  }

  @Override
  public int getMaxItems() {
    return maxItems;
  }

  @Override
  public IEquipableItem getEquippedItem() {
    return equippedItem;
  }

  @Override
  public Location getLocation() {
    return location;
  }

  @Override
  public int getMovement() {
    return movement;
  }

  @Override
  public void setEquippedItem(IEquipableItem item) {
    this.equippedItem = item;
  }

  @Override
  public void setHitPoints(int hitPoints) { this.hitPoints = hitPoints; }

  @Override
  public void setLocation(final Location location) {
    location.setUnit(this);
    this.location = location;
  }

  @Override
  public void moveTo(final Location targetLocation) {
    if (getLocation().distanceTo(targetLocation) <= getMovement() &&
        targetLocation.getUnit().isNull()) {
      setLocation(targetLocation);
    }
  }

  @Override
  public void equipItem(IEquipableItem item) {
    if (this.items.contains(item))
      item.equipTo(this);
  }

  @Override
  public void equipAxe(IEquipableItem item) {}

  @Override
  public void equipBow(IEquipableItem item) {}

  @Override
  public void equipSpear(IEquipableItem item) {}

  @Override
  public void equipStaff(IEquipableItem item) {}

  @Override
  public void equipSword(IEquipableItem item) {}

  @Override
  public void equipLightBook(IEquipableItem item) {}

  @Override
  public void equipDarkBook(IEquipableItem item) {}

  @Override
  public void equipSoulBook(IEquipableItem item) {}

  @Override
  public void giveItemTo(IEquipableItem item, IUnit unit) {
    if (!item.isNull()
        && !unit.isNull()
        && this.items.contains(item)
        && unit.getItems().size() < unit.getMaxItems()
        && this.location.distanceTo(unit.getLocation()) == 1) {
      if (item == equippedItem) {
        unequipItem();
      }
      this.removeItem(item);
      unit.addItem(item);
      item.setOwner(unit);
    }
  }

  @Override
  public void unequipItem() {
    this.equippedItem = new NullItem(this);
  }

  @Override
  public void addItem(IEquipableItem item) {
    if (items.size() < getMaxItems() && !item.isNull()) {
      item.setOwner(this);
      items.add(item);
    }
  }

  @Override
  public void removeItem(IEquipableItem item) {
    this.unequipItem();
    item.setOwner(new NullUnit());
    items.remove(item);
  }

  @Override
  public void useItemAgainst(IUnit unit) {
    if (getLocation().distanceTo(unit.getLocation()) <= getEquippedItem().getMaxRange()
    &&  getLocation().distanceTo(unit.getLocation()) >= getEquippedItem().getMinRange()) {
      getEquippedItem().useAgainst(unit);
    }
  }

  @Override
  public void counterAttack(IUnit unit) {
    getEquippedItem().counterAttackTo(unit);
  }

  @Override
  public void startCombat(IUnit unit) {
    useItemAgainst(unit);
    getEquippedItem().motivateCounterAttack(unit);
  }
}
