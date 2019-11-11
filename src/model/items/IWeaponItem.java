package model.items;

/**
 * This interface represents the <i>weapons</i> that the units of the game can use.
 * <p>
 * The signature for all the common methods of the weapons are defined here.
 * Every item has a base damage and can attack and counter-attack.
 *
 * @author Sebastián Zapata Ascencio
 * @since 2.0
 */
public interface IWeaponItem
extends IEquipableItem {

  /**
   * Sends the specific attack of this item
   *
   * @param item
   *      the item that will receive the specific attack of this item
   */
  void sendEffectAttackTo(IEquipableItem item);
}
