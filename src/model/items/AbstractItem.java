package model.items;

import model.units.IUnit;

/**
 * Abstract class that defines some common information and behaviour between all items.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public abstract class AbstractItem implements IEquipableItem {

  private String name;
  private int power;
  private int minRange;
  private int maxRange;
  private IUnit owner;

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
  public AbstractItem(String name, int power, int minRange, int maxRange) {
    this.name = name;
    this.power = power;
    this.minRange = Math.max(minRange, 1);
    this.maxRange = Math.max(maxRange, this.minRange);
  }

  @Override
  public IUnit getOwner() {
    return owner;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public int getPower() {
    return power;
  }

  @Override
  public int getMinRange() {
    return minRange;
  }

  @Override
  public int getMaxRange() {
    return maxRange;
  }

  @Override
  public void setOwner(IUnit owner) { this.owner = owner; }

  @Override
  public void setName(String name) { this.name = name; }

  @Override
  public void setPower(int power) { this.power = power; }

  @Override
  public void setMinRange(int minRange) { this.minRange = minRange; }

  @Override
  public void setMaxRange(int maxRange) { this.maxRange = maxRange; }

  @Override
  public void beEquippedByOwner() { owner.setEquippedItem(this); }

  @Override
  public boolean isNull() { return false; }

  @Override
  public IEquipableItem itemOrThis(IEquipableItem item) {
    return this;
  }

  @Override
  public void receiveHealing(int power) {
    owner.setHitPoints( Math.min(owner.getHitPoints() + power, owner.getMaxHitPoints()) );
  }

  @Override
  public void receiveNormalAttack(int power) {
    owner.setHitPoints( Math.max(owner.getHitPoints() - power, 0) );
  }

  @Override
  public void receiveWeaknessAttack(int power) {
    int damage = Math.max(0, (int) (power*1.5));
    owner.setHitPoints( Math.max(owner.getHitPoints() - damage, 0) );
  }

  public void receiveResistantAttack(int power) {
    int damage = Math.max(0, power - 20);
    owner.setHitPoints( Math.max(owner.getHitPoints() - damage, 0) );
  }

  @Override
  public void receiveAxeAttack(IPhysicWeapon weapon) { receiveNormalAttack(weapon.getPower()); }
  @Override
  public void receiveBowAttack(IPhysicWeapon weapon) { receiveNormalAttack(weapon.getPower()); }
  @Override
  public void receiveDarkBookAttack(IMagicWeapon weapon) { receiveNormalAttack(weapon.getPower()); }
  @Override
  public void receiveLightBookAttack(IMagicWeapon weapon) { receiveNormalAttack(weapon.getPower()); }
  @Override
  public void receiveSoulBookAttack(IMagicWeapon weapon) { receiveNormalAttack(weapon.getPower()); }
  @Override
  public void receiveSpearAttack(IPhysicWeapon weapon) { receiveNormalAttack(weapon.getPower()); }
  @Override
  public void receiveSwordAttack(IPhysicWeapon weapon) { receiveNormalAttack(weapon.getPower()); }
  @Override
  public void receiveMagicalAttack(IMagicWeapon weapon) { receiveNormalAttack(weapon.getPower()); }
  @Override
  public void receivePhysicalAttack(IPhysicWeapon weapon) { receiveNormalAttack(weapon.getPower()); }
  @Override
  public void receiveStaffHealing(INonWeaponItem item) { receiveHealing(item.getPower()); }
}