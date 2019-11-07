package controller.Listeners;

import model.Tactician;

/**
 * This interface represents the <i>listeners</i> that the controller
 * has for handling Tactician events.
 */

public interface GameControllerListeners {

  /**
   * Subscribes this listener to a specific event of a tactician.
   * @param tactician
   *      the tactician that the listener will subscribe to
   */
  void subscribeTo(Tactician tactician);
}
