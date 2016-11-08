package gui;

public enum Names {
  application("Evolution");
  private String name;

  Names(String name) {
    this.name = name;
  }

  public String get() {
    return name;
  }
}
