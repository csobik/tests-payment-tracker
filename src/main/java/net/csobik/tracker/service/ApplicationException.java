package net.csobik.tracker.service;

/**
 * Internal server exception
 *
 */
public class ApplicationException extends RuntimeException {

  private static final long serialVersionUID = -9152545354543000928L;
  private final String code;

  public ApplicationException(String code, String message) {
    super(message);
    this.code = code;
  }

  public ApplicationException(String code, String message, Throwable cause) {
    super(message, cause);
    this.code = code;
  }
}
