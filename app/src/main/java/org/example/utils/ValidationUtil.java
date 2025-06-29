package org.example.utils;
import org.example.model.UserInfoDto;

import java.util.regex.Pattern;

public class ValidationUtil {
    // Regex for a basic email check (e.g., user@gmail.com)
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)$";

    // Regex for password: at least 8 chars, one letter, one number
    private static final String PASSWORD_REGEX = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$";

    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);
    private static final Pattern PASSWORD_PATTERN = Pattern.compile(PASSWORD_REGEX);

    public static void validateUserAttributes(UserInfoDto userInfoDto) {
        if (!isValidEmail(userInfoDto.getUserName())) {
            throw new IllegalArgumentException("Invalid email format.");
        }
        if (!isValidPassword(userInfoDto.getPassword())) {
            throw new IllegalArgumentException("Password must be at least 8 characters long and contain both letters and numbers.");
        }
    }

    public static boolean isValidEmail(String email) {
        return email != null && EMAIL_PATTERN.matcher(email).matches();
    }

    public static boolean isValidPassword(String password) {
        return password != null && PASSWORD_PATTERN.matcher(password).matches();
    }
}
