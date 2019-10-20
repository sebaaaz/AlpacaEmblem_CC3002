package model.unitFactories;

import model.units.Alpaca;

public class AlpacaFactory extends AbstractUnitFactory {

  @Override
  public Alpaca createUnit() {
    return new Alpaca(50, 2, invalidLocation);
  }

  @Override
  public Alpaca createFullCustomUnit() {
    return new Alpaca(maxHitPoints, movement, invalidLocation);
  }
}