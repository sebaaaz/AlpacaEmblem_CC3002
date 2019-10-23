package model.factories;

import model.factories.itemFactories.*;
import model.factories.unitFactories.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class FactoryTest {

  private List<IUnitFactory> unitFactories = new ArrayList<>();
  private List<IEquipableItemFactory> itemFactories = new ArrayList<>();

  /**
   * Sets up the factories to be tested
   */
  @BeforeEach
  public void setUp() {
    setUnitFactories();
    setItemFactories();
  }

  /**
   * Sets up the distinct unit factories and adds them to the unit factories list.
   */
  public void setUnitFactories() {
    unitFactories.add(new AlpacaFactory());
    unitFactories.add(new ArcherFactory());
    unitFactories.add(new ClericFactory());
    unitFactories.add(new FighterFactory());
    unitFactories.add(new HeroFactory());
    unitFactories.add(new SorcererFactory());
    unitFactories.add(new SwordMasterFactory());
  }

  /**
   * Sets up the distinct item factories and adds them to the item factories list.
   */
  public void setItemFactories() {
    itemFactories.add(new AxeFactory());
    itemFactories.add(new BowFactory());
    itemFactories.add(new DarkBookFactory());
    itemFactories.add(new LightBookFactory());
    itemFactories.add(new SoulBookFactory());
    itemFactories.add(new SpearFactory());
    itemFactories.add(new SwordFactory());
  }

  /**
   * Tests the unit factories and checks they do not return null units.
   */
  @Test
  public void testUnitFactories() {
    unitFactories.forEach(factory -> assertNotNull(factory.createUnit()));
    unitFactories.forEach(factory -> assertNotNull(factory.createFullCustomUnit(50, 2)));
  }

  /**
   * Tests the item factories and checks they do not return null items.
   */
  @Test
  public void testItemFactories() {
    itemFactories.forEach(factory -> assertNotNull(factory.createItem()));
    itemFactories.forEach(factory -> assertNotNull(factory.createCustomPowerItem("Common item", 10)));
    itemFactories.forEach(factory -> assertNotNull(factory.createFullCustomItem("Common item", 10, 1, 1)));
  }

}