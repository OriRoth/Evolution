package util;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class RandomUtil {
  public static Random random = new Random();

  public static <T> T choose(List<T> ¢) {
    return ¢ == null || ¢.isEmpty() ? null : ¢.get(random.nextInt(¢.size()));
  }

  @SafeVarargs
  public static <T> T choose(T... ¢) {
    return choose(Arrays.asList(¢));
  }
}
