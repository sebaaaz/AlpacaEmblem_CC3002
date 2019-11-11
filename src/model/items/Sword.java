package model.items;

import model.units.IUnit;

/**
 * This class represents a sword type item.
 * <p>
 * Swords are strong against axes and weak against spears.
 *
 * @author Ignacio Slater Muñoz
 * @author Sebastián Zapata Ascencio
 * @since 1.0
 */
public class Sword extends AbstractPhysicWeapon {

  /**
   * Creates a new Sword.
   *
   * @param name
   *     the name that identifies the sword
   * @param power
   *     the base damage of the sword
   * @param minRange
   *     the minimum range of the sword
   * @param maxRange
   *     the maximum range of the sword
   */
  public Sword(String name, int power, int minRange, int maxRange) {
    super(name, power, minRange, maxRange);
  }

  @Override
  public void sendSpecificEffect(IEquipableItem item) {
    item.receiveSwordAttack(this);
  }

  @Override
  public void receiveAxeAttack(IPhysicWeapon weapon) {
    receiveResistantAttack(weapon.getPower());
  }

  @Override
  public void receiveSpearAttack(IPhysicWeapon weapon) {
    receiveWeaknessAttack(weapon.getPower());
  }

  @Override
  public void equipTo(IUnit unit) {
    unit.equipSword(this);
  }
}
