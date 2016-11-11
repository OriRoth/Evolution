package bacterium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import util.Int;
import util.RandomUtil;

public interface Characteristic<T> {
  void initialize();

  void increase();

  void decrease();

  boolean canIncrease();

  boolean canDecrease();

  T get();

  static Characteristic<Int> positiveInteger() {
    return new Characteristic<Int>() {

      Int inner;

      @Override
      public void initialize() {
        inner = Int.valueOf(0);
      }

      @Override
      public void increase() {
        ++inner.inner;
      }

      @Override
      public void decrease() {
        --inner.inner;
      }

      @Override
      public Int get() {
        return inner;
      }

      @Override
      public boolean canIncrease() {
        return true;
      }

      @Override
      public boolean canDecrease() {
        return inner.inner > 0;
      }
    };
  }

  static <E> Characteristic<E> choice(E defaultE, List<E> es) {
    return new Characteristic<E>() {

      E inner;

      @Override
      public void initialize() {
        inner = RandomUtil.choose(es);
      }

      @Override
      public void increase() {
        inner = RandomUtil.choose(es);
      }

      @Override
      public void decrease() {
        inner = defaultE;
      }

      @Override
      public E get() {
        return inner;
      }

      @Override
      public boolean canIncrease() {
        return defaultE.equals(inner);
      }

      @Override
      public boolean canDecrease() {
        return !defaultE.equals(inner);
      }
    };
  }

  static <E extends Enum<?>> Characteristic<List<E>> attributes(List<E> es) {
    return new Characteristic<List<E>>() {

      List<E> inner;

      @Override
      public void initialize() {
        inner = new ArrayList<>();
      }

      @Override
      public void increase() {
        List<E> cs = new LinkedList<>();
        cs.addAll(es);
        cs.removeAll(inner);
        E c = RandomUtil.choose(cs);
        if (c != null)
          inner.add(c);
      }

      @Override
      public void decrease() {
        inner.remove(RandomUtil.random.nextInt(inner.size()));
      }

      @Override
      public List<E> get() {
        return inner;
      }

      @Override
      public boolean canIncrease() {
        return inner.size() < es.size();
      }

      @Override
      public boolean canDecrease() {
        return !inner.isEmpty();
      }
    };
  }

  @SafeVarargs
  static <E extends Enum<?>> Characteristic<List<E>> attributes(E... ¢) {
    return attributes(Arrays.asList(¢));
  }
}
