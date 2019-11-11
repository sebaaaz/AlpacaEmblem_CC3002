package model.factories.unitFactory;

import model.map.InvalidLocation;
import model.units.Archer;
import model.units.IUnit;

/**
 * This class represents an <i>Archer factory</i> type factory.
 * <p>
 * They only return <i>Archer</i> units.
 *
 * @author Sebastián Zapata Ascencio
 * @since 2.0
 */
public class ArcherFactory implements IUnitFactory {

  @Override
  public IUnit createUnit() {
    return new Archer(50, 2, new InvalidLocation());
  }

  @Override
  public IUnit createFullCustomUnit(int maxHitPoints, int movement) {
    return new Archer(maxHitPoints, movement, new InvalidLocation());
  }
}