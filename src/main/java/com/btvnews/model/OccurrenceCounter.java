package com.btvnews.chain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class OccurrenceCounter {
  private Map<String, Integer> count;

  public OccurrenceCounter() {
    count = new HashMap<>();
  }

  public void increment(String key) {
    count.put(key, count.getOrDefault(key, 0) + 1);
  }

  public ArrayList<PrefixSum> toPrefixSums() {
    ArrayList<PrefixSum> prefixSums = new ArrayList<>();

    return count.entrySet().stream()
        .map(entry -> new PrefixSum(entry.getKey(), entry.getValue()))
        .collect(Collectors.toCollection(ArrayList::new));
  }
}
