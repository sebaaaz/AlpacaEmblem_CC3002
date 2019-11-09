package controller;

import java.util.*;
import java.util.stream.IntStream;
import model.Tactician;
import model.items.IEquipableItem;
import model.map.Field;
import model.map.Location;

import model.units.IUnit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static model.factories.itemFactories.*;
import static model.factories.unitFactories.*;
import static model.units.NullUnit.NULL_UNIT;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Ignacio Slater Muñoz
 * @since v2.0
 */
class GameControllerTest {

  private GameController controller;
  private long testSeed;
  private List<String> testTacticians;
  private Tactician tacticianTest;

  /**
   * Sets up the controller and tacticians to be tested. Always it sets up other attributes.
   */
  @BeforeEach
  void setUp() {
    testSeed = new Random().nextLong();
    controller = new GameController(4, 5);
    testTacticians = List.of("Player 0", "Player 1", "Player 2", "Player 3");
    tacticianTest = new Tactician("TestTactician");
  }

  /**
   * Checks the controller has the correct list of tacticians.
   */
  @Test
  void getTacticians() {
    List<Tactician> tacticians = controller.getTacticians();
    assertEquals(4, tacticians.size());
    for (int i = 0; i < tacticians.size(); i++) {
      assertEquals("Player " + i, tacticians.get(i).getName());
    }
  }

  @Test
  void getGameMap() {
    controller.generateNewMap();
    Field gameMap = controller.getGameMap();
    assertEquals(5, gameMap.getSize());

    // checking all cells are inside bounds
    for (Location location1 : gameMap.getMap().values()) {
      assertTrue(
          (location1.getRow() >= 0) &&
              (location1.getColumn() >= 0) &&
              (location1.getRow() < gameMap.getSize()) &&
              (location1.getColumn() < gameMap.getSize()));
    }

    assertTrue(gameMap.isConnected()); // we need the controller generates connected maps
    // the map is connected, so checking all cells have at least 1 neighbour
    gameMap.getMap().values().forEach(location -> {
        assertTrue(location.getNeighbours().size() >= 1);
      });

    // generating a copy of the map
    Field newMap = new Field();
    newMap.setSeed(gameMap.getSeed());
    for (int i = 0; i < gameMap.getSize(); i++) {
      Location[] row = new Location[gameMap.getSize()];
      for (int j = 0; j < gameMap.getSize(); j++) {
        row[j] = (new Location(i, j));
      }
      newMap.addCells(false, row);
    }

    // checking they are equals
    for (Location copyLocation : newMap.getMap().values()) {
      Location originalLocation = gameMap.getCell(copyLocation.getRow(), copyLocation.getColumn());
      assertEquals(originalLocation, copyLocation); // just position check
      assertEquals(originalLocation.getNeighbours().size(), copyLocation.getNeighbours().size()); // checking neighbours
    }
  }

  /**
   * Checks if the turn owner is correctly setted after every end turn event.
   */
  @Test
  void getTurnOwner() {
    controller.setSeed(42); // Player 3 Player 1 Player 0 Player 2
    controller.initEndlessGame();
    assertEquals("Player 3", controller.getTurnOwner().getName());
    assertEquals(0, controller.getTurnNumber());
    controller.endTurn();
    assertEquals("Player 1", controller.getTurnOwner().getName());
    assertEquals(1, controller.getTurnNumber());
    controller.endTurn();
    assertEquals("Player 0", controller.getTurnOwner().getName());
    assertEquals(2, controller.getTurnNumber());
    controller.endTurn();
    assertEquals("Player 2", controller.getTurnOwner().getName());
    assertEquals(3, controller.getTurnNumber());

    controller.endTurn();           // Player 0 Player 1 Player 3 Player 2
    assertEquals(0, controller.getTurnNumber());
    controller.endTurn();
    assertEquals(1, controller.getTurnNumber());
    assertEquals("Player 1", controller.getTurnOwner().getName());
    controller.removeTactician("Player 1"); // Player 0 Player 3 Player 2
    assertEquals("Player 3", controller.getTurnOwner().getName());
    assertEquals(1, controller.getTurnNumber());

    controller.removeTactician("Player 2"); // Player 0 Player 3
    assertEquals("Player 3", controller.getTurnOwner().getName());
    assertEquals(1, controller.getTurnNumber());
    controller.endTurn();                   // Player 0 Player 3
    assertEquals("Player 0", controller.getTurnOwner().getName());
    assertEquals(0, controller.getTurnNumber());
    controller.endTurn();
    assertEquals("Player 3", controller.getTurnOwner().getName());
    assertEquals(1, controller.getTurnNumber());
  }

