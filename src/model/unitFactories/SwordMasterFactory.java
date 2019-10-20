package model.unitFactories;

import model.units.SwordMaster;

public class SwordMasterFactory extends AbstractUnitFactory {

  @Override
  public SwordMaster createUnit() {
    return new SwordMaster(50, 2, invalidLocation);
  }

  @Override
  public SwordMaster createFullCustomUnit() {
    return new SwordMaster(maxHitPoints, movement, invalidLocation);
  }
}