package bacterium;

import java.util.Map.Entry;

import bacterium.settings.Attributes;
import gui.CMDGrid;
import system.Point;
import util.BidirectionalMap;
import util.Cyclic2DArrayList;
import util.CyclicArrayList;

public class BacteriumGrid extends CMDGrid {
  private BidirectionalMap<String, Characteristics> types;
  private Cyclic2DArrayList<BacteriumTile> grid;
  private static BacteriumGrid instance;

  private BacteriumGrid() {
    types = new BidirectionalMap<>();
    grid = new Cyclic2DArrayList<>();
    for (int i = 0; i < Attributes.rows_count; ++i) {
      CyclicArrayList<BacteriumTile> t = new CyclicArrayList<>();
      for (int j = 0; j < Attributes.columns_count; ++j)
        t.add(new EmptyTile(Point.at(i, j)));
      grid.add(t);
    }
  }
  
  public static BacteriumGrid instance() {
    return instance != null ? instance : (instance = new BacteriumGrid());
  }
  
  protected String putType(Characteristics c) {
    for (Entry<String, Characteristics> ¢ : types.entrySet())
      if (¢.getValue().equals(c))
        return ¢.getKey();
    String $ = NameGenerator.get();
    types.put($, c);
    return $;
  }
}
