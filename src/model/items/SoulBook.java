package model.items;

import model.units.IUnit;

/**
 * This class represents a Soul Book.
 * <p>
 * Books can be equipped by <i>Sorcerers</i>.
 *
 * @author Sebastián Zapata Ascencio
 * @since 1.0
 */
public class SoulBook extends AbstractItem{

  /**
   * Creates a new Soul Book.
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
  public SoulBook(final String name, final int power, final int minRange, final int maxRange) {
    super(name, power, minRange, maxRange);
  }

  @Override
  public void equipTo(IUnit unit) { unit.equipSoulBook(this); }

  @Override
  public void useAgainst(IUnit unit) { unit.receiveSoulBookAttack(this); }
}
