package com.btvnews.printer;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class TitlePrinter implements Closeable {
  private final PrintWriter printWriter;

  public TitlePrinter(String section) throws IOException {
    String filename = String.format("titles/%s.txt", section);
    File file = new File(filename);
    printWriter = new PrintWriter(file);
  }

  public void print(List<String> titles) {
    titles.forEach(printWriter::println);
  }

  public void print(String title) {
    printWriter.println(title);
  }

  @Override
  public void close() {
    printWriter.close();
  }
}
