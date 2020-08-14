package com.btvnews.chain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SearchableMarkovChain {
  private Map<SecondOrderEntry, ArrayList<PrefixSum>> partialSums;

  public SearchableMarkovChain() {
    partialSums = new HashMap<>();
  }

  public void append(SecondOrderEntry key, String word, int occurrences) {
    ArrayList<PrefixSum> list = partialSums.getOrDefault(key, new ArrayList<>());
    if (list.isEmpty()) {
      list.add(new PrefixSum(word, occurrences));
    } else {
      list.add(new PrefixSum(word, list.get(list.size() - 1).getSum()));
    }
  }

  public void search(SecondOrderEntry key, int sum) {

  }
}
