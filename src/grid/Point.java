package grid;

public class Point {
  int x, y;
  protected Point(int x, int y) {
    this.x = x;
    this.y = y;
  }
  public static Point at(int x, int y) {
    return new Point(x, y);
  }
}
