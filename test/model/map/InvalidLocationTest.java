package model.map;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Sebastián Zapata Ascencio
 * @since 1.0
 */
class InvalidLocationTest {
  private InvalidLocation
      invalidLocation0,
      invalidLocation1;
  private Location
      location1;

  /**
   * Sets up some invalid locations for testing.
   */
  @BeforeEach
  void setUp() {
    invalidLocation0 = new InvalidLocation();
    invalidLocation1 = new InvalidLocation();
    location1 = new Location(0,0);
  }

  /**
   * Tests the invalid locations do not have neighbours.
   */
  @Test
  void testNeighbourhood() {
    invalidLocation0.addNeighbour(invalidLocation1);
    assertTrue(invalidLocation0.getNeighbours().isEmpty());
    assertTrue(invalidLocation1.getNeighbours().isEmpty());
    invalidLocation0.addNeighbour(location1);
    assertTrue(invalidLocation0.getNeighbours().isEmpty());
    assertTrue(location1.getNeighbours().isEmpty());
  }

}