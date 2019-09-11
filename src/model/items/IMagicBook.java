package model.items;

/**
 * This interface represents the <i>magic books</i> that certain units of the game can
 * use, like <i>sorcerers</i>.
 * <p>
 * These magic books are weak and strong against other magic books.
 *
 * @author Sebastián Zapata Ascencio
 * @since 1.0
 */
public interface IMagicBook {

  /**
   * Receives a dark book attack and the owner of this book will receive damage through its item.
   *
   * @param attack
   *      the magical attack that the owner of this book will receive
   */
  void receiveDarkAttack(IEquipableItem attack);

  /**
   * Receives a light book attack and the owner of this book will receive damage through its item.
   *
   * @param attack
   *      the magical attack that the owner of this book will receive
   */
  void receiveLightAttack(IEquipableItem attack);

  /**
   * Receives a soul book attack and the owner of this book will receive damage through its item.
   *
   * @param attack
   *      the magical attack that the owner of this book will receive
   */
  void receiveSoulAttack(IEquipableItem attack);

  /**
   * Sends the specific magical attack of this book to the other book so the owner can
   * receive the damage.
   *
   * @param book
   *      the book that will receive the specific magic attack
   */
  void sendMagicalAttack(IMagicBook book);
}
