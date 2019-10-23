package model.factories.unitFactories;

import model.map.InvalidLocation;
import model.units.Cleric;
import model.units.IUnit;

public class ClericFactory implements IUnitFactory {

  @Override
  public IUnit createUnit() {
    return new Cleric(50, 2, new InvalidLocation());
  }

  @Override
  public IUnit createFullCustomUnit(int maxHitPoints, int movement) {
    return new Cleric(maxHitPoints, movement, new InvalidLocation());
  }
}