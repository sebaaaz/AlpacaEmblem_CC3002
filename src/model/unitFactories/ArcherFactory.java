package model.unitFactories;

import model.units.Archer;

public class ArcherFactory extends AbstractUnitFactory {

  @Override
  public Archer createUnit() {
    return new Archer(50, 2, invalidLocation);
  }

  @Override
  public Archer createFullCustomUnit() {
    return new Archer(maxHitPoints, movement, invalidLocation);
  }
}