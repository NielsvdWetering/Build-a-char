package nl.itvitae.buildachar.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class RestException extends RuntimeException {
  private final HttpStatus status;

  public RestException(HttpStatus status) {
    super();

    this.status = status;
  }

  public RestException(HttpStatus status, String message) {
    super(message);

    this.status = status;
  }

  public RestException(HttpStatus status, Throwable cause) {
    super(cause);

    this.status = status;
  }

  public RestException(HttpStatus status, String message, Throwable cause) {
    super(message, cause);

    this.status = status;
  }
}
