package com.btvnews.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**

 */
public class PageParser {
  public List<String> getTitlesOnPage(String section, int pageNumber) throws IOException {
    Document document = Jsoup.connect(buildUrlFromSectionAndPageNumber(section, pageNumber)).get();
    Elements titles = document.body().getElementsByClass("title");

    return titles.stream()
        .map(Element::text)
        .collect(Collectors.toList());
  }

  private String buildUrlFromSectionAndPageNumber(String section, int pageNumber) {
    return String.format("https://btvnovinite.bg/%s/?page=%d", section ,pageNumber);
  }
}
