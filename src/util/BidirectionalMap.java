package util;

import java.util.HashMap;
import java.util.Map;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class BidirectionalMap<K, V> extends HashMap<K, V> {
  private static final long serialVersionUID = 6813163784499665097L;
  Map<V, K> reversed = new HashMap<>();

  @Override
  public V put(K key, V value) {
    reversed.put(value, key);
    return super.put(key, value);
  }

  @Override
  public void putAll(Map<? extends K, ? extends V> k) {
    for (java.util.Map.Entry<? extends K, ? extends V> ¢ : k.entrySet())
      reversed.put(¢.getValue(), ¢.getKey());
    super.putAll(k);
  }

  @Override
  public V putIfAbsent(K key, V value) {
    throw new NotImplementedException();
  }

  @Override
  public V get(Object key) {
    if (containsKey(key))
      return super.get(key);
    for (java.util.Map.Entry<V, K> ¢ : reversed.entrySet())
      if (¢.getValue().equals(key))
        return ¢.getKey();
    return null;
  }
}
