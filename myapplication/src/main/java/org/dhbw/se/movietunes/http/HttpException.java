package org.dhbw.se.movietunes.http;

public class HttpException extends RuntimeException {
  public HttpException(String text) {
    super(text);
  }
  public HttpException(String text, Throwable o) {
    super(text, o);
  }
}
