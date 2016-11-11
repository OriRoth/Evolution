package bacterium.strategies;

import java.util.LinkedList;
import java.util.List;

import bacterium.BacteriaTile;
import bacterium.BacteriumTile;
import bacterium.FoodTile;
import bacterium.Characteristics.Attribute;
import bacterium.Characteristics.Direction;
import bacterium.settings.rules;
import util.Cyclic2DArrayList;
import util.RandomUtil;

public class EagerBlind implements Strategy {

  @SuppressWarnings("unchecked")
  @Override
  public BacteriumTile decide(Cyclic2DArrayList<BacteriumTile> env, BacteriaTile t) {
    List<Direction> ds = new LinkedList<>();
    for (Direction ¢ : (List<Direction>) t.characteristics().get(Attribute.strideDirections))
      if (rules.canMove(env, t, ¢))
        ds.add(¢);
    return destination(env, t, ds);
  }

  private static BacteriumTile destination(Cyclic2DArrayList<BacteriumTile> env, BacteriaTile t, List<Direction> ds) {
    if (ds == null || ds.isEmpty())
      return null;
    BacteriumTile fd = findFood(env, t, ds), fo = findFoe(env, t, ds);
    if (rules.attack_spawns.isApplied()) {
      if (fo != null)
        return fo;
      if (fd != null)
        return fd;
    } else {
      if (fd != null)
        return fd;
      if (fo != null)
        return fo;
    }
    Direction $ = RandomUtil.choose(ds);
    return $ == null ? null : env.get(t.getPosition().x + $.xd, t.getPosition().y + $.yd);
  }

  private static BacteriumTile findFood(Cyclic2DArrayList<BacteriumTile> env, BacteriaTile t, List<Direction> ds) {
    for (Direction $ : ds)
      if (rules.canMove(env, t, $)) {
        BacteriumTile d = env.get(t.getPosition().x + $.xd, t.getPosition().y + $.yd);
        if (d instanceof FoodTile)
          return (FoodTile) d;
      }
    return null;
  }

  private static BacteriumTile findFoe(Cyclic2DArrayList<BacteriumTile> env, BacteriaTile t, List<Direction> ds) {
    for (Direction $ : ds)
      if (rules.canMove(env, t, $)) {
        BacteriumTile d = env.get(t.getPosition().x + $.xd, t.getPosition().y + $.yd);
        if (d instanceof BacteriaTile && rules.goodOdds(t, ((BacteriaTile) d)))
          return d;
      }
    return null;
  }

  private static EagerBlind instance;

  public static EagerBlind self() {
    return instance != null ? instance : (instance = new EagerBlind());
  }
}
