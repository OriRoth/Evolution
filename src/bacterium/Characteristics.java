package bacterium;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import bacterium.settings.Attributes;
import bacterium.strategies.Strategy;
import util.RandomUtil;

public class Characteristics {
  public enum Direction {
    UP(-1, 0), Right(0, 1), DOWN(1, 0), LEFT(0, -1);
    public final int xd, yd;

    Direction(int xd, int yd) {
      this.xd = xd;
      this.yd = yd;
    }
  }

  public enum Attribute {
    spawn(() -> Characteristic.positiveInteger()), attack(() -> Characteristic.positiveInteger()), speed(
        () -> Characteristic.positiveInteger()), strideDirections(
            () -> Characteristic.attributes(Direction.values())), strategy(
                () -> Characteristic.choice(Strategy.basic(), Strategy.all()));
    private Supplier<Characteristic<?>> characteristicSupplier;

    Attribute(Supplier<Characteristic<?>> characteristicSupplier) {
      this.characteristicSupplier = characteristicSupplier;
    }

    public Characteristic<?> characteristic() {
      return characteristicSupplier.get();
    }
  }

  Map<Attribute, Characteristic<?>> inner;

  private Characteristics() {
    inner = new HashMap<>();
  }

  @SuppressWarnings("unchecked")
  public <T> T get(Attribute a, Class<T> x) {
    return (T) inner.get(a).get();
  }

  public Object get(Attribute ¢) {
    return inner.get(¢).get();
  }

  public int getInt(Attribute ¢) {
    return ((Integer) inner.get(¢).get()).intValue();
  }

  public Characteristics copy() {
    Characteristics $ = new Characteristics();
    $.inner.putAll(inner);
    return $;
  }

  public static Characteristics random() {
    Characteristics $ = empty();
    for (int ¢ = 0; ¢ < Attributes.points; ++¢)
      $.increase();
    return $;
  }

  public static Characteristics spawn(Characteristics ¢) {
    return RandomUtil.random.nextInt(100) >= Attributes.mutation_rate ? clone(¢) : mutate(¢);
  }

  public static Characteristics clone(Characteristics ¢) {
    return ¢;
  }

  public static Characteristics mutate(Characteristics ¢) {
    Characteristics $ = ¢.copy();
    $.decrease();
    $.increase();
    return null;
  }

  private void increase() {
    alter(c -> c.canIncrease(), c -> c.increase());
  }

  private void decrease() {
    alter(c -> c.canDecrease(), c -> c.decrease());
  }

  private void alter(Function<Characteristic<?>, Boolean> canAlter, Consumer<Characteristic<?>> alter) {
    List<Characteristic<?>> cs = new LinkedList<>();
    for (Characteristic<?> ¢ : inner.values())
      if (canAlter.apply(¢).booleanValue())
        cs.add(¢);
    alter.accept(RandomUtil.choose(cs));
  }

  private static Characteristics empty() {
    Characteristics $ = new Characteristics();
    for (Attribute ¢ : Attribute.values())
      $.inner.put(¢, ¢.characteristic());
    for (Characteristic<?> ¢ : $.inner.values())
      ¢.initialize();
    return $;
  }
}
