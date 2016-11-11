package bacterium.strategies;

import java.util.LinkedList;
import java.util.List;

import bacterium.BacteriaTile;
import bacterium.BacteriumTile;
import util.Cyclic2DArrayList;

public interface Strategy {
  BacteriumTile decide(Cyclic2DArrayList<BacteriumTile> env, BacteriaTile t);
  
  static Strategy basic() {
    return EagerBlind.self();
  }
  
  static List<Strategy> all() {
    return new LinkedList<>();
  }
}
