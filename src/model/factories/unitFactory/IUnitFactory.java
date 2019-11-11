package model.factories.unitFactory;

import model.units.IUnit;

/**
 * This interface represents all unit factories in the game.
 * <p>
 * The signature of all the common methods that a factory can execute are defined here. All
 * unit factories can create only units that inherit from <i>IUnit</i>.
 *
 * @author Sebastián Zapata Ascencio
 * @since 2.0
 */
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