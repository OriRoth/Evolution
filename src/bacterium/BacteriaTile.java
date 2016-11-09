package bacterium;

import system.Point;
import util.Cyclic2DArrayList;

public class BacteriaTile extends BacteriumTile {
  public BacteriaTile(Point pos) {
    super(pos);
  }

  @Override
  public void act(Cyclic2DArrayList<BacteriumTile> env) {
    assert env != null;
    assert this.equals(env.get(pos));
  }
}
