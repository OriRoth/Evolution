package system;

public interface Tile {
  Content getContent();
  void setContent(Content c);
  Point getPosition();
  void setPosition(Point p);
  void draw();
}