  /**
   * Checks if the turns are correct after every end turn event.
   */
  @Test
  void getRoundNumber() {
    controller.initGame(10);
    for (int i = 0; i < 10; i++) {
      assertEquals(i, controller.getRoundNumber());
      for (int j = 0; j < 4; j++) {
        controller.endTurn();
      }
    }
  }

  /**
   * Checks if the max amount of rounds are returned correctly.
   */
  @Test
  void getMaxRounds() {
    Random randomTurnSequence = new Random();
    IntStream.range(0, 50).map(i -> randomTurnSequence.nextInt() & Integer.MAX_VALUE).forEach(nextInt -> {
      controller.initGame(nextInt);
      assertEquals(nextInt, controller.getMaxRounds());
    });
    controller.initEndlessGame();
    assertEquals(-1, controller.getMaxRounds());
  }

  /**
   * Checks if the turn owner and the current turn
   */
  @Test
  void endTurn() {
    controller.setSeed(42);
    controller.initGame(1); // Player 3 Player 1 Player 0 Player 2
    Tactician firstPlayer = controller.getTurnOwner();
    Tactician secondPlayer = new Tactician("Player 1");
    assertNotEquals(secondPlayer.getName(), firstPlayer.getName());

    controller.endTurn();
    assertNotEquals(firstPlayer.getName(), controller.getTurnOwner().getName());
    assertEquals(secondPlayer.getName(), controller.getTurnOwner().getName());
  }

  /**
   * Checks if the removal of a tactician is successful.
   */
  @Test
  void removeTactician() {
    assertEquals(4, controller.getTacticians().size());
    controller.getTacticians()
        .forEach(tactician -> Assertions.assertTrue(testTacticians.contains(tactician.getName())));

    controller.removeTactician("Player 1");
    assertEquals(3, controller.getTacticians().size());
    controller.getTacticians().forEach(tactician -> assertNotEquals("Player 1", tactician));
    controller.getTacticians()
        .forEach(tactician -> Assertions.assertTrue(testTacticians.contains(tactician.getName())));

    controller.removeTactician("Player 5");
    assertEquals(3, controller.getTacticians().size());
    controller.getTacticians()
        .forEach(tactician -> Assertions.assertTrue(testTacticians.contains(tactician.getName())));
  }

  /**
   * Checks if the winners of the game are returned correctly.
   */
  @Test
  void getWinners() {
    controller.initGame(2);
    IntStream.range(0, 8).forEach(i -> controller.endTurn());
    assertEquals(4, controller.getWinners().size());
    controller.getWinners()
        .forEach(player -> Assertions.assertTrue(testTacticians.contains(player)));

    controller.initGame(2);
    IntStream.range(0, 4).forEach(i -> controller.endTurn());
    assertNull(controller.getWinners());
    controller.removeTactician("Player 0");
    controller.removeTactician("Player 2");
    IntStream.range(0, 2).forEach(i -> controller.endTurn());
    List<String> winners = controller.getWinners();
    assertEquals(2, winners.size());
    assertTrue(List.of("Player 1", "Player 3").containsAll(winners));

    controller.initEndlessGame();
    for (int i = 0; i < 3; i++) {
      assertNull(controller.getWinners());
      controller.removeTactician("Player " + i);
    }
    assertTrue(List.of("Player 3").containsAll(controller.getWinners()));
  }

