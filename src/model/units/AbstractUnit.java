package model.units;

import static java.lang.Math.min;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import model.items.IEquipableItem;
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
  private final int maxItems;
  private final int maxHitPoints;
  private  int currentHitPoints;
  private final int movement;
  private IEquipableItem equippedItem;
  private Location location;

  /**
   * Creates a new Unit.
   *
   * @param hitPoints
   *     the maximum amount of damage a unit can sustain
   * @param movement
   *     the number of panels a unit can move
   * @param location
   *     the initial position of this unit on the map
   * @param maxItems
   *     maximum amount of items this unit can carry
   * @param items
   *     the initial items that the unit has
   */
  protected AbstractUnit(final int hitPoints, final int movement,
      final Location location, final int maxItems, final IEquipableItem... items) {
    this.maxHitPoints = hitPoints;
    this.currentHitPoints = this.maxHitPoints;
    this.movement = movement;
    this.location = location;
    this.items.addAll(Arrays.asList(items).subList(0, min(maxItems, items.length)));
    this.maxItems = maxItems;
  }

  @Override
  public int getMaxHitPoints() { return maxHitPoints; }

  @Override
  public int getCurrentHitPoints() {
    return currentHitPoints;
  }

  @Override
  public List<IEquipableItem> getItems() {
    return List.copyOf(items);
  }

  @Override
  public int getMaxItems() { return maxItems; }

  @Override
  public IEquipableItem getEquippedItem() {
    return equippedItem;
  }

  @Override
  public void setEquippedItem(final IEquipableItem item) {
    this.equippedItem = item;
  }

  @Override
  public Location getLocation() {
    return location;
  }

  @Override
  public void setLocation(final Location location) {
    this.location = location;
  }

  @Override
  public int getMovement() {
    return movement;
  }

  @Override
  public void moveTo(final Location targetLocation) {
    if (getLocation().distanceTo(targetLocation) <= getMovement()
        && targetLocation.getUnit() == null) {
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
    if (this.items.contains(item)
    && unit.getItems().size() < unit.getMaxItems()
    && this.location.distanceTo(unit.getLocation()) == 1 ) {
      if (item == equippedItem) { unequipItem(); }
      this.removeItem(item);
      unit.addItem(item);
      item.setOwner(unit);
    }
  }

  @Override
  public void unequipItem() { this.equippedItem = null; }

  @Override
  public void addItem(IEquipableItem item) {
    if (items.size() < getMaxItems()) { items.add(item); }
  }

  @Override
  public void removeItem(IEquipableItem item) {
    this.unequipItem();
    items.remove(item);
  }

  /*@Override
  public void startCombat(IUnit unit) {
    if (getEquippedItem() != null) {
      getEquippedItem().useAgainst(unit);
    }
  }*/

  /**
   * Receives negative damage from an attack with the item
   *
   * @param item
   *      Item which attacks this unit
   */
  protected void receiveAttack(IEquipableItem item) {
    this.currentHitPoints = Math.max(this.currentHitPoints - item.getPower(), 0);
  }

  /**
   * Receives positive healing from an attack with the item
   *
   * @param item
   *      Item which heals this unit
   */
  protected void receiveHealing(IEquipableItem item) {
    this.currentHitPoints = Math.min(this.currentHitPoints + item.getPower(), this.getMaxHitPoints());
  }

  @Override
  public void receiveAxeAttack(IEquipableItem item) {
    receiveAttack(item);
  }

  @Override
  public void receiveBowAttack(IEquipableItem item) {
    receiveAttack(item);
  }

  @Override
  public void receiveDarkBookAttack(IEquipableItem item) {
    receiveAttack(item);
  }

  @Override
  public void receiveLightBookAttack(IEquipableItem item) {
    receiveAttack(item);
  }

  @Override
  public void receiveSoulBookAttack(IEquipableItem item) {
    receiveAttack(item);
  }

  @Override
  public void receiveSpearAttack(IEquipableItem item) {
    receiveAttack(item);
  }

  @Override
  public void receiveStaffAttack(IEquipableItem item) {
    receiveHealing(item);
  }

  @Override
  public void receiveSwordAttack(IEquipableItem item) {
    receiveAttack(item);
  }
}
