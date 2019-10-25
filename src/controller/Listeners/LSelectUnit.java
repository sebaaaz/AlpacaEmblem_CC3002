package controller.Listeners;

import controller.GameController;
import model.Tactician;
import model.units.IUnit;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class LSelectUnit implements PropertyChangeListener, GameControllerListeners {

  private GameController controller;
  public LSelectUnit(GameController controller) {
    this.controller = controller;
  }

  @Override
  public void propertyChange(PropertyChangeEvent event) {
    controller.setSelectedUnit( (IUnit) event.getNewValue());
  }

  @Override
  public void subscribeTo(Tactician tactician) {
    tactician.addSelectUnitListener(this);
  }
}