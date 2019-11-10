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
public class Staff extends AbstractNonWeapon {

  /**
   * Creates a new Staff item.
   *
   * @param name     the name of the staff
   * @param power    the healing power of the staff
   * @param minRange the minimum range of the staff
   * @param maxRange the maximum range of the staff
   */
  public Staff(String name, int power, int minRange, int maxRange) {
    super(name, power, minRange, maxRange);
  }

  @Override
  public void sendSpecificEffect(IEquipableItem item) {
    item.receiveStaffHealing(this);
  }

  @Override
  public void receiveMagicalAttack(IMagicWeapon weapon) {
    receiveWeaknessAttack(weapon.getPower());
  }

  @Override
  public void equipTo(IUnit unit) {
    unit.equipStaff(this);
  }
}
