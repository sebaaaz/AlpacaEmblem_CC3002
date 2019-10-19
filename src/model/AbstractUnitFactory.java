package model;

import model.items.IEquipableItem;
import model.map.Location;
import model.units.*;

public abstract class AbstractUnitFactory implements IUnitFactory {

  private int maxHitPoints;
  private int movement;
  private Location location;
  private IEquipableItem items;

  @Override
  public void setMaxHitPoints(int maxHitPoints) { this.maxHitPoints = maxHitPoints; }
  @Override
  public void setMovement(int movement) { this.movement = movement; }
  @Override
  public void setLocation(Location location) { this.location = location; }
  @Override
  public void setItems(IEquipableItem items) { this.items = items; }

  @Override
  public Alpaca createAlpaca() {
    return null;
  }
  @Override
  public Archer createArcher() {
    return null;
  }
  @Override
  public Cleric createCleric() {
    return null;
  }
  @Override
  public Fighter createFighter() {
    return null;
  }
  @Override
  public Hero createHero() {
    return null;
  }
  @Override
  public Sorcerer createSorcerer() {
    return null;
  }
  @Override
  public SwordMaster createSwordMaster() {
    return null;
  }
}
