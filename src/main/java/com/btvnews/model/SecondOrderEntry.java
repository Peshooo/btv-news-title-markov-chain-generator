package com.btvnews.model;

import java.util.Objects;

public class SecondOrderEntry {
  private String first;
  private String second;

  public SecondOrderEntry(String first, String second) {
    this.first = first;
    this.second = second;
  }

  public String getFirst() {
    return first;
  }

  public void setFirst(String first) {
    this.first = first;
  }

  public String getSecond() {
    return second;
  }

  public void setSecond(String second) {
    this.second = second;
  }

  @Override
  public int hashCode() {
    return Objects.hash(first, second);
  }

  @Override
  public boolean equals(Object obj) {
    if (!obj.getClass().equals(SecondOrderEntry.class)) {
      return false;
    }

    SecondOrderEntry o = (SecondOrderEntry) obj;

    return first.equals(o.first) && second.equals(o.second);
  }
}
