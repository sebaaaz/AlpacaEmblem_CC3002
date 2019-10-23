package model.items;

import model.units.IUnit;

/**
 * This class represents a sword type item.
 * <p>
 * Swords are strong against axes and weak against spears.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class Sword extends AbstractPhysicalWeapon {

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
  public void equipTo(IUnit unit) {
    unit.equipSword(this);
  }

  @Override
  public void sendAttack(IEquipableItem item) { item.receiveSwordAttack(this); }

  @Override
  public void receiveAxeAttack(IEquipableItem item) {
    receiveResistantAttack(item);
  }

  @Override
  public void receiveSpearAttack(IEquipableItem item) {
    receiveWeaknessAttack(item);
  }
}
