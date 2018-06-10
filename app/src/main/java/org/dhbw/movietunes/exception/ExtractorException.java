package org.dhbw.movietunes.exception;

public class ExtractorException extends RuntimeException {

  public ExtractorException(String text) {
    super(text);
  }

  public ExtractorException(String text, Throwable o) {
    super(text, o);
  }
}
