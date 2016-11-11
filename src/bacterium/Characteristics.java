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
    UP, DOWN, LEFT, Right
  }

  public enum Attribute {
    spawn(() -> Characteristic.positiveInteger()), attack(() -> Characteristic.positiveInteger()), speed(
        () -> Characteristic.positiveInteger()), strieDirections(
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

  Map<String, Characteristic<?>> inner;

  private Characteristics() {
    inner = new HashMap<>();
  }

  @SuppressWarnings("unchecked")
  public <T> T get(String s, Class<T> x) {
    return (T) inner.get(s).get();
  }

  public int getInt(String ¢) {
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
      $.inner.put(¢.name(), ¢.characteristic());
    for (Characteristic<?> ¢ : $.inner.values())
      ¢.initialize();
    return $;
  }
}
