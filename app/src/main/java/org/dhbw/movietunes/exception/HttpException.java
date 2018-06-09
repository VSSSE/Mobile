package org.dhbw.movietunes.exception;

public class HttpException extends RuntimeException {
  public HttpException(String text) {
    super(text);
  }

  public HttpException(String text, Throwable o) {
    super(text, o);
  }
}
