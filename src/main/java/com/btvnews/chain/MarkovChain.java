package com.btvnews.chain;

import com.btvnews.model.OccurrenceCounter;
import com.btvnews.model.SecondOrderEntry;

import java.util.HashMap;
import java.util.Map;

class MarkovChain {
  private Map<SecondOrderEntry, OccurrenceCounter> chain;

  MarkovChain() {
    chain = new HashMap<>();
  }

  void record(String first, String second, String following) {
    SecondOrderEntry entry = new SecondOrderEntry(first, second);
    chain.putIfAbsent(entry, new OccurrenceCounter());
    chain.get(entry).increment(following);
  }

  SearchableMarkovChain toSearchableMarkovChain() {
    SearchableMarkovChain searchableMarkovChain = new SearchableMarkovChain();

    for (Map.Entry<SecondOrderEntry, OccurrenceCounter> entry : chain.entrySet()) {
      searchableMarkovChain.add(entry.getKey(), entry.getValue().toPrefixSums());
    }

    return searchableMarkovChain;
  }
}
