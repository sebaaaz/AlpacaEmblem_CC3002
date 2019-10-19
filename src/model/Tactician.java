package model;

import model.items.IEquipableItem;
import model.units.IUnit;

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
   * @param name the name of this player
   */
  public Tactician(String name) {
    this.name = name;
  }

  @Override
  public boolean equals(Object o) {
    return o instanceof Tactician && this.getName().equals(((Tactician) o).getName());
  }

  public void unitFactory(IUnitFactory anUnitFactory) {
    unitFactory = anUnitFactory;
  }

  public void addUnit(IUnit unit) {
    units.add(unitFactory.create());
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
   * @param unit
   *      the unit to be selected by the player
   */
  public void selectUnit(IUnit unit) {
    selectedUnit = unit;
  }

  public void selectItem(int index) {
    selectedItem = selectedUnit.getItems().get(index);
  }

  /**
   * @param unit
   *      the unit to be added to the tactician.
   */
  public void addUnit(IUnit unit) {
    units.add(unit);
  }


}
