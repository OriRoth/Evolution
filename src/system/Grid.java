package system;

import java.util.List;

public interface Grid {
  Bounds getBounds();
  List<Tile> getTiles();
  Tile getTile(int x, int y);
  List<Tile> getRow(int x);
  List<Tile> getColumn(int y);
  void draw();
}
