package com.btvnews;

import com.btvnews.chain.MarkovChainFacade;
import com.btvnews.parser.PageParser;
import com.btvnews.printer.TitlePrinter;
import com.google.common.collect.ImmutableMap;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class App {
  private static final Map<String, Integer> SECTIONS_BY_NUMBER_OF_PAGES =
      ImmutableMap.<String, Integer>builder()
          .put("bulgaria", 13000)
          .put("svetut", 8000)
          .put("pari", 1000)
          .put("predavania", 1000)
          .put("covid19bg", 76)
          .build();

  public static void main(String[] args) throws IOException {
    MarkovChainFacade chainFacade = new MarkovChainFacade();
    PageParser pageParser = new PageParser();

    for (Map.Entry<String, Integer> entry : SECTIONS_BY_NUMBER_OF_PAGES.entrySet()) {
      processSection(chainFacade, pageParser, entry.getKey(), entry.getValue());
    }

    chainFacade.convertToSearchableMarkovChainAndSaveToFile();

    generateAndPrintTitles(chainFacade, 10000);
  }

  private static void processSection(MarkovChainFacade chainFacade, PageParser pageParser,
                                     String section, int pages) throws IOException {
    try (TitlePrinter titlePrinter = new TitlePrinter(section)) {
      processSection(chainFacade, pageParser, section, pages, titlePrinter);
    }
  }

  private static void processSection(MarkovChainFacade chainFacade, PageParser pageParser,
                                     String section, int pages, TitlePrinter titlePrinter) throws IOException {
    for (int page = 1; page <= pages; page++) {
      logIfThousandPages(section, page);

      List<String> titles = pageParser.getTitlesOnPage(section, page);

      titlePrinter.print(titles);
      chainFacade.addTitlesToChain(titles);
    }
  }

  private static void logIfThousandPages(String section, int page) {
    if (page % 1000 == 0) {
      System.out.println(String.format("Processed page %d of section %s.", page, section));
    }
  }

  private static void generateAndPrintTitles(MarkovChainFacade chainFacade, int count) throws IOException {
    try (TitlePrinter titlePrinter = new TitlePrinter("generated")) {
      generateAndPrintTitles(chainFacade, titlePrinter, count);
    }
  }

  private static void generateAndPrintTitles(MarkovChainFacade chainFacade, TitlePrinter titlePrinter, int count) {
    for (int i = 1; i <= count; i++) {
      titlePrinter.print(chainFacade.generate());
    }
  }
}
