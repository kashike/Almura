/*
 * This file is part of Almura, All Rights Reserved.
 *
 * Copyright (c) AlmuraDev <http://github.com/AlmuraDev/>
 */
package com.almuradev.almura.util;

public class MathUtil {

    public static float convertToRange(float originalValue, float originalMin, float originalMax, float newMin, float newMax) {
        float originalDiff = originalMax - originalMin;
        float newDiff = newMax - newMin;
        float ratio = newDiff / originalDiff;
        return Math.round(((originalValue * ratio) + newMin) * 100.0f) / 100.0f;
    }

    /**
     * Squashes the passed value to fit within the minimum and maximum specified.
     *
     * @param value The value to squash
     * @param min The minimum value to return
     * @param max The maximum value to return
     * @return The value between the minimum and maximum values
     */
    public static float squash(float value, float min, float max) {
        if (min >= max) {
            throw new IllegalArgumentException("Minimum value [" + min + "] cannot be greater than or equal to the maximum value [" + max + "]!");
        } else if (max <= min) {
            throw new IllegalArgumentException("Maximum value [" + max + "] cannot be lesser than or equal to the minimum value [" + min + "]!");
        }
        return Math.min(Math.max(value, min), max);
    }

    /**
     * Check if a value is within a range
     *
     * @param value The value to check
     * @param start The start of the range
     * @param end The end of the range
     * @return True if within the range
     */
    public static boolean withinRange(float value, float start, float end) {
        return value >= start && value <= end;
    }
}
