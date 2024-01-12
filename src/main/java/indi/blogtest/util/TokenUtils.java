package indi.blogtest.util;

import java.util.UUID;

public class TokenUtils {
    public static String generateToken() {
        return UUID.randomUUID().toString();
    }
}
