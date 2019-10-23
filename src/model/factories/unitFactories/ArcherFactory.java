package model.factories.unitFactories;

import model.map.InvalidLocation;
import model.units.Archer;
import model.units.IUnit;

public class ArcherFactory implements IUnitFactory {

  @Override
  public IUnit createUnit() {
    return new Archer(50, 2, new InvalidLocation());
  }

  @Override
  public IUnit createFullCustomUnit(int maxHitPoints, int movement) {
    return new Archer(maxHitPoints, movement, new InvalidLocation());
  }
}