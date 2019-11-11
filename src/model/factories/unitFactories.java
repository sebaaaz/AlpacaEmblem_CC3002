package model.factories;

import model.factories.unitFactory.*;

/**
 * This class contains unit factories that can be used.
 *
 * @author Sebastián Zapata Ascencio
 * @since 2.0
 */
public class unitFactories {
  public static final AlpacaFactory ALPACA_FACTORY = new AlpacaFactory();
  public static final ArcherFactory ARCHER_FACTORY = new ArcherFactory();
  public static final ClericFactory CLERIC_FACTORY = new ClericFactory();
  public static final FighterFactory FIGHTER_FACTORY = new FighterFactory();
  public static final HeroFactory HERO_FACTORY = new HeroFactory();
  public static final SorcererFactory SORCERER_FACTORY = new SorcererFactory();
  public static final SwordMasterFactory SWORD_MASTER_FACTORY = new SwordMasterFactory();
}
