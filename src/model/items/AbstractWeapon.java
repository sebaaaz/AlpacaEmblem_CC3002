package model.items;

import model.units.IUnit;

/**
 * Abstract class that defines some common information and behaviour between all weapons.
 *
 * @author Sebastián Zapata Ascencio
 * @since 2.0
 */
public abstract class AbstractWeapon extends AbstractItem implements IWeaponItem {

  /**
   * Constructor for a default weapon.
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
  public AbstractWeapon(String name, int power, int minRange, int maxRange) {
    super(name, power, minRange, maxRange);
  }

  @Override
  public void initUseOn(IUnit unit) {
    this.sendSpecificEffect(unit.getEquippedItem());
    unit.getEquippedItem().beAttacked(this);
  }

  @Override
  public void sendItemTypeAttack(IUnit unit) {
    attackTo(unit.getEquippedItem());
  }

  @Override
  public void beAttacked(IEquipableItem item) {
    if (item.getOwner().getHitPoints() > 0) counterAttackTo(item);
  }

  @Override
  public void attackTo(IEquipableItem item) {
    sendEffectAttackTo(item);
  }

  @Override
  public void counterAttackTo(IEquipableItem item) {
    this.sendSpecificEffect(item);
  }
}