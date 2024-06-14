package nl.itvitae.buildachar.security;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import org.springframework.stereotype.Component;

@Component
public class PasswordValidator {
  private static final int MIN_LENGTH = 8;
  private static final String DIGIT_PATTERN = "[0-9]";
  private static final String LOWERCASE_PATTERN = "[a-z]";
  private static final String UPPERCASE_PATTERN = "[A-Z]";
  private static final String SPECIAL_CHARACTER_PATTERN = "[!@#$%&*()_+=|<>?{}\\\\[\\\\]~-]";

  public PasswordValidationResult validate(String password) {
    if (password == null) {
      throw new IllegalArgumentException("password is null");
    }

    List<String> errors = new ArrayList<>();

    if (password.length() < MIN_LENGTH) {
      errors.add("password must have at least " + MIN_LENGTH + " characters");
    }

    if (isNotMatch(DIGIT_PATTERN, password)) {
      errors.add("password must have at least one digit");
    }
    if (isNotMatch(LOWERCASE_PATTERN, password)) {
      errors.add("password must have at least one lowercase letter");
    }
    if (isNotMatch(UPPERCASE_PATTERN, password)) {
      errors.add("password must have at least one uppercase letter");
    }
    if (isNotMatch(SPECIAL_CHARACTER_PATTERN, password)) {
      errors.add("password must have at least one special character");
    }

    return new PasswordValidationResult(errors.isEmpty(), errors);
  }

  private boolean isNotMatch(String pattern, String value) {
    return !Pattern.compile(pattern).matcher(value).find();
  }
}
