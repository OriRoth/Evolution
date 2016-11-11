package bacterium;

import util.RandomUtil;

public class NameGenerator {
  private static char current = (char) (RandomUtil.random.nextInt(127 - '!') + '!');
  public static String get() {
    String $ = String.valueOf(current);
    ++current;
    if (current >= 127)
      current = '!';
    return $;
  }
}
