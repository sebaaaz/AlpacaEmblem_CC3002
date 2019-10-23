package model.items;

import model.units.IUnit;

/**
 * Abstract class that defines some common information and behaviour between all magic weapons.
 *
 * @author Sebastián Zapata Ascencio
 * @since 2.0
 */
public abstract class AbstractMagicWeapon extends AbstractWeapon {

  /**
   * Constructor for a default item without any special behaviour.
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
  public AbstractMagicWeapon(String name, int power, int minRange, int maxRange) {
    super(name, power, minRange, maxRange);
  }

  @Override
  public void receivePhysicalAttack(IEquipableItem item) {
    receiveWeaknessAttack(item);
  }

  @Override
  public void useAgainst(IUnit unit) { unit.getEquippedItem().receiveMagicalAttack(this); }
}