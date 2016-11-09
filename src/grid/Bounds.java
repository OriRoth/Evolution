package grid;

public class Bounds {
  protected int w, h;

  protected Bounds(int w, int h) {
    this.w = w;
    this.h = h;
  }

  public static Bounds measures(int w, int h) {
    return new Bounds(w, h);
  }

  public int getW() {
    return w;
  }

  public int getH() {
    return h;
  }
}
