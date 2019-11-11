package model.factories.unitFactory;

import model.map.InvalidLocation;
import model.units.Alpaca;
import model.units.IUnit;

/**
 * This class represents an <i>Alpaca factory</i> type factory.
 * <p>
 * They only return <i>Alpaca</i> units.
 *
 * @author Sebastián Zapata Ascencio
 * @since 2.0
 */
public class AlpacaFactory implements IUnitFactory {

  @Override
  public IUnit createUnit() {
    return new Alpaca(50, 2, new InvalidLocation());
  }

  @Override
  public IUnit createFullCustomUnit(int maxHitPoints, int movement) {
    return new Alpaca(maxHitPoints, movement, new InvalidLocation());
  }
}