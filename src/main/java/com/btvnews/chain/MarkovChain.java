package com.btvnews.chain;

import java.util.HashMap;
import java.util.Map;

public class MarkovChain {
  private Map<SecondOrderEntry, OccurrenceCounter> chain;

  public MarkovChain() {
    chain = new HashMap<>();
  }

  public void record(String first, String second, String following) {
    SecondOrderEntry entry = new SecondOrderEntry(first, second);
    chain.putIfAbsent(entry, new OccurrenceCounter());
    chain.get(entry).increment(following);
  }
}
