package bacterium;

import system.Point;
import util.Cyclic2DArrayList;

public class EmptyTile extends BacteriumTile {
  public EmptyTile(Point pos) {
    super(pos);
  }
  @Override
  public void act(Cyclic2DArrayList<BacteriumTile> env) {
  }
}
