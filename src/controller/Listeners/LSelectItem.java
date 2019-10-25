package controller.Listeners;

import controller.GameController;
import model.Tactician;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class LSelectItem implements PropertyChangeListener, GameControllerListeners {

  private GameController controller;
  public LSelectItem(GameController controller) {
    this.controller = controller;
  }

  @Override
  public void propertyChange(PropertyChangeEvent event) {
    controller.selectItem( (Integer) event.getNewValue());
  }

  @Override
  public void subscribeTo(Tactician tactician) {
    tactician.addSelectItemListener(this);
  }
}