package com.btvnews.chain;

public class PrefixSum {
  private String word;
  private long sum;

  public PrefixSum(String word, long sum) {
    this.word = word;
    this.sum = sum;
  }

  public String getWord() {
    return word;
  }

  public long getSum() {
    return sum;
  }
}