  /**
   *
   */
  @Test
  void getSelectedUnit() {
    assertEquals(NULL_UNIT, controller.getSelectedUnit());

    IUnit unitTest1 = ALPACA_FACTORY.createUnit();
    controller.addUnit(unitTest1);

    controller.selectUnitWithInvalidPosition();
    assertEquals(unitTest1, controller.getSelectedUnit());

    controller.setSelectedUnitLocation(controller.getGameMap().getCell(3,1));
    controller.selectUnit(NULL_UNIT);
    assertEquals(NULL_UNIT, controller.getSelectedUnit());

    controller.selectUnitIn(3, 1);
    assertEquals(unitTest1, controller.getSelectedUnit());

    controller.selectUnit(unitTest1);
    assertEquals(unitTest1, controller.getSelectedUnit());

    IUnit unitTest2 = FIGHTER_FACTORY.createUnit();
    tacticianTest.addUnit(unitTest2);
    controller.selectUnit(unitTest2); // it is not the unit of turn owner tactician
    assertEquals(NULL_UNIT, controller.getSelectedUnit());
  }

  @Test
  void selectUnitIn() {
    IUnit unitTest1 = ALPACA_FACTORY.createUnit();
    IUnit unitTest2 = FIGHTER_FACTORY.createUnit();
    unitTest1.setLocation(controller.getGameMap().getCell(4,2));
    unitTest2.setLocation(controller.getGameMap().getCell(1,1));
    assertEquals(NULL_UNIT, controller.getSelectedUnit());

    controller.selectUnitIn(4,2); // the unit is not assigned to a tactician
    assertEquals(NULL_UNIT, controller.getSelectedUnit());

    controller.addUnit(unitTest1);
    controller.selectUnitIn(4,2);
    assertEquals(unitTest1, controller.getSelectedUnit());

    controller.addUnit(unitTest2);
    controller.selectUnitIn(1,1);
    assertEquals(unitTest2, controller.getSelectedUnit());

    controller.selectUnitIn(0,0); // there is not an unit here
    assertEquals(NULL_UNIT, controller.getSelectedUnit());
  }

  @Test
  void getItems() {
    assertEquals(NULL_UNIT, controller.getSelectedUnit());
    assertEquals(0, controller.getItems().size());
    IUnit unitTest1 = FIGHTER_FACTORY.createUnit();
    controller.addUnit(unitTest1);
    controller.selectUnitWithInvalidPosition();
    assertEquals(unitTest1, controller.getSelectedUnit());
    assertEquals(0, controller.getItems().size());
    unitTest1.addItem(AXE_FACTORY.createItem());
    unitTest1.addItem(SWORD_FACTORY.createItem());
    assertEquals(2, controller.getItems().size());
  }

  @Test
  void equipItem() {
    assertEquals(NULL_UNIT, controller.getSelectedUnit());
    IUnit unitTest1 = FIGHTER_FACTORY.createUnit();
    IEquipableItem itemTest1 = AXE_FACTORY.createItem();
    unitTest1.addItem(itemTest1);
    controller.addUnit(unitTest1);
    controller.selectUnitWithInvalidPosition();
    assertTrue(controller.getSelectedUnit().getEquippedItem().isNull());
    controller.equipItem(1);
    assertTrue(controller.getSelectedUnit().getEquippedItem().isNull());
    controller.equipItem(0);
    assertEquals(itemTest1, controller.getSelectedUnit().getEquippedItem());
  }

