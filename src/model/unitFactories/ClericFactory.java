package model.unitFactories;

import model.units.Cleric;

public class ClericFactory extends AbstractUnitFactory {

  @Override
  public Cleric createUnit() {
    return new Cleric(50, 2, invalidLocation);
  }

  @Override
  public Cleric createFullCustomUnit() {
    return new Cleric(maxHitPoints, movement, invalidLocation);
  }
}