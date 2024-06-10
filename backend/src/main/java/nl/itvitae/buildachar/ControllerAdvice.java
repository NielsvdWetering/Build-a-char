package nl.itvitae.buildachar;

import nl.itvitae.buildachar.exceptions.RestException;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {
  @ExceptionHandler(RestException.class)
  public ResponseEntity<ProblemDetail> handleRestException(RestException exception) {
    return ResponseEntity.status(exception.getStatus())
        .body(ProblemDetail.forStatusAndDetail(exception.getStatus(), exception.getMessage()));
  }
}
