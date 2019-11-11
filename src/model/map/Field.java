package model.map;

import java.util.*;
import java.lang.Math;

/**
 * This class represents the map where the units are located and the game is played.
 * <p>
 * The field is an undirected graph composed of <i>Location</i> nodes where the weight of every edge
 * of the graph is 1.
 * Since all cells of the map should be reachable, the graph must be connected.
 *
 * @author Ignacio Slater Muñoz
 * @author Sebastián Zapata Ascencio
 * @since 1.0
 */
public class Field {

  private Map<String, Location> map = new HashMap<>();
  private StringBuilder builder = new StringBuilder();
  private Random random = new Random();
  private long seed;
  private int size = 0;

  /**
   * Creates the map/field for a game
   */
  public Field() {
    setSeed(random.nextLong());
  }

  /**
   * Sets the seed to be used as param of random events.
   *
   * @param seed
   *      the value of the seed
   */
  public void setSeed(long seed) {
    this.seed = seed;
    random = new Random();
    random.setSeed(this.seed);
  }

  /**
   * @return the seed used for random events
   */
  public long getSeed() { return seed; }

  /**
   * Add cells to the map.
   *
   * @param connectAll
   *     a flag that indicates if all the cells should be connected to it's neighbours
   * @param cells
   *     the locations that are going to be added to the map
   */
  public void addCells(final boolean connectAll, final Location... cells) {
    for (Location cell : cells) {
      addCell(cell);
      size++;
      Location[] adjacentCells = getAdjacentCells(cell);
      for (Location adjacentCell : adjacentCells) {
        if (connectAll || random.nextDouble() > 1.0 / 3 || cell.getNeighbours().size() < 1) {
          addConnection(cell, adjacentCell);
        }
      }
    }
  }

  /**
   * Adds a cell to the map
   *
   * @param cell
   *     the location to be added
   */
  private void addCell(final Location cell) {
    map.put(cell.toString(), cell);
  }

  /**
   * Gets the possible adjacent cells to a given cell
   *
   * @param cell
   *     the location of the current cell
   * @return an array of the adjacent cells
   */
  private Location[] getAdjacentCells(final Location cell) {
    int row = cell.getRow(),
        col = cell.getColumn();
    return new Location[]{getCell(row - 1, col), getCell(row + 1, col), getCell(row, col - 1),
        getCell(row, col + 1)};
  }

  /**
   * Creates a connection between 2 cells
   *
   * @param cell1
   *      one of the two cells for the connection
   * @param cell2
   *      one of the two cells for the connection
   */
  private void addConnection(Location cell1, Location cell2) {
    cell1.addNeighbour(cell2);
  }

  /**
   * @param row
   *     the row of the cell
   * @param col
   *     the column of the cell
   * @return the Location that represents the cell at (row, col)
   */
  public Location getCell(final int row, final int col) {
    String id = generateID(row, col);
    return map.getOrDefault(id, new InvalidLocation());
  }

  /**
   * Creates a map key from a row and a column
   *
   * @param row
   *     the row of the cell
   * @param col
   *     the column of the cell
   * @return a string of the form (row, col)
   */
  private String generateID(final int row, final int col) {
    builder.setLength(0);
    builder.append("(").append(row).append(", ").append(col).append(")");
    return builder.toString();
  }

  /**
   * @return a copy of this map. This copy can't modify the original map.
   */
  public Map<String, Location> getMap() {
    return Map.copyOf(map);
  }

  /**
   * Checks if the map is connected using BFS.
   *
   * @return true if the map is connected, false otherwise.
   */
  public boolean isConnected() {
    Set<Location> visitedNodes = new HashSet<>();
    Queue<Location> toVisit = new LinkedList<>();
    toVisit.add(map.entrySet().iterator().next().getValue());
    while (!toVisit.isEmpty()) {
      if (visitedNodes.size() == map.size()) {
        return true;
      }
      Location currentNode = toVisit.poll();
      for (Location neighbour :
          currentNode.getNeighbours()) {
        if (!visitedNodes.contains(neighbour)) {
          visitedNodes.add(neighbour);
          toVisit.add(neighbour);
        }
      }
    }
    return false;
  }

  /**
   * Removes a connection from two locations of the field
   *
   * @param cell1
   *      one of the two cells for removing the connection
   * @param cell2
   *      one of the two cells for removing the connection
   */
  public void removeConnection(final Location cell1, final Location cell2) {
    if (cell1.getNeighbours().size() > 1 && cell2.getNeighbours().size() > 1) {
      cell1.removeNeighbour(cell2);
    }
  }

  /**
   * Checks if two cells of the map are connected
   *
   * @param cell1
   *      one of the two cells for checking the connection
   * @param cell2
   *      one of the two cells for checking the connection
   *
   * @return true if both of the cells are connected. False otherwise.
   */
  public boolean checkConnection(final Location cell1, final Location cell2) {
    return cell1.isNeighbour(cell2);
  }

  /**
   * It is assumed the map has (size x size) locations. So the calculus
   * of the square root of the amount of cells is always an integer.
   *
   * @return the size of the side of the map
   */
  public int getSize() {
    return (int) Math.pow(size, 0.5);
  }

//  /**
//   * Prints the map on console. With x-coordinate to right and y-coordinate to up.
//   * <p>
//   * 1 if the cell has an unit. 0 otherwise.
//   */
//  public void printMap() {
//    for (int x = getSize() - 1; x >= 0; x--) {
//      for (int y = 0; y < getSize(); y++) {
//        if (getCell(y, x).getUnit().isNull()) {
//          System.out.print("0 ");
//        } else {
//          System.out.print("1 ");
//        }
//      }
//      System.out.println();
//    }
//  }

}
