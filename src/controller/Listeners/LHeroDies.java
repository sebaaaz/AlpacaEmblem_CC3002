package controller.Listeners;

import controller.GameController;
import model.Tactician;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * This class represents a listener of the "Hero of Tactician died" event.
 * <p>
 * The <i>GameController</i> uses this listener for handling that event.
 *
 * @author Sebastián Zapata Ascencio
 * @since 2.0
 */
public class LHeroDies implements PropertyChangeListener, GameControllerListeners {

  private GameController controller;

  /**
   * Creates a new LHeroDies listener.
   *
   * @param controller
   *      the controller that has this listener.
   */
  public LHeroDies(GameController controller) {
    this.controller = controller;
  }

  @Override
  public void propertyChange(PropertyChangeEvent event) {
    controller.removeTactician((String) event.getNewValue());
  }

  @Override
  public void subscribeTo(Tactician tactician) {
    tactician.addHeroDiesListener(this);
  }
}