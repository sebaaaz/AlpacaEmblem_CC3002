package model;

import model.items.IEquipableItem;
import model.map.Location;
import model.units.IUnit;

public interface IUnitFactory {

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
  void setLocation(Location location);

  /**
   * Sets the initial items of this unit.
   *
   * @param items
   *      the initial items
   */
  void setItems(IEquipableItem items);


  void create();

  /**
   * @return an alpaca unit.
   */
  IUnit createAlpaca(String unitType);

  /**
   * @return an archer unit.
   */
  IUnit createArcher(String unitType);

  /**
   * @return a cleric unit.
   */
  IUnit createCleric(String unitType);

  /**
   * @return a fighter unit.
   */
  IUnit createFighter(String unitType);

  /**
   * @return a hero unit.
   */
  IUnit createHero(String unitType);

  /**
   * @return a sorcerer unit.
   */
  IUnit createSorcerer(String unitType);

  /**
   * @return a swordmaster unit.
   */
  IUnit createSwordMaster(String unitType);
}
