package bacterium;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import bacterium.settings.Attributes;
import util.RandomUtil;

public class Characteristics {
  public enum Direction {
    UP, DOWN, LEFT, Right
  }

  public enum Attribute {
    spawn(() -> Characteristic.positiveInteger()), attack(() -> Characteristic.positiveInteger()), speed(
        () -> Characteristic.positiveInteger()), strieDirections(() -> Characteristic.attributes(Direction.values()));
    private Supplier<Characteristic<?>> characteristicSupplier;

    Attribute(Supplier<Characteristic<?>> characteristicSupplier) {
      this.characteristicSupplier = characteristicSupplier;
    }

    public Characteristic<?> characteristic() {
      return characteristicSupplier.get();
    }
  }

  Map<String, Characteristic<?>> inner;

  private Characteristics() {
    inner = new HashMap<>();
  }

  public static Characteristics random() {
    Characteristics $ = empty();
    for (int ¢ = 0; ¢ < Attributes.points; ++¢)
      increase($);
    return $;
  }

  private static void increase(Characteristics $) {
    alter($, c -> c.canIncrease(), c -> c.increase());
  }
  
  private static void decrease(Characteristics $) {
    alter($, c -> c.canDecrease(), c -> c.decrease());
  }
  
  private static void alter(Characteristics $, Function<Characteristic<?>, Boolean> canAlter, Consumer<Characteristic<?>> alter) {
    List<Characteristic<?>> cs = new LinkedList<>();
    for (Characteristic<?> ¢ : $.inner.values())
      if (canAlter.apply(¢).booleanValue())
        cs.add(¢);
    alter.accept(RandomUtil.choose(cs));
  }

  private static Characteristics empty() {
    Characteristics $ = new Characteristics();
    for (Attribute ¢ : Attribute.values())
      $.inner.put(¢.name(), ¢.characteristic());
    for (Characteristic<?> ¢ : $.inner.values())
      ¢.initialize();
    return $;
  }
}
