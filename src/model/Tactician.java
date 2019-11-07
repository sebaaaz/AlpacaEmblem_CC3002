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
   * @param index
   *      the index of the unit in the units list.
   */
  public void removeUnit(int index) {
    if ( index >= 0 && index < getUnits().size() ) units.remove(index);
  }

  /**
   * Removes all the units of the tactician.
   */
  public void removeAllUnits() {units = new ArrayList<>(); }

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
   * Moves the selected unit to another location.
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

  // Listeners

  public void addListener(GameControllerListeners listener) {
    listener.subscribeTo(this);
  }
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
