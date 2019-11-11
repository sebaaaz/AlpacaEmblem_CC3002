package model.factories;

import model.factories.itemFactory.*;

/**
 * This class contains item factories that can be used.
 *
 * @author Sebastián Zapata Ascencio
 * @since 2.0
 */
public class itemFactories {
  public static final AxeFactory AXE_FACTORY = new AxeFactory();
  public static final BowFactory BOW_FACTORY = new BowFactory();
  public static final DarkBookFactory DARK_BOOK_FACTORY = new DarkBookFactory();
  public static final LightBookFactory LIGHT_BOOK_FACTORY = new LightBookFactory();
  public static final SoulBookFactory SOUL_BOOK_FACTORY = new SoulBookFactory();
  public static final SpearFactory SPEAR_FACTORY = new SpearFactory();
  public static final SwordFactory SWORD_FACTORY = new SwordFactory();
}
