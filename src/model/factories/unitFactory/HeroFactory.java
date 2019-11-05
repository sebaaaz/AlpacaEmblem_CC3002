package model.factories.unitFactory;

import model.map.InvalidLocation;
import model.units.Hero;
import model.units.IUnit;

public class HeroFactory implements IUnitFactory {

  @Override
  public IUnit createUnit() {
    return new Hero(50, 2, new InvalidLocation());
  }

  @Override
  public IUnit createFullCustomUnit(int maxHitPoints, int movement) {
    return new Hero(maxHitPoints, movement, new InvalidLocation());
  }
}