package model;

/**
 * This class represents a player of the game.
 * <p>
 * A <i>Tactician</i> handles all the instructions of the player and
 * delegates messages to the objects of the model.
 *
 * @author Sebastián Zapata Ascencio
 * @since 2.0
 */
public class Tactician {

  private String name;

  /**
   * Creates a new Tactician/Player
   *
   * @param name the name of this player
   */
  public Tactician(String name) {
    this.name = name;
  }

  public String getName() { return name; }

}
