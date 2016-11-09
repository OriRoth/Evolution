package bacterium;

import gui.CMDTile;
import system.Point;
import util.Cyclic2DArrayList;

public abstract class BacteriumTile extends CMDTile {
  protected Point pos;
  public BacteriumTile(Point pos) {
    this.pos = pos;
  }
  public abstract void act(Cyclic2DArrayList<BacteriumTile> env);
}
