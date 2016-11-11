package bacterium.settings;

import bacterium.BacteriaTile;
import bacterium.BacteriumTile;
import bacterium.Characteristics.Attribute;
import bacterium.Characteristics.Direction;
import util.Cyclic2DArrayList;

public enum rules {
  attack_spawns(false);
  boolean isApplied;

  rules(boolean isApplied) {
    this.isApplied = isApplied;
  }
  
  public boolean isApplied() {
    return isApplied;
  }

  public static void move(Cyclic2DArrayList<BacteriumTile> env, BacteriaTile t, BacteriumTile d) {
    // TODO Roth: complete
  }

  public static boolean canMove(Cyclic2DArrayList<BacteriumTile> env, BacteriaTile __, Direction d) {
    return true;
  }

  public static boolean goodOdds(BacteriaTile b1, BacteriaTile b2) {
    return b1.characteristics().getInt(Attribute.attack) > b2.characteristics().getInt(Attribute.attack);
  }
}
