package model.factories.unitFactory;

import model.map.InvalidLocation;
import model.units.Alpaca;
import model.units.IUnit;


public class AlpacaFactory implements IUnitFactory {

  @Override
  public IUnit createUnit() {
    return new Alpaca(50, 2, new InvalidLocation());
  }

  @Override
  public IUnit createFullCustomUnit(int maxHitPoints, int movement) {
    return new Alpaca(maxHitPoints, movement, new InvalidLocation());
  }
}