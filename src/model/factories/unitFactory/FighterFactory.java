package model.factories.unitFactory;

import model.map.InvalidLocation;
import model.units.Fighter;
import model.units.IUnit;

/**
 * This class represents a <i>Fighter factory</i> type factory.
 * <p>
 * They only return <i>Fighter</i> units.
 *
 * @author Sebastián Zapata Ascencio
 * @since 2.0
 */
public class FighterFactory implements IUnitFactory {

  @Override
  public IUnit createUnit() {
    return new Fighter(50, 2, new InvalidLocation());
  }

  @Override
  public IUnit createFullCustomUnit(int maxHitPoints, int movement) {
    return new Fighter(maxHitPoints, movement, new InvalidLocation());
  }
}