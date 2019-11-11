package model.factories.unitFactory;

import model.map.InvalidLocation;
import model.units.IUnit;
import model.units.SwordMaster;

/**
 * This class represents a <i>SwordMaster factory</i> type factory.
 * <p>
 * They only return <i>SwordMaster</i> units.
 *
 * @author Sebastián Zapata Ascencio
 * @since 2.0
 */
public class SwordMasterFactory implements IUnitFactory {

  @Override
  public IUnit createUnit() {
    return new SwordMaster(50, 2, new InvalidLocation());
  }

  @Override
  public IUnit createFullCustomUnit(int maxHitPoints, int movement) {
    return new SwordMaster(maxHitPoints, movement, new InvalidLocation());
  }
}