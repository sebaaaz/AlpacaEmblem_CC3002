package model.items;

import model.units.IUnit;

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
  public boolean isNull() {
    return true;
  }

  @Override
  public void initUseOn(IUnit unit) {}

  @Override
  public void beAttacked(IEquipableItem item) {}

  @Override
  public void sendItemTypeAttack(IUnit unit) {}

  @Override
  public void sendSpecificEffect(IEquipableItem item) {}

  @Override
  public IEquipableItem itemOrThis(IEquipableItem item) {
    return item;
  }

  @Override
  public boolean equals(Object object) {
    return object instanceof NullItem;
  }
}
