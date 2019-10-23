package model;

import model.items.IEquipableItem;
import model.factories.unitFactories.IUnitFactory;
import model.items.NullItem;
import model.map.InvalidLocation;
import model.units.IUnit;
import model.units.NullUnit;

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

  private IUnitFactory unitFactory;

  private String name;
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
   * Adds a unit to the units list. This unit is created by the current factory.
   */
  public void addDefaultUnit() {
    units.add(unitFactory.createUnit());
  }

  /**
   * Adds a custom unit to the units list. This unit is created by the current factory.
   *
   * @param maxHitPoints
   *    the maximum hit points that the unit will have.
   * @param movement
   *    the amount of cells this unit can move in every turn.
   */
  public void addCustomUnit(int maxHitPoints, int movement) {
    units.add(unitFactory.createFullCustomUnit(maxHitPoints, movement));
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
    selectedUnit = unit;
  }

  /**
   * Selects an item.
   * @param index
   *      the index of the item in the items list.
   */
  public void selectItem(int index) {
    IEquipableItem item = selectedUnit.getItem(index);
    selectedItem = (item.isNull()) ? selectedItem : item;
  }

  @Override
  public boolean equals(Object object) {
    return object instanceof Tactician && this.getName().equals(((Tactician) object).getName());
  }
}
