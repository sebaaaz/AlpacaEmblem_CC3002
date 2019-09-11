package model.items;

import model.units.IUnit;

/**
 * This class represents a Dark Book.
 * <p>
 * Books can be equipped by <i>Sorcerers</i>.
 *
 * @author Sebastián Zapata Ascencio
 * @since 1.0
 */
public class DarkBook extends AbstractItem implements IMagicBook {

  /**
   * Creates a new Dark Book.
   *
   * @param name
   *     the name that identifies the book
   * @param power
   *     the base damage of the book
   * @param minRange
   *     the minimum range of the book
   * @param maxRange
   *     the maximum range of the book
   */
  public DarkBook(final String name, final int power, final int minRange, final int maxRange) {
    super(name, power, minRange, maxRange);
  }

  @Override
  public void equipTo(IUnit unit) { unit.equipDarkBook(this); }

  @Override
  public void useAgainst(IUnit unit) {
    unit.receiveMagicalAttack(this);
  }

  @Override
  public void sendAttack(IUnit unit) { unit.receiveDarkBookAttack(this); }

  @Override
  public void receiveDarkAttack(IEquipableItem attack) {
    getOwner().receiveNormalAttack(attack);
  }

  @Override
  public void receiveLightAttack(IEquipableItem attack) {
    getOwner().receiveWeaknessAttack(attack);
  }

  @Override
  public void receiveSoulAttack(IEquipableItem attack) {
    getOwner().receiveResistantAttack(attack);
  }

  @Override
  public void sendMagicalAttack(IMagicBook book) {
    book.receiveDarkAttack(this);
  }
}
