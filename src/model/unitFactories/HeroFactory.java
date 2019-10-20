package model.unitFactories;

import model.units.Hero;

public class HeroFactory extends AbstractUnitFactory {

  @Override
  public Hero createUnit() {
    return new Hero(50, 2, invalidLocation);
  }

  @Override
  public Hero createFullCustomUnit() {
    return new Hero(maxHitPoints, movement, invalidLocation);
  }
}