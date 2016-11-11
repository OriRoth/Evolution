package bacterium.settings;

import bacterium.BacteriaTile;
import bacterium.BacteriumTile;
import bacterium.EmptyTile;
import bacterium.FoodTile;
import system.Point;
import bacterium.Characteristics.Attribute;
import bacterium.Characteristics.Direction;
import util.Cyclic2DArrayList;
import util.RandomUtil;

public enum rules {
  attack_spawns(false), attack_opponent_spawns(true);
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
    else if (d instanceof FoodTile) {
      swap(env, t, new EmptyTile(t.getPosition()), d, t);
      for (int ¢ = 0; ¢ < t.characteristics().getInt(Attribute.spawn); ++¢)
        spawn(env, t);
    } else if (d instanceof BacteriaTile) {
      BacteriaTile w = fight(t, (BacteriaTile) d) ? t : (BacteriaTile) d;
      swap(env, w, new EmptyTile(t.getPosition()), d, t);
      if (attack_opponent_spawns.isApplied())
        for (int ¢ = 0; ¢ < w.characteristics().getInt(Attribute.spawn); ++¢)
          spawn(env, w);
    }
  }

  public static boolean canMove(Cyclic2DArrayList<BacteriumTile> env, BacteriaTile __, Direction d) {
    return true;
  }

  public static boolean goodOdds(BacteriaTile b1, BacteriaTile b2) {
    return b1.characteristics().getInt(Attribute.attack) > b2.characteristics().getInt(Attribute.attack);
  }

  public static boolean fight(BacteriaTile b1, BacteriaTile b2) {
    int a1 = b1.characteristics().getInt(Attribute.attack), a2 = b2.characteristics().getInt(Attribute.attack);
    return a1 > a2 || a2 <= a1 && RandomUtil.random.nextBoolean();
  }

  private static void swap(Cyclic2DArrayList<BacteriumTile> env, BacteriumTile b1, BacteriumTile r1, BacteriumTile b2,
      BacteriumTile r2) {
    env.set(b1.getPosition().x, b1.getPosition().y, r1);
    env.set(b2.getPosition().x, b2.getPosition().y, r2);
    r1.setPosition(b1.getPosition());
    r2.setPosition(b2.getPosition());
  }

  private static boolean spawn(Cyclic2DArrayList<BacteriumTile> env, BacteriaTile t) {
    for (int x = t.getPosition().x, y = t.getPosition().y, i = -1; i <= 1; ++i)
      for (int j = -1; j <= 1; ++j)
        if (env.get(x + i, y + j) instanceof EmptyTile) {
          env.set(x + i, y + j, new BacteriaTile(Point.at(x + i, y + j), t.characteristics().spawn()));
          return true;
        }
    return false;
  }
}
