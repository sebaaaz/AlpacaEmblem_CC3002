package model.items;

import model.units.IUnit;

/**
 * Abstract class that defines some common information and behaviour between all physical weapons.
 *
 * @author Sebastián Zapata Ascencio
 * @since 2.0
 */
public abstract class AbstractPhysicalWeapon extends AbstractWeapon {

  /**
   * Constructor for a default physical weapon.
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
  public AbstractPhysicalWeapon(String name, int power, int minRange, int maxRange) {
    super(name, power, minRange, maxRange);
  }

  @Override
  public void receiveMagicalAttack(IEquipableItem item) {
    receiveWeaknessAttack(item);
  }

  @Override
  public void useAgainst(IUnit unit) { unit.getEquippedItem().receivePhysicalAttack(this); }
}