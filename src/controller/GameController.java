package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import static java.util.Collections.shuffle;
import static java.lang.Math.min;

import model.Tactician;
import model.items.IEquipableItem;
import model.map.Field;
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

  private List<Tactician> players = new ArrayList<>();
  private List<Integer> playersOrder = new ArrayList<>();
  private Field map = new Field();
  private int numberOfPlayers;
  private int currentRound;
  private int currentTurn;
  private int maxRounds;
  private Tactician turnOwner;

  /**
   * Creates the controller for a new game.
   *
   * @param numberOfPlayers
   *     the number of players for this game
   * @param mapSize
   *     the dimensions of the map, for simplicity, all maps are squares
   */
  public GameController(int numberOfPlayers, int mapSize) {
    this.numberOfPlayers = numberOfPlayers;
    for (int i = 0; i < numberOfPlayers; i++) {
      Tactician player = new Tactician("Player " + i);
      players.add(player);
      playersOrder.add(i);
    }
    turnOwner = players.get(0);
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
   * @return the maximum number of rounds a match can last
   */
  public int getMaxRounds() {
    return maxRounds;
  }

  /**
   * @return the number of players
   */
  public int getNumberOfPlayers() {
    return numberOfPlayers;
  }

  /**
   * Finishes the current player's turn.
   */
  public void endTurn() {
    currentTurn = (currentTurn + 1) % numberOfPlayers;
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
    if (index != -1) players.remove(index);
  }

  /**
   * Starts the game.
   * @param maxTurns
   *  the maximum number of turns the game can last
   */
  public void initGame(final int maxTurns) {
    maxRounds = maxTurns;
    currentRound = 0;
    currentTurn = 0;
  }

  /**
   * Starts a game without a limit of turns.
   */
  public void initEndlessGame() {
    maxRounds = -1;
    currentRound = 0;
    currentTurn = 0;
  }

  /**
   * @return the winner of this game, if the match ends in a draw returns a list of all the winners
   */
  public List<String> getWinners() { return null; }

  /**
   * @return the current player's selected unit
   */
  public IUnit getSelectedUnit() {
    return null;
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

  }

  /**
   * @return the inventory of the currently selected unit.
   */
  public List<IEquipableItem> getItems() {
    return null;
  }

  /**
   * Equips an item from the inventory to the currently selected unit.
   *
   * @param index
   *     the location of the item in the inventory.
   */
  public void equipItem(int index) {

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
    Tactician lastTactician = players.get(numberOfPlayers - 1);
    int newPositionLastTactician = new Random().nextInt(numberOfPlayers);
    players.remove(numberOfPlayers - 1);
    shuffle(players, new Random());
    players.add(newPositionLastTactician, lastTactician);
  }
}
