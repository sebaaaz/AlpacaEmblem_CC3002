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
  public boolean isNull() { return false; }

  @Override
  public IEquipableItem itemOrThis(IEquipableItem item) {
    return this;
  }

  @Override
  public void receiveHealing(IEquipableItem item) {
    owner.setHitPoints( Math.min(owner.getHitPoints() + item.getPower(), owner.getMaxHitPoints()) );
  }

  @Override
  public void receiveNormalAttack(IEquipableItem item) {
    owner.setHitPoints( Math.max(owner.getHitPoints() - item.getPower(), 0) );
  }

  @Override
  public void receiveWeaknessAttack(IEquipableItem item) {
    int damage = (int) (item.getPower()*1.5);
    owner.setHitPoints( Math.max(owner.getHitPoints() - damage, 0) );
  }

  public void receiveResistantAttack(IEquipableItem item) {
    int damage = Math.max(item.getPower() - 20, 0);
    owner.setHitPoints( Math.max(owner.getHitPoints() - damage, 0) );
  }

  @Override
  public void receiveMagicalAttack(IEquipableItem item) { item.sendAttack(this); }

  @Override
  public void receivePhysicalAttack(IEquipableItem item) { item.sendAttack(this); }

  @Override
  public void receiveAxeAttack(IEquipableItem item) { receiveNormalAttack(item); }

  @Override
  public void receiveBowAttack(IEquipableItem item) { receiveNormalAttack(item); }

  @Override
  public void receiveDarkBookAttack(IEquipableItem item) { receiveNormalAttack(item); }

  @Override
  public void receiveLightBookAttack(IEquipableItem item) { receiveNormalAttack(item); }

  @Override
  public void receiveSoulBookAttack(IEquipableItem item) { receiveNormalAttack(item); }

  @Override
  public void receiveSpearAttack(IEquipableItem item) { receiveNormalAttack(item); }

  @Override
  public void receiveSwordAttack(IEquipableItem item) { receiveNormalAttack(item); }

  @Override
  public void receiveStaffHealing(IEquipableItem item) { receiveHealing(item); }
}