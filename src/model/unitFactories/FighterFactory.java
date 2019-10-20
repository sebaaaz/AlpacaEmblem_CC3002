package model.unitFactories;

import model.units.Fighter;

public class FighterFactory extends AbstractUnitFactory {

  @Override
  public Fighter createUnit() {
    return new Fighter(50, 2, invalidLocation);
  }

  @Override
  public Fighter createFullCustomUnit() {
    return new Fighter(maxHitPoints, movement, invalidLocation);
  }
}