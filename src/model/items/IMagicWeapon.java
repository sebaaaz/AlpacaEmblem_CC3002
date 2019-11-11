package model.items;

/**
 * This interface represents the <i>magic weapons</i> that the units of the game can use.
 * <p>
 * The signature for all the common methods of the magic weapons are defined here.
 * <p>
 * Every magic weapon is weak against <i>physic weapons</i> and weak/strong against some other <i>magical weapons</i>.
 *
 * @author Sebastián Zapata Ascencio
 * @since 2.0
 */
public interface IMagicWeapon
extends IWeaponItem {

  /**
   * Sends a magic attack to an item.
   *
   * @param item
   *      the item to be attacked with a magic attack.
   */
  void sendMagicAttack(IEquipableItem item);
}