package model.items;

/**
 * Abstract class that defines some common information and behaviour between all physical weapons.
 *
 * @author Sebastián Zapata Ascencio
 * @since 2.0
 */
public abstract class AbstractPhysicWeapon extends AbstractWeapon implements IPhysicWeapon {

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
  public AbstractPhysicWeapon(String name, int power, int minRange, int maxRange) {
    super(name, power, minRange, maxRange);
  }

  @Override
  public void sendEffectAttackTo(IEquipableItem item) {
    sendPhysicAttack(item);
  }

  @Override
  public void receivePhysicalAttack(IPhysicWeapon weapon) {
    weapon.sendSpecificEffect(this);
  }

  @Override
  public void receiveMagicalAttack(IMagicWeapon weapon) {
    receiveWeaknessAttack(weapon.getPower());
  }

  @Override
  public void sendPhysicAttack(IEquipableItem item) {
    item.receivePhysicalAttack(this);
  }
}