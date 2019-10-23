package model.items;

import model.units.IUnit;

/**
 * Abstract class that defines some common information and behaviour between all non weapons.
 *
 * @author Sebastián Zapata Ascencio
 * @since 2.0
 */
public abstract class AbstractNonWeapon extends AbstractItem {

  /**
   * Constructor for a default non weapon.
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
  public AbstractNonWeapon(String name, int power, int minRange, int maxRange) {
    super(name, power, minRange, maxRange);
  }

  @Override
  public void receiveMagicalAttack(IEquipableItem item) {
    receiveWeaknessAttack(item);
  }

  @Override
  public void sendAttack(IEquipableItem item) {
    // empty because Staff item does not attack
  }

  @Override
  public void counterAttackTo(IUnit unit) {
    // empty because Staff item does not counter attack
  }

  @Override
  public void motivateCounterAttack(IUnit unit) {
    // empty because Staff item does not allow other items counter attack it
  }
}