package bacterium.settings;

import bacterium.BacteriaTile;
import bacterium.BacteriumTile;
import bacterium.EmptyTile;
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
    assert env.get(t.getPosition()).equals(t);
    if (d instanceof EmptyTile)
      swap(env, t, d, d, t);
    // TODO Roth: complete
  }

  public static boolean canMove(Cyclic2DArrayList<BacteriumTile> env, BacteriaTile __, Direction d) {
    return true;
  }

  public static boolean goodOdds(BacteriaTile b1, BacteriaTile b2) {
    return b1.characteristics().getInt(Attribute.attack) > b2.characteristics().getInt(Attribute.attack);
  }

  private static void swap(Cyclic2DArrayList<BacteriumTile> env, BacteriumTile b1, BacteriumTile r1, BacteriumTile b2,
      BacteriumTile r2) {
    env.set(b1.getPosition().x, b1.getPosition().y, r1);
    env.set(b2.getPosition().x, b2.getPosition().y, r2);
    r1.setPosition(b1.getPosition());
    r2.setPosition(b2.getPosition());
  }
}
