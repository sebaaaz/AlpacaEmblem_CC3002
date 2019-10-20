package model.itemFactories;

public abstract class AbstractEquipableItemFactory implements IEquipableItemFactory {

  protected String name;
  protected int power;
  protected int minRange;
  protected int maxRange;

  @Override
  public void setName(String name) {
    this.name = name;
  }

  @Override
  public void setPower(int power) {
    this.power = power;
  }

  @Override
  public void setMinRange(int minRange) {
    this.minRange = minRange;
  }

  @Override
  public void setMaxRange(int maxRange) {
    this.maxRange = maxRange;
  }
}
