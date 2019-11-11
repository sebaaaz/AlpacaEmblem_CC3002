package model.factories.unitFactory;

import model.map.InvalidLocation;
import model.units.IUnit;
import model.units.Sorcerer;

/**
 * This class represents a <i>Sorcerer factory</i> type factory.
 * <p>
 * They only return <i>Sorcerer</i> units.
 *
 * @author Sebastián Zapata Ascencio
 * @since 2.0
 */
public class SorcererFactory implements IUnitFactory {

  @Override
  public IUnit createUnit() {
    return new Sorcerer(50, 2, new InvalidLocation());
  }

  @Override
  public IUnit createFullCustomUnit(int maxHitPoints, int movement) {
    return new Sorcerer(maxHitPoints, movement, new InvalidLocation());
  }
}