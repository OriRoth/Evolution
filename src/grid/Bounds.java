package grid;

public class Bounds {
  protected int x, y;

  protected Bounds(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public static Bounds measures(int x, int y) {
    return new Bounds(x, y);
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }
}
