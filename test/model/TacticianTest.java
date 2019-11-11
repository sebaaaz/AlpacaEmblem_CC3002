package model;

import model.factories.unitFactory.IUnitFactory;
import model.items.IEquipableItem;
import model.items.Sword;
import model.map.InvalidLocation;
import model.map.Location;
import model.units.Hero;
import model.units.IUnit;
import model.units.SwordMaster;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static model.factories.itemFactories.*;
import static model.factories.unitFactories.SWORD_MASTER_FACTORY;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test set for Tactician
 *
 * @author Sebastián Zapata Ascencio
 * @since 2.0
 */
public class TacticianTest {

  private Tactician tacticianTest = new Tactician("Test Player");
  private Tactician otherTactician = new Tactician("Test Player");

  private String expectedName;
  private IUnit unitTest;
  private IEquipableItem itemTest;
  private IUnitFactory unitFactoryTest;

  /**
   * Sets up the parameters to be tested
   */
  @BeforeEach
  public void setUp() {
    expectedName = "Test Player";
    unitTest = new SwordMaster(50, 2, new Location(0,0));
    itemTest = new Sword("Test Sword", 10, 1, 1);
    unitFactoryTest = SWORD_MASTER_FACTORY;
    unitTest.addItem(itemTest);
    unitTest.equipItem(itemTest);
  }

  /**
   * Tests the getter of the name of a Tactician.
   */
  @Test
  public void getNameTest() {
    assertEquals(expectedName, tacticianTest.getName());
  }

  /**
   * Tests the selection of units and items by a tactician.
   */
  @Test
  public void selectTest() {
    tacticianTest.selectItem(0);
    assertTrue(tacticianTest.getSelectedItem().isNull());
    tacticianTest.selectItem(-1);
    assertTrue(tacticianTest.getSelectedItem().isNull());

    tacticianTest.selectUnit(unitTest);
    assertEquals(unitTest, tacticianTest.getSelectedUnit());

    tacticianTest.selectItem(1);
    assertTrue(tacticianTest.getSelectedItem().isNull());
    tacticianTest.selectItem(0);
    assertEquals(itemTest, tacticianTest.getSelectedItem());
    tacticianTest.selectItem(1);
    assertEquals(itemTest, tacticianTest.getSelectedItem());
  }

  /**
   * Tests the equality between Tacticians.
   */
  @Test
  public void equalsTest() {
    assertEquals(tacticianTest, otherTactician);
    Tactician notEqualTactician = new Tactician("False Tactician");
    assertNotEquals(notEqualTactician, tacticianTest);
    assertNotEquals(notEqualTactician, otherTactician);
  }

  /**
   * Tests the getters of selected units and items.
   */
  @Test
  public void gettersSUnitSItemTest() {
    IUnit hero = new Hero(60, 5, new InvalidLocation());
    hero.setHitPoints(50);
    hero.addItem(SPEAR_FACTORY.createItem());
    hero.addItem(DARK_BOOK_FACTORY.createItem());
    tacticianTest.addUnit(hero);
    tacticianTest.selectUnit(hero);

    assertEquals(50, tacticianTest.getHitPointsSU());
    assertEquals(60, tacticianTest.getMaxHitPointsSU());
    assertEquals("Hero", tacticianTest.getNameSU());
    assertEquals(5, tacticianTest.getMovementSU());
    assertEquals(" Common Spear, Common Dark Book", tacticianTest.getItemsSU());
  }
}
