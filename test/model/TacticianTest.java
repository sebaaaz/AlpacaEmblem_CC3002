package model;

import model.factories.unitFactories.*;
import model.items.IEquipableItem;
import model.items.Sword;
import model.map.Location;
import model.units.IUnit;
import model.units.SwordMaster;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    unitFactoryTest = new SwordMasterFactory();
    unitTest.addItem(itemTest);
    unitTest.equipItem(itemTest);
  }

  @Test
  public void getNameTest() {
    assertEquals(expectedName, tacticianTest.getName());
  }

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

  @Test
  public void unitsTest() {
    assertEquals(0, tacticianTest.getUnits().size());
    tacticianTest.unitFactory(unitFactoryTest);
    tacticianTest.addDefaultUnit();
    tacticianTest.addDefaultUnit();
    tacticianTest.addCustomUnit(50, 2);
    assertEquals(3, tacticianTest.getUnits().size());
    tacticianTest.removeUnit(2);
    assertEquals(2, tacticianTest.getUnits().size());
    tacticianTest.removeUnit(2);
    assertEquals(2, tacticianTest.getUnits().size());
    tacticianTest.removeAllUnits();
    assertEquals(0, tacticianTest.getUnits().size());
  }

  @Test
  public void equalsTest() {
    assertEquals(tacticianTest, otherTactician);
  }
}
