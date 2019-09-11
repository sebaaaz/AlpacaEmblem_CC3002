package model.items;

import model.units.IUnit;

/**
 * This class represents a <i>Staff</i> type item.
 * <p>
 * A staff is an item that can heal other units but cannot counter any attack
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class Staff extends AbstractItem {

  /**
   * Creates a new Staff item.
   *
   * @param name
   *     the name of the staff
   * @param power
   *     the healing power of the staff
   * @param minRange
   *     the minimum range of the staff
   * @param maxRange
   *     the maximum range of the staff
   */
  public Staff(final String name, final int power, final int minRange, final int maxRange) {
    super(name, power, minRange, maxRange);
  }

  @Override
  public void equipTo(IUnit unit) { unit.equipStaff(this); }

  @Override
  public void useAgainst(IUnit unit) {
    unit.receiveStaffHealing(this);
  }

  @Override
  public void sendAttack(IUnit unit) {}

  @Override
  public void counterAttackTo(IUnit unit) {
    // empty because Staff item does not counter attack
  }

  @Override
  public void motivateCounterAttack(IUnit unit) {
    // empty because Staff item does not allow other items counter attack it
  }
}
