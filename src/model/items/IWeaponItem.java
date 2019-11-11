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
   * Sends an attack to the item
   *
   * @param item
   *      the item that will receive the attack
   */
  void attackTo(IEquipableItem item);

  /**
   * Sends a counter-attack to the item
   *
   * @param item
   *      the item that will receive the counter-attack
   */
  void counterAttackTo(IEquipableItem item);

  /**
   * Sends the specific effect of this item
   *
   * @param item
   *      the item that will receive the specific effect of this item
   */
  void sendEffectAttackTo(IEquipableItem item);
}
