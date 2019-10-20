package model.unitFactories;

import model.units.*;

public interface IUnitFactory {

  /**
   * Creates a unit with default parameters.
   * @return a default unit.
   */
  IUnit createUnit();

  /**
   * Creates a unit with all parameters setted by the player.
   * @return a custom unit.
   */
  IUnit createFullCustomUnit();

  /**
   * Sets the maximum hit points of the unit to be created.
   *
   * @param maxHitPoints
   *      the maximum hit points
   */
  void setMaxHitPoints(int maxHitPoints);

  /**
   * Sets the number of cells this unit can move.
   *
   * @param movement
   *      the number of cells
   */
  void setMovement(int movement);

  /**
   * Sets the initial location of this unit in the map.
   *
   * @param location
   *      the initial location
   */
  //void setLocation(Location location);

  /**
   * Sets the initial items of this unit.
   *
   * @param items
   *      the initial items
   */
  //void setItems(IEquipableItem items);
}
