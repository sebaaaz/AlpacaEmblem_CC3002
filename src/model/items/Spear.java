package model.items;

import model.units.IUnit;

/**
 * This class represents a <i>spear</i>.
 * <p>
 * Spears are strong against swords and weak against axes
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class Spear extends AbstractItem {

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
  public Spear(final String name, final int power, final int minRange, final int maxRange) {
    super(name, power, minRange, maxRange);
  }

  @Override
  public void equipTo(IUnit unit) { unit.equipSpear(this); }

  @Override
  public void useAgainst(IUnit unit) {
    unit.receivePhysicalAttack(this);
  }

  @Override
  public void sendAttack(IUnit unit) { unit.receiveSpearAttack(this); }
}
