package controller.Listeners;

import controller.GameController;
import model.Tactician;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class LHeroDies implements PropertyChangeListener, GameControllerListeners {

  private GameController controller;
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