  @Test
  void useItemOn() {
    IUnit unitTest1 = FIGHTER_FACTORY.createUnit();
    IUnit unitTest2 = ALPACA_FACTORY.createUnit();
    unitTest1.setLocation(controller.getGameMap().getCell(1,1));
    unitTest2.setLocation(controller.getGameMap().getCell(1,0));
    IEquipableItem itemTest1 = AXE_FACTORY.createFullCustomItem(
        "Big Range Axe",20, 0, (int) (Math.pow(controller.getGameMap().getSize(), 2) + 1));
    unitTest1.addItem(itemTest1);
    unitTest1.equipItem(itemTest1);

    controller.addUnit(unitTest1);
    controller.selectUnitIn(1,1);
    assertEquals(unitTest1, controller.getSelectedUnit());
    assertEquals(itemTest1, controller.getSelectedUnit().getEquippedItem());

    assertEquals(20, controller.getSelectedUnit().getEquippedItem().getPower());
    assertEquals(50, unitTest2.getHitPoints());
    controller.useItemOn(1,0);
    assertEquals(30, unitTest2.getHitPoints());

    controller.useItemOn(3, 3);
    assertEquals(30, unitTest2.getHitPoints());
  }

  @Test
  void selectItem() {
    IUnit unitTest1 = FIGHTER_FACTORY.createUnit();
    IEquipableItem itemTest1 = BOW_FACTORY.createItem();
    IEquipableItem itemTest2 = AXE_FACTORY.createItem();
    unitTest1.addItem(itemTest1);
    unitTest1.addItem(itemTest2);
    controller.addUnit(unitTest1);

    assertTrue(controller.getSelectedItem().isNull());
    controller.selectUnit(unitTest1);
    assertTrue(controller.getSelectedItem().isNull());
    controller.selectItem(0);
    assertEquals(itemTest1, controller.getSelectedItem());
    controller.selectItem(1);
    assertEquals(itemTest2, controller.getSelectedItem());
    controller.selectItem(2);
    assertEquals(itemTest2, controller.getSelectedItem());
  }

  @Test
  void giveItemTo() {
    controller.setMapSeed(42);
    controller.generateNewMap(); // the cells of the units will be connected
    IUnit giverUnitTest = FIGHTER_FACTORY.createUnit();
    IUnit allyUnitTest = ARCHER_FACTORY.createUnit();
    IUnit nonAllyUnitTest = FIGHTER_FACTORY.createUnit();
    IEquipableItem itemTest1 = BOW_FACTORY.createItem();
    IEquipableItem itemTest2 = AXE_FACTORY.createItem();
    giverUnitTest.addItem(itemTest1);
    giverUnitTest.addItem(itemTest2);
    controller.addUnit(giverUnitTest);
    controller.addUnit(allyUnitTest);
    allyUnitTest.setLocation(controller.getGameMap().getCell(2,3));
    giverUnitTest.setLocation(controller.getGameMap().getCell(2,2));
    nonAllyUnitTest.setLocation(controller.getGameMap().getCell(2,1));
    assertTrue(controller.getGameMap().getCell(2,2).getNeighbours().contains(controller.getGameMap().getCell(2,3)));
    assertTrue(controller.getGameMap().getCell(2,2).getNeighbours().contains(controller.getGameMap().getCell(2,1)));

    assertEquals(2, giverUnitTest.getItems().size());
    assertEquals(0, allyUnitTest.getItems().size());
    assertEquals(0, nonAllyUnitTest.getItems().size());
    assertEquals(controller.getTurnOwner(), giverUnitTest.getOwner());
    assertEquals(controller.getTurnOwner(), allyUnitTest.getOwner());
    assertNotEquals(controller.getTurnOwner(), nonAllyUnitTest);

    controller.selectUnit(giverUnitTest);
    controller.selectItem(0);
    assertEquals(itemTest1, controller.getSelectedItem());
    controller.giveItemTo(2,3); // the giving is successful
    assertEquals(allyUnitTest, controller.getSelectedUnit());
    assertEquals(itemTest1, controller.getSelectedItem());
    assertEquals(1, allyUnitTest.getItems().size());
    assertEquals(1, giverUnitTest.getItems().size());
    assertTrue(allyUnitTest.getItems().contains(itemTest1));

    controller.selectUnit(giverUnitTest);
    controller.selectItem(0);
    assertEquals(itemTest2, controller.getSelectedItem());
    controller.giveItemTo(2,1); // the giving is not successful
    assertEquals(giverUnitTest, controller.getSelectedUnit());
    assertEquals(itemTest2, controller.getSelectedItem());
    assertEquals(1, giverUnitTest.getItems().size());
    assertEquals(0, nonAllyUnitTest.getItems().size());
    assertTrue(giverUnitTest.getItems().contains(itemTest2));
  }

