package model.factories.unitFactory;

import model.units.IUnit;

public interface IUnitFactory {

  /**
   * Creates an unit with default parameters.
   *
   * @return a default unit.
   */
  IUnit createUnit();

  /**
   * Creates a unit with all parameters setted by the player.
   *
   * @param maxHitPoints
   *      maximum hit points of the unit
   * @param movement
   *      the amount of cells this unit can move
   *
   * @return a custom unit.
   */
  IUnit createFullCustomUnit(int maxHitPoints, int movement);
}