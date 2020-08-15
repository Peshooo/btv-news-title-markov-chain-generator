package com.btvnews.chain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MarkovChainFacade {
  private static final String START_KEYWORD = "START";
  private static final String END_KEYWORD = "END";

  private MarkovChain markovChain;
  private SearchableMarkovChain searchableMarkovChain;

  public MarkovChainFacade() {
    markovChain = new MarkovChain();
  }

  public void convertToSearchableMarkovChainAndSaveToFile() {
    searchableMarkovChain = markovChain.toSearchableMarkovChain();
    searchableMarkovChain.saveToFile();
  }

  public void addTitlesToChain(List<String> titles) {
    for (String title : titles) {
      addTitleToChain(title);
    }
  }

  private void addTitleToChain(String title) {
    addWordsToChain(Arrays.asList(title.split(" ")));
  }

  private void addWordsToChain(List<String> words) {
    String first = START_KEYWORD;
    String second = START_KEYWORD;

    for (String word : words) {
      markovChain.record(first, second, word);
      first = second;
      second = word;
    }

    markovChain.record(first, second, END_KEYWORD);
  }

  public String generate() {
    List<String> title = new ArrayList<>();

    generateTitleAsListOfWords(START_KEYWORD, START_KEYWORD, title);

    return String.join(" ", title);
  }

  private void generateTitleAsListOfWords(String first, String second, List<String> title) {
    String next = searchableMarkovChain.getNext(first, second);

    if (!next.equals(END_KEYWORD)) {
      title.add(next);
      generateTitleAsListOfWords(second, next, title);
    }
  }
}
