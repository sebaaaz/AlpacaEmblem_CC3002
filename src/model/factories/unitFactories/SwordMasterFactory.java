package model.factories.unitFactories;

import model.map.InvalidLocation;
import model.units.IUnit;
import model.units.SwordMaster;

public class SwordMasterFactory implements IUnitFactory {

  @Override
  public IUnit createUnit() {
    return new SwordMaster(50, 2, new InvalidLocation());
  }

  @Override
  public IUnit createFullCustomUnit(int maxHitPoints, int movement) {
    return new SwordMaster(maxHitPoints, movement, new InvalidLocation());
  }
}