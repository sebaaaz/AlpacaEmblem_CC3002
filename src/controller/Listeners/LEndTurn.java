package controller.Listeners;

import controller.GameController;
import model.Tactician;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class LEndTurn implements PropertyChangeListener, GameControllerListeners {

  private GameController controller;

  public LEndTurn(GameController controller) {
    this.controller = controller;
  }

  @Override
  public void propertyChange(PropertyChangeEvent event) {
    controller.endTurn();
  }

  @Override
  public void subscribeTo(Tactician tactician) {
    tactician.addEndTurnListener(this);
  }
}
