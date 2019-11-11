package model.factories.unitFactory;

import model.map.InvalidLocation;
import model.units.Hero;
import model.units.IUnit;

/**
 * This class represents an <i>Hero factory</i> type factory.
 * <p>
 * They only return <i>Hero</i> units.
 *
 * @author Sebastián Zapata Ascencio
 * @since 2.0
 */
public class HeroFactory implements IUnitFactory {

  @Override
  public IUnit createUnit() {
    return new Hero(50, 2, new InvalidLocation());
  }

  @Override
  public IUnit createFullCustomUnit(int maxHitPoints, int movement) {
    return new Hero(maxHitPoints, movement, new InvalidLocation());
  }
}