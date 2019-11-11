package model.items;

import model.units.IUnit;

/**
 * This class represents a Bow.
 * <p>
 * Bows have a range not less than two.
 *
 * @author Ignacio Slater Muñoz
 * @author Sebastián Zapata Ascencio
 * @since 1.0
 */
public class Bow extends AbstractPhysicWeapon {

  /**
   * Creates a new bow.
   * <p>
   * Bows are weapons that can't attack adjacent units, so it's minimum range must me greater than
   * one.
   *
   * @param name
   *     the name of the bow
   * @param power
   *     the damage power of the bow
   * @param minRange
   *     the minimum range of the bow
   * @param maxRange
   *     the maximum range of the bow
   */
  public Bow(final String name, final int power, final int minRange, final int maxRange) {
    super(name, power, minRange, maxRange);
    this.setMinRange( Math.max(minRange, 2) );
    this.setMaxRange( Math.max(maxRange, this.getMinRange()) );
  }

  @Override
  public void sendSpecificEffect(IEquipableItem item) {
    item.receiveBowAttack(this);
  }

  @Override
  public void equipTo(IUnit unit) {
    unit.equipBow(this);
  }
}
