package model.unitFactories;

import model.items.IEquipableItem;
import model.map.InvalidLocation;
import model.units.*;

public abstract class AbstractUnitFactory implements IUnitFactory {

  protected int maxHitPoints;
  protected int movement;
  protected IEquipableItem items;
  protected InvalidLocation invalidLocation = new InvalidLocation();

  @Override
  public void setMaxHitPoints(int maxHitPoints) { this.maxHitPoints = maxHitPoints; }
  @Override
  public void setMovement(int movement) { this.movement = movement; }
}