  @Test
  void shufflePlayersTest() {
    List<Tactician> tacticians = controller.getTacticians();
    assertEquals(4, tacticians.size());
    String lastTactician = controller.getTacticians().get(controller.getNumberOfPlayers() - 1).getName();
    controller.shufflePlayers();
    assertEquals(4, tacticians.size());
    assertNotEquals(lastTactician, controller.getTacticians().get(0).getName());
  }

  @Test
  void heroDefeatedTest() {
    Tactician player0 = controller.getTacticians().get(0);
    Tactician player1 = controller.getTacticians().get(1);
    controller.setMapSeed(42);
    controller.generateNewMap(); // the cells of the units will be connected
    IUnit hero0 = HERO_FACTORY.createFullCustomUnit(100, 10);
    IUnit hero1 = HERO_FACTORY.createFullCustomUnit(1, 10);
    IEquipableItem spear0 = SPEAR_FACTORY.createItem();
    hero0.setLocation(controller.getGameMap().getCell(2,2));
    hero1.setLocation(controller.getGameMap().getCell(2,3));
    hero0.addItem(spear0);
    controller.addUnit(hero0);
    controller.selectUnit(hero0);
    controller.equipItem(0);
    player1.addUnit(hero1);

    assertEquals(4, controller.getTacticians().size());
    assertTrue(controller.getTacticians().contains(player1));
    controller.useItemOn(2,3);
    assertEquals(3, controller.getTacticians().size());
    assertFalse(controller.getTacticians().contains(player1));
  }

  @Test
  void tacticianDefeatedTest() {
    Tactician player0 = controller.getTacticians().get(0);
    controller.addUnit(ARCHER_FACTORY.createUnit());
    controller.addUnit(FIGHTER_FACTORY.createUnit());
    controller.addUnit(ALPACA_FACTORY.createUnit());
    controller.selectUnitWithInvalidPosition();
    controller.setSelectedUnitLocation(controller.getGameMap().getCell(2,2));
    controller.selectUnitWithInvalidPosition();
    controller.setSelectedUnitLocation(controller.getGameMap().getCell(2,1));
    controller.selectUnitWithInvalidPosition();
    controller.setSelectedUnitLocation(controller.getGameMap().getCell(2,0));
    assertTrue(player0.allUnitsAllocated());

    assertTrue(controller.getTacticians().contains(player0));
    assertEquals(4, controller.getTacticians().size());
    assertEquals(3, player0.getUnits().size());
    controller.removeTactician("Player 0");
    assertFalse(controller.getTacticians().contains(player0));
    assertEquals(3, controller.getTacticians().size());
    assertEquals(0, player0.getUnits().size());
  }

  @Test
  void getWinnersDrawTest() {
    controller.setSeed(42);
    controller.initGame(1);
    assertNull(controller.getWinners());
    IntStream.range(0, 4).forEach(i -> controller.endTurn());

    assertTrue(controller.endedGame());
    assertEquals(4, Objects.requireNonNull(controller.getWinners()).size());
    controller.getTactician("Player 0").addUnit(HERO_FACTORY.createUnit());
    assertEquals(1, controller.getWinners().size());
    assertTrue(controller.getWinners().contains(controller.getTactician("Player 0").getName()));
    controller.getTactician("Player 1").addUnit(FIGHTER_FACTORY.createUnit());
    assertEquals(2, controller.getWinners().size());
    controller.getTactician("Player 1").addUnit(HERO_FACTORY.createUnit());
    assertEquals(1, controller.getWinners().size());
    assertTrue(controller.getWinners().contains(controller.getTactician("Player 1").getName()));
  }
}