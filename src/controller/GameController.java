package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import static java.util.Collections.shuffle;

import model.Tactician;
import model.items.IEquipableItem;
import model.map.Field;
import model.map.Location;
import model.units.IUnit;

/**
 * Controller of the game.
 * The controller manages all the input received from de game's GUI.
 *
 * @author Ignacio Slater Muñoz
 * @author Sebastián Zapata Ascencio
 * @version 2.0
 * @since 2.0
 */
public class GameController {

  private Random random = new Random();
  private List<Tactician> players;
  private List<Tactician> originalPlayers = new ArrayList<>();
  private Field map;

  private int mapSize;
  private int currentRound;
  private int currentTurn;
  private int maxRounds;

  private Tactician turnOwner;
  private IUnit selectedUnit;
  private IEquipableItem selectedItem;

  /**
   * Creates the controller for a new game.
   *
   * @param numberOfPlayers
   *     the number of players for this game
   * @param mapSize
   *     the dimensions of the map, for simplicity, all maps are squares
   */
  public GameController(int numberOfPlayers, int mapSize) {
    for (int i = 0; i < numberOfPlayers; i++) {
      Tactician player = new Tactician("Player " + i);
      originalPlayers.add(player);
    }
    players = new ArrayList<>(originalPlayers);
    turnOwner = players.get(0);
    this.mapSize = mapSize;
  }

  /**
   * @return the list of all the tacticians participating in the game.
   */
  public List<Tactician> getTacticians() {
    return players;
  }

  /**
   * @return the map of the current game
   */
  public Field getGameMap() {
    return map;
  }

  /**
   * @return the tactician that's currently playing
   */
  public Tactician getTurnOwner() {
    return turnOwner;
  }

  /**
   * @return the number of rounds since the start of the game.
   */
  public int getRoundNumber() {
    return currentRound;
  }

  /**
   * @return the number of turns since the start of the round.
   */
  public int getTurnNumber() { return currentTurn; }

  /**
   * @return the maximum number of rounds a match can last
   */
  public int getMaxRounds() {
    return maxRounds;
  }

  /**
   * @return the number of players
   */
  public int getNumberOfPlayers() {
    return players.size();
  }

  /**
   * Print the names of the players/tacticians of the game
   */
  public void printNames() {
    StringBuilder names = new StringBuilder();
    names.setLength(0);
    players.forEach(player -> names.append(player.getName()).append(" "));
    System.out.println(names);
  }

  /**
   * Sets the seed to be used as param of random events.
   *
   * @param seed
   *      the value of the seed
   */
  public void setSeed(long seed) {
    random = new Random();
    random.setSeed(seed);
  }

  /**
   * Sets the seed to the map.
   *
   * @param seed
   *      the value of the seed
   */
  public void setMapSeed(long seed) {
    map.setSeed(seed);
  }

  /**
   * Finishes the current player's turn.
   */
  public void endTurn() {
    if (!getTurnOwner().getName().equals(getTacticians().get(currentTurn).getName())) {
      turnOwner = getTacticians().get(currentTurn);
      return;
    }
    currentTurn = (currentTurn + 1) % getNumberOfPlayers();
    if (currentTurn == 0) {
      currentRound++;
      shufflePlayers();
    }
    turnOwner = players.get(currentTurn);
  }

  /**
   * Removes a tactician and all of it's units from the game.
   *
   * @param tactician
   *     the player to be removed
   */
  public void removeTactician(String tactician) {
    int index = -1;
    for (int i = 0; i < players.size(); i++) {
      if (players.get(i).getName().equals(tactician)) index = i;
    }
    if (index != -1) {
      players.remove(index);
    }
  }

  /**
   * Starts the game.
   * @param totalRounds
   *  the maximum number of rounds the game can last
   */
  public void initGame(final int totalRounds) {
    players = new ArrayList<>(originalPlayers);
    shuffle(players, random);
    turnOwner = players.get(0);
    maxRounds = totalRounds;
    currentRound = 1;
    currentTurn = 0;
  }

  /**
   * Starts a game without a limit of turns.
   */
  public void initEndlessGame() {
    this.initGame(-1);
  }

  /**
   * @return the winner of this game, if the match ends in a draw returns a list of all the winners
   */
  public List<String> getWinners() {
    List<String> names = new ArrayList<>();
    players.forEach(player -> names.add(player.getName()));
    if (names.size() == 1) {
      return names;
    }
    if ( getMaxRounds() == -1 || names.size() == 0 || (!endedGame())) {
      return null;
    } else {
      return names;
    }
  }

  /**
   * @return the current player's selected unit
   */
  public IUnit getSelectedUnit() {
    return turnOwner.getSelectedUnit();
  }

  /**
   * Selects a unit in the game map
   *
   * @param x
   *     horizontal position of the unit
   * @param y
   *     vertical position of the unit
   */
  public void selectUnitIn(int x, int y) {
    selectedUnit = map.getCell(y, x).getUnit();
  }

  /**
   * @return the inventory of the currently selected unit.
   */
  public List<IEquipableItem> getItems() {
    return getSelectedUnit().getItems();
  }

  /**
   * Equips an item from the inventory to the currently selected unit.
   *
   * @param index
   *     the location of the item in the inventory.
   */
  public void equipItem(int index) {
    getSelectedUnit().equipItem(getItems().get(index));
  }

  /**
   * Uses the equipped item on a target
   *
   * @param x
   *     horizontal position of the target
   * @param y
   *     vertical position of the target
   */
  public void useItemOn(int x, int y) {

  }

  /**
   * Selects an item from the selected unit's inventory.
   *
   * @param index
   *     the location of the item in the inventory.
   */
  public void selectItem(int index) {
    selectedItem = turnOwner.getSelectedItem();
  }

  /**
   * Gives the selected item to a target unit.
   *
   * @param x
   *     horizontal position of the target
   * @param y
   *     vertical position of the target
   */
  public void giveItemTo(int x, int y) {

  }

  /**
   * Shuffles the list of tacticians in order to get new turns for a round.
   */
  public void shufflePlayers(){
    Tactician lastTactician = players.get(getNumberOfPlayers() - 1);
    int newPositionLastTactician = random.nextInt(getNumberOfPlayers() - 1) + 1;
    players.remove(getNumberOfPlayers() - 1);
    shuffle(players, random);
    players.add(newPositionLastTactician, lastTactician);
    turnOwner = players.get(currentTurn);
  }

  /**
   * Determines if the game ended, seeing the current round.
   *
   * @return true if the game ended. Returns false if not.
   */
  private boolean endedGame(){
    return getRoundNumber() > getMaxRounds() && getMaxRounds() != -1;
  }

  public void createMap(){
    map = new Field();
    for (int i = 0; i < mapSize; i++) {
      for (int j = 0; j < mapSize; j++) {
        map.addCells(false, new Location(i, j));
      }
    }
  }
}