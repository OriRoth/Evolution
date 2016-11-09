package util;

import java.util.ArrayList;

public class CyclicArrayList<T> extends ArrayList<T> {
  private static final long serialVersionUID = 4869127174553116905L;
  @Override
  public T get(int index) {
    return super.get(isEmpty() ? index : index % size());
  }
}
