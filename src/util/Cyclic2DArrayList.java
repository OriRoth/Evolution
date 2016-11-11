package util;

import system.Point;;

public class Cyclic2DArrayList<T> extends CyclicArrayList<CyclicArrayList<T>> {
  private static final long serialVersionUID = 733130691846276080L;

  public T get(Point ¢) {
    assert ¢ != null;
    return super.get(¢.x).get(¢.y);
  }
  
  public T get(int x, int y) {
    return super.get(x).get(y);
  }
  
  public T set(Point ¢, T t) {
    assert ¢ != null;
    return super.get(¢.x).set(¢.y, t);
  }
  
  public T set(int x, int y, T t) {
    return super.get(x).set(y, t);
  }
}
