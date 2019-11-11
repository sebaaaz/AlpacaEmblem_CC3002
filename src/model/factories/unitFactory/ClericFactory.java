package model.factories.unitFactory;

import model.map.InvalidLocation;
import model.units.Cleric;
import model.units.IUnit;

/**
 * This class represents a <i>Cleric factory</i> type factory.
 * <p>
 * They only return <i>Cleric</i> units.
 *
 * @author Sebastián Zapata Ascencio
 * @since 2.0
 */
public class ClericFactory implements IUnitFactory {

  @Override
  public IUnit createUnit() {
    return new Cleric(50, 2, new InvalidLocation());
  }

  @Override
  public IUnit createFullCustomUnit(int maxHitPoints, int movement) {
    return new Cleric(maxHitPoints, movement, new InvalidLocation());
  }
}