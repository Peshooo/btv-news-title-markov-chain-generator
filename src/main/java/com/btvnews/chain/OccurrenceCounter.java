package com.btvnews.chain;

import java.util.HashMap;
import java.util.Map;

public class OccurrenceCounter {
  private Map<String, Integer> count;

  public OccurrenceCounter() {
    count = new HashMap<>();
  }

  public void increment(String key) {
    count.put(key, count.getOrDefault(key, 0) + 1);
  }

  public int get(String key) {
    return count.getOrDefault(key, 0);
  }
}
