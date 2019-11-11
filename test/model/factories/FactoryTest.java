package model.factories;

import model.factories.itemFactory.IEquipableItemFactory;
import model.factories.unitFactory.IUnitFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static model.factories.unitFactories.*;
import static model.factories.itemFactories.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Test set for the factories of the game model.
 *
 * @author Sebastián Zapata Ascencio
 * @since 2.0
 */
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
    unitFactories.add(ALPACA_FACTORY);
    unitFactories.add(ARCHER_FACTORY);
    unitFactories.add(CLERIC_FACTORY);
    unitFactories.add(FIGHTER_FACTORY);
    unitFactories.add(HERO_FACTORY);
    unitFactories.add(SORCERER_FACTORY);
    unitFactories.add(SWORD_MASTER_FACTORY);
  }

  /**
   * Sets up the distinct item factories and adds them to the item factories list.
   */
  public void setItemFactories() {
    itemFactories.add(AXE_FACTORY);
    itemFactories.add(BOW_FACTORY);
    itemFactories.add(DARK_BOOK_FACTORY);
    itemFactories.add(LIGHT_BOOK_FACTORY);
    itemFactories.add(SOUL_BOOK_FACTORY);
    itemFactories.add(STAFF_FACTORY);
    itemFactories.add(SPEAR_FACTORY);
    itemFactories.add(SWORD_FACTORY);
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