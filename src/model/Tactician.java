package model;

import controller.Listeners.GameControllerListeners;
import model.factories.itemFactories.IEquipableItemFactory;
import model.items.IEquipableItem;
import model.factories.unitFactories.IUnitFactory;
import model.items.NullItem;
import model.units.IUnit;
import model.units.NullUnit;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

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
      endTurnEvent = new PropertyChangeSupport(this),
      heroDiesEvent = new PropertyChangeSupport(this),
      selectUnitEvent = new PropertyChangeSupport(this),
      selectItemEvent = new PropertyChangeSupport(this);

  private IUnitFactory unitFactory;
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
    selectedUnit = new NullUnit();
    selectedItem = new NullItem(selectedUnit);
  }

  /**
   * Sets a new unit factory for this tactician.
   *
   * @param anUnitFactory
   *      the unit factory to be setted.
   */
  public void unitFactory(IUnitFactory anUnitFactory) {
    unitFactory = anUnitFactory;
  }

  /**
   * Adds a unit to the units list. This unit is created by the current factory and
   * then is selected.
   */
  public void addDefaultUnit() {
    units.add(unitFactory.createUnit());
    selectUnit(units.get(units.size()-1));
  }

  /**
   * Adds a custom unit to the units list. This unit is created by the current factory
   * and then is selected.
   *
   * @param maxHitPoints
   *    the maximum hit points that the unit will have.
   * @param movement
   *    the amount of cells this unit can move in every turn.
   */
  public void addCustomUnit(int maxHitPoints, int movement) {
    units.add(unitFactory.createFullCustomUnit(maxHitPoints, movement));
    selectUnit(units.get(units.size()-1));
  }

  /**
   * Removes a unit from the units list.
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
    selectUnitEvent.firePropertyChange(new PropertyChangeEvent(this, "New unit selected", getSelectedUnit(), unit));
    selectedUnit = unit;
  }

  /**
   * Selects an item.
   * @param index
   *      the index of the item in the items list.
   */
  public void selectItem(int index) {
    selectedItem = selectedUnit.getItem(index).itemOrThis(selectedItem);
    selectItemEvent.firePropertyChange(new PropertyChangeEvent(this, "New item selected", null, index));
  }

  // Listeners

  public void addListener(GameControllerListeners listener) {
    listener.subscribeTo(this);
  }
  public void addEndTurnListener(PropertyChangeListener listener) {
    endTurnEvent.addPropertyChangeListener(listener);
  }
  public void addHeroDiesListener(PropertyChangeListener listener) {
    heroDiesEvent.addPropertyChangeListener(listener);
  }
  public void addSelectUnitListener(PropertyChangeListener listener) {
    selectUnitEvent.addPropertyChangeListener(listener);
  }
  public void addSelectItemListener(PropertyChangeListener listener) {
    selectItemEvent.addPropertyChangeListener(listener);
  }

  /**
   * Ends the turn of this tactician.
   */
  public void endTurn() {
    endTurnEvent.firePropertyChange(new PropertyChangeEvent(this, "Ended turn", null, null));
  }

  /**
   * Notifies to the listener of this event that a hero of this tactician died.
   */
  public void notifyHeroDefeated() {
    heroDiesEvent.firePropertyChange(new PropertyChangeEvent(this, "Hero died", null, getName()));
  }

  @Override
  public boolean equals(Object object) {
    return object instanceof Tactician && this.getName().equals(((Tactician) object).getName());
  }
}
