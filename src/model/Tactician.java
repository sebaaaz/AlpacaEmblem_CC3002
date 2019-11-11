package model;

import controller.Listeners.GameControllerListeners;
import model.factories.itemFactory.IEquipableItemFactory;
import model.items.IEquipableItem;
import model.items.NullItem;
import model.map.Location;
import model.units.IUnit;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

import static model.units.NullUnit.NULL_UNIT;

/**
 * This class represents a player of the game.
 * <p>
 * A <i>Tactician</i> handles all the instructions of the player and
 * delegates messages to the objects of the model.
 *
 * @author Sebastián Zapata Ascencio
 * @since 2.0
 */
public class Tactician {

  private PropertyChangeSupport
      heroDiesEvent = new PropertyChangeSupport(this);

  private IEquipableItemFactory itemFactory;
  private final String name;
  private List<IUnit> units = new ArrayList<>();
  private IUnit selectedUnit;
  private IEquipableItem selectedItem;

  /**
   * Creates a new Tactician/Player
   *
   * @param name
   *      the name of this player
   */
  public Tactician(String name) {
    this.name = name;
    selectedUnit = NULL_UNIT;
    selectedItem = new NullItem(selectedUnit);
  }

  /**
   * Adds an unit to the units list.
   *
   * @param unit
   *      the unit to be added.
   */
  public void addUnit(IUnit unit) {
    units.add(unit);
    unit.setOwner(this);
  }

  /**
   * Removes an unit from the units list.
   *
   * @param unit
   *      the unit in the units list.
   */
  public void removeUnit(IUnit unit) {
    getUnits().remove(unit);
  }

  /**
   * Removes all the units of the tactician, setting them as defeated.
   * <p>
   * This removes all units of the unit list.
   */
  public void defeatAllUnits() {
    while (getUnits().size() > 0) {
      getUnits().get(0).toBeDefeated();
    }
  }

  /**
   * @return true if all units from the unit list are in
   *      a valid position.
   */
  public boolean allUnitsAllocated() {
    for (IUnit unit : units) {
      if (!unit.isOnValidLocation()) return false;
    }
    return true;
  }

  /**
   * Allows the movement of all the units.
   */
  public void allowMovementAllUnits() {
    for (IUnit unit : units) {
      unit.allowMovement();
    }
  }

  /**
   * @return the name of this tactician.
   */
  public String getName() { return name; }

  /**
   * @return the selected unit of this tactician.
   */
  public IUnit getSelectedUnit() { return selectedUnit; }

  /**
   * @return the selected item of this tactician.
   */
  public IEquipableItem getSelectedItem() { return selectedItem; }

  /**
   * @return the alive units of this tactician.
   */
  public List<IUnit> getUnits() { return units; }

  /**
   * Selects a unit.
   * @param unit
   *      the unit to be selected by the player
   */
  public void selectUnit(IUnit unit) {
    selectedUnit = unit;
  }

  /**
   * Selects an item.
   * @param index
   *      the index of the item in the items list.
   */
  public void selectItem(int index) {
    selectedItem = selectedUnit.getItem(index).itemOrThis(selectedItem);
  }

  /**
   * Sets a new location for the selected unit.
   *
   * @param location
   *      the location for the unit
   */
  public void setSelectedUnitLocation(Location location) {
    selectedUnit.setLocation(location);
  }

  /**
   * Moves the selected unit to another location on the game map.
   * <p>
   * If the other location is out of this unit's movement range,
   * the unit doesn't move.
   *
   * @param targetLocation
   *      the new location where the unit will move on
   */
  public void moveSelectedUnit(final Location targetLocation) {
    getSelectedUnit().moveTo(targetLocation);
  }

  /**
   * Equips the item to the selected unit.
   *
   * @param item
   *      the item to be equipped to the selected unit.
   */
  public void equipItemToSelectedUnit(IEquipableItem item) {
    getSelectedUnit().equipItem(item);
  }

//  /**
//   * @return the stats of the selected unit.
//   */
//  public String seeSelectedUnitStats() {
//    StringBuilder stats = new StringBuilder();
//    stats.setLength(0);
//    stats.append(getNameSU()).append("\n");
//    stats.append("Hit Points: ").append(getHitPointsSU()).append("\n");
//    stats.append("Max Hit Points: ").append(getMaxHitPointsSU()).append("\n");
//    stats.append("Movement: ").append(getMovementSU()).append("\n");
//    stats.append("Items: ");
//    for (IEquipableItem item : selectedUnit.getItems()) {
//      stats.append(item.getName()).append(", ");
//    }
//    if (selectedUnit.getItems().size() > 0) stats.deleteCharAt(stats.lastIndexOf(","));
//    return stats.toString();
//  }

  // IUnit getters
  /**
   * @return hit points of the selected unit.
   */
  public int getHitPointsSU() { return getSelectedUnit().getHitPoints(); }
  /**
   * @return max hit points of the selected unit.
   */
  public int getMaxHitPointsSU() { return getSelectedUnit().getMaxHitPoints(); }
  /**
   * @return the movement of the selected unit.
   */
  public int getMovementSU() { return getSelectedUnit().getMovement(); }
  /**
   * @return the name of the type of the selected unit.
   */
  public String getNameSU() { return getSelectedUnit().getName(); }
  /**
   * @return the items of the selected unit.
   */
  public String getItemsSU() {
    StringBuilder items = new StringBuilder();
    items.setLength(0);
    for (IEquipableItem item : selectedUnit.getItems()) {
      items.append(" ").append(item.getName()).append(",");
    }
    if (selectedUnit.getItems().size() > 0) items.deleteCharAt(items.lastIndexOf(","));
    return items.toString();
  }

  // IEquipableItem getters

  /**
   * @return the name of the selected item.
   */
  public String getNameSI() { return getSelectedItem().getName(); }
  /**
   * @return the power of the selected item.
   */
  public int getPowerSI() { return getSelectedItem().getPower(); }
  /**
   * @return the minRange of the selected item.
   */
  public int getMinRangeSI() { return getSelectedItem().getMinRange(); }
  /**
   * @return the maxRange of the selected item.
   */
  public int getMaxRangeSI() { return getSelectedItem().getMaxRange(); }

  // Listeners

  /**
   * Adds a listener to the listeners of this tactician for handling some events.
   *
   * @param listener
   *      the listener to be added to this tactician
   */
  public void addListener(GameControllerListeners listener) {
    listener.subscribeTo(this);
  }

  /**
   * Adds a <i>HeroDiesListener</i> to the listeners of this tactician.
   *
   * @param listener
   *      the <i>HeroDiesListener</i> to be added.
   */
  public void addHeroDiesListener(PropertyChangeListener listener) {
    heroDiesEvent.addPropertyChangeListener(listener);
  }

  /**
   * Notifies to the listener of this event that a hero of this tactician died.
   */
  public void notifyHeroDefeated() {
    heroDiesEvent.firePropertyChange(new PropertyChangeEvent(this, "Hero of " + getName() + " died.", null, getName()));
  }


  @Override
  public boolean equals(Object object) {
    return object instanceof Tactician && this.getName().equals(((Tactician) object).getName());
  }
}
