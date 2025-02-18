package model.units;

import static java.lang.Math.min;
import static model.units.NullUnit.NULL_UNIT;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.Tactician;
import model.items.IEquipableItem;
import model.items.IMagicWeapon;
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
 * @author Sebastián Zapata Ascencio
 * @since 1.0
 */
public abstract class AbstractUnit implements IUnit {

  private List<IEquipableItem> items = new ArrayList<>();
  private IEquipableItem equippedItem;
  private final int maxItems;
  private final int maxHitPoints;
  private int hitPoints;
  private final int originalMovement;
  private int movement;
  private Location location;
  private Tactician owner;
  private String name;

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
    this.originalMovement = movement;
    this.movement = originalMovement;

    location.setUnit(this);
    this.location = location;

    this.items.addAll(Arrays.asList(items).subList(0, min(maxItems, items.length)));
    this.maxItems = maxItems;
    this.equippedItem = new NullItem(this);
  }

  @Override
  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public int getOriginalMovement() {
    return originalMovement;
  }

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
  public void equipItem(IEquipableItem item) {
    if (this.items.contains(item)) item.equipTo(this);
  }

  @Override
  public void setHitPoints(int hitPoints) {
    this.hitPoints = hitPoints;
    if (getHitPoints() <= 0) toBeDefeated();
  }

  @Override
  public void setLocation(final Location location) {
    location.setUnit(this);
    this.location = location;
  }

  @Override
  public void moveTo(final Location targetLocation) {
    if (   getLocation().distanceTo(targetLocation) <= getMovement()
        && targetLocation.getUnit().isNull()
        && targetLocation != getLocation() ) {
      setLocation(targetLocation);
      denyMovement();
    }
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
  public void equipMagicBook(IMagicWeapon item) {}

  @Override
  public void giveItemTo(IEquipableItem item, IUnit targetUnit) {
    if (!item.isNull()
        && !targetUnit.isNull()
        && this.items.contains(item)
        && targetUnit.getItems().size() < targetUnit.getMaxItems()
        && this.location.distanceTo(targetUnit.getLocation()) == 1
        && this.getOwner() == targetUnit.getOwner()) {
      if (item == equippedItem) {
        unequipItem();
      }
      this.removeItem(item);
      targetUnit.addItem(item);
      item.setOwner(targetUnit);
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
    item.setOwner(NULL_UNIT);
    items.remove(item);
  }

  @Override
  public void useItemAgainst(IUnit unit) {
    if (getLocation().distanceTo(unit.getLocation()) <= getEquippedItem().getMaxRange()
    &&  getLocation().distanceTo(unit.getLocation()) >= getEquippedItem().getMinRange()) {
      getEquippedItem().sendItemTypeAttack(unit );
    }
  }

  @Override
  public void attack(IUnit unit) {
    getEquippedItem().initUseOn(unit);
  }

  @Override
  public boolean isOnValidLocation() {
    return getLocation().isValidLocation();
  }

  @Override
  public boolean isNull() { return false; }

  @Override
  public void setOwner(Tactician owner) {
    this.owner = owner;
  }

  @Override
  public Tactician getOwner() {
    return owner;
  }

  @Override
  public void beSelectedBy(Tactician tactician) {
    tactician.selectUnit(NULL_UNIT);
    if (owner != null) owner.selectUnit(this);
  }

  @Override
  public void toBeDefeated() {
    this.setLocation(new InvalidLocation());
    if (getOwner() != null) getOwner().removeUnit(this);
  }

  @Override
  public void denyMovement() {
    movement = 0;
  }

  @Override
  public void allowMovement() {
    movement = originalMovement;
  }
}
