package controller.Listeners;

import model.Tactician;

/**
 * This interface represents the <i>listeners</i> that the controller has to handle
 * Tactician events.
 * <p>
 * The signature for all the common methods of the listeners are defined here.
 */

public interface GameControllerListeners {

  /**
   * Subscribes this listener to a specific event of a tactician.
   * @param tactician
   *      the tactician that the listener will subscribe to
   */
  public void subscribeTo(Tactician tactician);

}
