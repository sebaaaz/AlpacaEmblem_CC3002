package model.items;

import model.units.IUnit;

/**
 * Abstract class that defines some common information and behaviour between all non weapons.
 *
 * @author Sebastián Zapata Ascencio
 * @since 2.0
 */
public abstract class AbstractNonWeapon extends AbstractItem implements INonWeaponItem {

  /**
   * Constructor for a default non weapon.
   *
   * @param name
   *     the name of the item
   * @param power
   *     the power of the item
   * @param minRange
   *     the minimum range of the item
   * @param maxRange
   *     the maximum range of the item
   */
  public AbstractNonWeapon(String name, int power, int minRange, int maxRange) {
    super(name, power, minRange, maxRange);
  }

  @Override
  public void initUseOn(IUnit unit) {
    this.sendSpecificEffect(unit.getEquippedItem());
  }

  @Override
  public void sendItemTypeAttack(IUnit unit) {
    sendSpecificEffect(unit.getEquippedItem());
  }

  @Override
  public void beAttacked(IEquipableItem item) {
    // this item does not trigger any event
  }
}