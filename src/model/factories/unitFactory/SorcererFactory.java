package model.factories.unitFactory;

import model.map.InvalidLocation;
import model.units.IUnit;
import model.units.Sorcerer;

public class SorcererFactory implements IUnitFactory {

  @Override
  public IUnit createUnit() {
    return new Sorcerer(50, 2, new InvalidLocation());
  }

  @Override
  public IUnit createFullCustomUnit(int maxHitPoints, int movement) {
    return new Sorcerer(maxHitPoints, movement, new InvalidLocation());
  }
}