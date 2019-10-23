package model.items;

import model.units.IUnit;

/**
 * Abstract class that defines some common information and behaviour between all weapons.
 *
 * @author Sebastián Zapata Ascencio
 * @since 2.0
 */
public abstract class AbstractWeapon extends AbstractItem {

  /**
   * Constructor for a default weapon.
   *
   * @param name
   *     the name of the item
   * @param power
   *     the power of the item (this could be the amount of damage or healing the item does)
   * @param minRange
   *     the minimum range of the item
   * @param maxRange
   *     the maximum range of the item
   */
  public AbstractWeapon(String name, int power, int minRange, int maxRange) {
    super(name, power, minRange, maxRange);
  }

  @Override
  public void counterAttackTo(IUnit unit) { getOwner().useItemAgainst(unit); }

  @Override
  public void motivateCounterAttack(IUnit unit) { unit.counterAttack(getOwner()); }

}