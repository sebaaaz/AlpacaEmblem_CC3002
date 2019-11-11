package model.items;

import model.units.IUnit;

/**
 * This class represents an Axe.
 * <p>
 * Axes are strong against spears but weak against swords.
 *
 * @author Ignacio Slater Muñoz
 * @author Sebastián Zapata Ascencio
 * @since 1.0
 */
public class Axe extends AbstractPhysicWeapon {

  /**
   * Creates a new Axe item
   *
   * @param name
   *     the name of the Axe
   * @param power
   *     the damage of the axe
   * @param minRange
   *     the minimum range of the axe
   * @param maxRange
   *     the maximum range of the axe
   */
  public Axe(final String name, final int power, final int minRange, final int maxRange) {
    super(name, power, minRange, maxRange);
  }

  @Override
  public void sendSpecificEffect(IEquipableItem item) {
    item.receiveAxeAttack(this);
  }

  @Override
  public void receiveSwordAttack(IPhysicWeapon weapon) {
    receiveWeaknessAttack(weapon.getPower());
  }

  @Override
  public void receiveSpearAttack(IPhysicWeapon weapon) {
    receiveResistantAttack(weapon.getPower());
  }

  @Override
  public void equipTo(IUnit unit) { unit.equipAxe(this); }
}
