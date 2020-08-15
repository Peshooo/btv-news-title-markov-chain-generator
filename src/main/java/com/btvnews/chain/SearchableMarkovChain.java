package com.btvnews.chain;

import com.btvnews.model.PrefixSum;
import com.btvnews.model.SecondOrderEntry;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

class SearchableMarkovChain {
  private Map<SecondOrderEntry, ArrayList<PrefixSum>> prefixSums;

  SearchableMarkovChain() {
    prefixSums = new HashMap<>();
  }
  
  void add(SecondOrderEntry key, ArrayList<PrefixSum> prefixSumsList) {
    prefixSums.put(key, prefixSumsList);
  }

  String getNext(String first, String second) {
    SecondOrderEntry key = new SecondOrderEntry(first, second);
    ArrayList<PrefixSum> list = prefixSums.get(key);

    if (list == null || list.isEmpty()) {
      throw new RuntimeException("Key should have been present");
    }

    long sum = list.get(list.size() - 1).getSum();
    long randomPick = ThreadLocalRandom.current().nextLong(1, sum + 1);

    return binarySearch(list, randomPick);
  }

  private String binarySearch(ArrayList<PrefixSum> list, long value) {
    int left = -1, right = list.size() - 1;

    while (right - left > 1) {
      int middle = (left + right) / 2;

      if (list.get(middle).getSum() >= value) {
        right = middle;
      } else {
        left = middle;
      }
    }

    return list.get(right).getWord();
  }

  void saveToFile() {
    String filename = "chain/chain.txt";
    File file = new File(filename);

    try (PrintWriter printWriter = new PrintWriter(file)) {
      for (Map.Entry<SecondOrderEntry, ArrayList<PrefixSum>> entry : prefixSums.entrySet()) {
        printWriter.print(String.format("%s %s", entry.getKey().getFirst(), entry.getKey().getSecond()));
        ArrayList<PrefixSum> list = entry.getValue();

        for (PrefixSum prefixSum : list) {
          printWriter.print(String.format(" %s %d", prefixSum.getWord(), prefixSum.getSum()));
        }

        printWriter.println();
      }
    } catch(IOException e) {
    }
  }
}
