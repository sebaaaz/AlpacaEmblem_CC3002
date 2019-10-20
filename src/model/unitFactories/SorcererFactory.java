package model.unitFactories;

import model.units.Sorcerer;

public class SorcererFactory extends AbstractUnitFactory {

  @Override
  public Sorcerer createUnit() {
    return new Sorcerer(50, 2, invalidLocation);
  }

  @Override
  public Sorcerer createFullCustomUnit() {
    return new Sorcerer(maxHitPoints, movement, invalidLocation);
  }
}