package bacterium.strategies;

import bacterium.BacteriaTile;
import bacterium.BacteriumTile;
import bacterium.Characteristics.Direction;
import util.Cyclic2DArrayList;

public class EagerBlind implements Strategy {

  @Override
  public Direction decide(Cyclic2DArrayList<BacteriumTile> env, BacteriaTile t) {
    // TODO Auto-generated method stub
    return null;
  }

  private static EagerBlind instance;

  public static EagerBlind self() {
    return instance != null ? instance : (instance = new EagerBlind());
  }
}
