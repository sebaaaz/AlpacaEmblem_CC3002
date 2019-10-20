package model.unitFactories;

import model.units.Hero;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

public abstract class AbstractUnitFactoryTest {

  protected AlpacaFactory alpacaFactory;
  protected ArcherFactory archerFactory;
  protected ClericFactory clericFactory;
  protected FighterFactory fighterFactory;
  protected HeroFactory heroFactory;
  protected SorcererFactory sorcererFactory;
  protected SwordMasterFactory swordMasterFactory;

  /**
   * Sets up the factories to be tested.
   */
  @BeforeEach
  public void setUp() {
    setTestFactory();
  }

  /**
   * Creates a set of testing factories.
   */
  public void setTestFactory() {
    alpacaFactory = new AlpacaFactory();
    archerFactory = new ArcherFactory();
    clericFactory = new ClericFactory();
    fighterFactory = new FighterFactory();
    heroFactory = new HeroFactory();
    sorcererFactory = new SorcererFactory();
    swordMasterFactory = new SwordMasterFactory();
  }



}