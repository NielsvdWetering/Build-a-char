package nl.itvitae.buildachar.security;

import java.util.List;

public record PasswordValidationResult(boolean isValid, List<String> errors) {}
