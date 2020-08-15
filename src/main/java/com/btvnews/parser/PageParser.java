package com.btvnews.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PageParser {
  public List<String> getTitlesOnPage(String section, int pageNumber) throws IOException {
    String url = buildUrlFromSectionAndPageNumber(section, pageNumber);
    Optional<Document> document = getDocumentWithRetriesAndTimeout(url, 3, 10000);
    Elements titles = document
        .map(d -> d.getElementsByClass("title"))
        .orElse(new Elements(Collections.emptyList()));

    return titles.stream()
        .map(Element::text)
        .collect(Collectors.toList());
  }

  private Optional<Document> getDocumentWithRetriesAndTimeout(String url, int retries, int timeout) throws IOException {
    for (int i = 1; i <= retries; i++) {
      try {
        return Optional.of(Jsoup.connect(url).timeout(timeout).get());
      } catch(Exception e) {
      }
    }

    return Optional.empty();
  }

  private String buildUrlFromSectionAndPageNumber(String section, int pageNumber) {
    return String.format("https://btvnovinite.bg/%s/?page=%d", section ,pageNumber);
  }
}
