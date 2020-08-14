package com.btvnews;

import com.btvnews.parser.PageParser;
import com.btvnews.printer.TitlePrinter;
import com.google.common.collect.ImmutableMap;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * Hello world!
 *
 */
public class App 
{
    private static final Map<String, Integer> SECTIONS_BY_NUMBER_OF_PAGES =
        ImmutableMap.<String, Integer>builder()
            .put("bulgaria", 13000)
            .put("svetut", 8000)
            .put("pari", 1000)
            .put("predavania", 1000)
            .put("covid19bg", 76)
            .build();

    public static void main( String[] args ) throws IOException {
        for (Map.Entry<String, Integer> entry : SECTIONS_BY_NUMBER_OF_PAGES.entrySet()) {
            String section = entry.getKey();
            int pages = entry.getValue();

            TitlePrinter titlePrinter = new TitlePrinter(section);

        }
    }
}
