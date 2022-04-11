package com.springboot.redtest.common;

import java.util.UUID;

public class Utility {
    // image extensions
    final static String[] imageExtensions = { "image/png", "image/jpg", "image/gif", "image/jpeg" };

    /**
     * Generate UUID with length 16 characters.
     * Used in public id's
     * @return UUID
     */
    public static String UUID16() {
        String uuid = UUID.randomUUID ().toString ().replace ("-", "");
        return uuid.substring(0, uuid.length() / 2);
    }

    /**
     * Generate UUID with length 25 characters
     * Used in uploaded image name
     * @return UUID
     */
    public static String UUID25() {
        String uuid = UUID.randomUUID ().toString ().replace ("-", "");
        return uuid.substring(0, 25);
    }

    /**
     * Check image extension
     * @param imageExtension
     * @return boolean
     */
    public static boolean checkImageExtension(String imageExtension) {
        if (imageExtension == null) return false;

        for (String imgExtension : imageExtensions) {
            if (imgExtension.equals(imageExtension)) return true;
        }

        return false;
    }

}
