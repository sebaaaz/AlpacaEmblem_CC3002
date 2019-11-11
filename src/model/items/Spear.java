package model.items;

import model.units.IUnit;

/**
 * This class represents a <i>spear</i>.
 * <p>
 * Spears are strong against swords and weak against axes
 *
 * @author Ignacio Slater Muñoz
 * @author Sebastián Zapata Ascencio
 * @since 1.0
 */
public class Spear extends AbstractPhysicWeapon {

  /**
   * Creates a new Spear item
   *
   * @param name
   *     the name of the Spear
   * @param power
   *     the damage of the Spear
   * @param minRange
   *     the minimum range of the Spear
   * @param maxRange
   *     the maximum range of the Spear
   */
  public Spear(String name, int power, int minRange, int maxRange) {
    super(name, power, minRange, maxRange);
  }

  @Override
  public void sendSpecificEffect(IEquipableItem item) {
    item.receiveSpearAttack(this);
  }

  @Override
  public void receiveAxeAttack(IPhysicWeapon weapon) {
    receiveWeaknessAttack(weapon.getPower());
  }

  @Override
  public void receiveSwordAttack(IPhysicWeapon weapon) {
    receiveResistantAttack(weapon.getPower());
  }

  @Override
  public void equipTo(IUnit unit) { unit.equipSpear(this); }
}
