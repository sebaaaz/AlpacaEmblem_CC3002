package model.items;

import model.units.IUnit;

/**
 * This class represents a Light Book.
 * <p>
 * Books can be equipped by <i>Sorcerers</i>.
 *
 * @author Sebastián Zapata Ascencio
 * @since 1.0
 */
public class LightBook extends AbstractMagicWeapon {

  /**
   * Creates a new Light Book.
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
  public LightBook(String name, int power, int minRange, int maxRange) {
    super(name, power, minRange, maxRange);
  }

  @Override
  public void equipTo(IUnit unit) { unit.equipLightBook(this); }

  @Override
  public void sendAttack(IEquipableItem item) { item.receiveLightBookAttack(this); }

  @Override
  public void receiveDarkBookAttack(IEquipableItem item) {
    receiveResistantAttack(item);
  }

  @Override
  public void receiveSoulBookAttack(IEquipableItem item) {
    receiveWeaknessAttack(item);
  }
}
