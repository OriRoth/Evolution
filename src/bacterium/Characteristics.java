package bacterium;

import java.util.List;

public class Characteristics {
  public enum Direction {
    UP, DOWN, LEFT, Right
  }
  int spawn;
  List<Direction> strideDirections;
  int attack;
  int speed;
}
