package model.items;

import model.units.IUnit;
import model.units.NullUnit;

/**
 * This class represents a Null Item.
 * <p>
 * Null items do not not affect the game in any way. They allow a better
 * handling of some item events.
 * <p>
 * Null items can't be created by a player.
 *
 * @author Sebastián Zapata Ascencio
 * @since 2.0
 */
public class NullItem extends AbstractItem {

  /**
   * Creates a new Null Item
   *
   * @param owner
   *      the owner of this item
   */
  public NullItem(IUnit owner) {
    super("Null Item", 0, 1, 1);
    setOwner(owner);
  }

  @Override
  public void equipTo(IUnit unit) {
    unit.setEquippedItem(this);
    this.setOwner(unit);
  }

  @Override
  public void useAgainst(IUnit unit) {
    // empty because Null item can't be used
  }

  @Override
  public void sendAttack(IEquipableItem item) {
    // empty because Null item can't attack
  }

  @Override
  public void counterAttackTo(IUnit unit) {
    // empty because Null item does not counter attack
  }

  @Override
  public void motivateCounterAttack(IUnit unit) {
    // empty because Null item does not allow other items counter attack it
  }

  @Override
  public boolean isNull() {
    return true;
  }

  @Override
  public IEquipableItem itemOrThis(IEquipableItem item) {
    return item;
  }

  @Override
  public boolean equals(Object object) {
    return object instanceof NullItem;
  }
}
