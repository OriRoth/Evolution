package bacterium;

import bacterium.Characteristics.Attribute;
import bacterium.settings.rules;
import bacterium.strategies.Strategy;
import system.Point;
import util.Cyclic2DArrayList;

public class BacteriaTile extends BacteriumTile {
  private Characteristics characteristics;
  private int moves;

  public BacteriaTile(Point pos) {
    super(pos);
    characteristics = Characteristics.random();
    moves = characteristics.getInt(Attribute.spawn);
  }

  public BacteriaTile(Point pos, Characteristics cs) {
    super(pos);
    characteristics = cs;
    moves = characteristics.getInt(Attribute.spawn);
  }

  @Override
  public void act(Cyclic2DArrayList<BacteriumTile> env) {
    assert env != null;
    assert this.equals(env.get(pos));
    if (moves <= 0)
      return;
    --moves;
    rules.move(env, this, characteristics.get(Attribute.strategy, Strategy.class).decide(env, this));
  }

  public Characteristics characteristics() {
    return characteristics;
  }

}
