package model.factories.unitFactories;

import model.units.IUnit;

public interface IUnitFactory {

  /**
   * Creates an unit with default parameters.
   * @return a default unit.
   */
  IUnit createUnit();

  /**
   * Creates a unit with all parameters setted by the player.
   * @return a custom unit.
   */
  IUnit createFullCustomUnit(int maxHitPoints, int movement);
}