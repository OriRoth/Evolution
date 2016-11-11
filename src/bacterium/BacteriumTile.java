package bacterium;

import bacterium.Characteristics.Direction;
import gui.CMDTile;
import system.Point;
import util.Cyclic2DArrayList;

public abstract class BacteriumTile extends CMDTile {
  protected Point pos;

  public BacteriumTile(Point pos) {
    this.pos = pos;
  }

  @Override
  public Point getPosition() {
    return pos;
  }

  @Override
  public void setPosition(Point ¢) {
    this.pos = ¢;
  }

  public void act(Cyclic2DArrayList<BacteriumTile> env) {

  }

  public void refresh() {

  }

  public BacteriumTile at(Cyclic2DArrayList<BacteriumTile> env, Direction d) {
    return env.get(pos.x + d.xd, pos.y + d.yd);
  }
}
