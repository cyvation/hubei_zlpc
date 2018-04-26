package com.start.boot.support.utils;

import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.Map;

/**
 * 断言工具类
 */
public abstract class AssertUtils {

    /**
     * @param expression
     * @param message
     */
    public static void isTrue(boolean expression, String message) {
        if (!expression)
            throw new IllegalArgumentException(message);
        else
            return;
    }

    /**
     * @param expression
     */
    public static void isTrue(boolean expression) {
        isTrue(expression, "[Assertion failed] - this expression must be true");
    }

    /**
     * @param object
     * @param message
     */
    public static void isNull(Object object, String message) {
        if (object != null)
            throw new IllegalArgumentException(message);
        else
            return;
    }

    /**
     * @param object
     */
    public static void isNull(Object object) {
        isNull(object, "[Assertion failed] - the object argument must be null");
    }

    /**
     * @param object
     * @param message
     */
    public static void notNull(Object object, String message) {
        if (object == null)
            throw new IllegalArgumentException(message);
        else if (object instanceof String) {
            if (StringUtils.isEmpty(object)) {
                throw new NullPointerException(message);
            }
        }
        return;
    }

    /**
     * @param object
     */
    public static void notNull(Object object) {
        notNull(object, "[Assertion failed] - this argument is required; it must not be null");
    }

    /**
     * @param text
     * @param message
     */
    public static void hasLength(String text, String message) {
        if (!StringUtils.hasLength(text))
            throw new IllegalArgumentException(message);
        else
            return;
    }

    /**
     * @param text
     */
    public static void hasLength(String text) {
        hasLength(text, "[Assertion failed] - this String argument must have length; it must not be null or empty");
    }

    /**
     * @param text
     * @param message
     */
    public static void hasText(String text, String message) {
        if (!StringUtils.hasText(text))
            throw new IllegalArgumentException(message);
        else
            return;
    }

    /**
     * @param text
     */
    public static void hasText(String text) {
        hasText(text, "[Assertion failed] - this String argument must have text; it must not be null, empty, or blank");
    }

    /**
     * @param textToSearch
     * @param substring
     * @param message
     */
    public static void doesNotContain(String textToSearch, String substring, String message) {
        if (StringUtils.hasLength(textToSearch) && StringUtils.hasLength(substring) && textToSearch.indexOf(substring) != -1)
            throw new IllegalArgumentException(message);
        else
            return;
    }

    /**
     * @param textToSearch
     * @param substring
     */
    public static void doesNotContain(String textToSearch, String substring) {
        doesNotContain(textToSearch, substring, (new StringBuilder("[Assertion failed] - this String argument must not contain the substring [")).append(substring).append("]").toString());
    }

    /**
     * @param array
     * @param message
     */
    public static void notEmpty(Object array[], String message) {
        if (ObjectUtils.isEmpty(array))
            throw new IllegalArgumentException(message);
        else
            return;
    }

    /**
     * @param array
     */
    public static void notEmpty(Object array[]) {
        notEmpty(array, "[Assertion failed] - this array must not be empty: it must contain at least 1 element");
    }

    /**
     * @param array
     * @param message
     */
    public static void noNullElements(Object array[], String message) {
        if (array != null) {
            for (int i = 0; i < array.length; i++)
                if (array[i] == null)
                    throw new IllegalArgumentException(message);

        }
    }

    /**
     * @param array
     */
    public static void noNullElements(Object array[]) {
        noNullElements(array, "[Assertion failed] - this array must not contain any null elements");
    }

    /**
     * @param collection
     * @param message
     */
    public static void notEmpty(Collection<?> collection, String message) {
        if (CollectionUtils.isEmpty(collection))
            throw new IllegalArgumentException(message);
        else
            return;
    }

    public static void notEmpty(Collection<?> collection) {
        notEmpty(collection, "[Assertion failed] - this collection must not be empty: it must contain at least 1 element");
    }

    /**
     * @param map
     * @param message
     */
    public static void notEmpty(Map<?, ?> map, String message) {
        if (CollectionUtils.isEmpty(map))
            throw new IllegalArgumentException(message);
        else
            return;
    }

    /**
     * @param map
     */
    public static void notEmpty(Map<?, ?> map) {
        notEmpty(map, "[Assertion failed] - this map must not be empty; it must contain at least one entry");
    }

    /**
     * @param clazz
     * @param obj
     */
    public static void isInstanceOf(Class<?> clazz, Object obj) {
        isInstanceOf(clazz, obj, "");
    }

    /**
     * @param type
     * @param obj
     * @param message
     */
    public static void isInstanceOf(Class<?> type, Object obj, String message) {
        notNull(type, "Type to check against must not be null");
        if (!type.isInstance(obj))
            throw new IllegalArgumentException((new StringBuilder(String.valueOf(message))).append("Object of class [").append(obj == null ? "null" : obj.getClass().getName()).append("] must be an instance of ").append(type).toString());
        else
            return;
    }

    /**
     * @param superType
     * @param subType
     */
    public static void isAssignable(Class<?> superType, Class<?> subType) {
        isAssignable(superType, subType, "");
    }

    /**
     * @param superType
     * @param subType
     * @param message
     */
    public static void isAssignable(Class<?> superType, Class<?> subType, String message) {
        notNull(superType, "Type to check against must not be null");
        if (subType == null || !superType.isAssignableFrom(subType))
            throw new IllegalArgumentException((new StringBuilder(String.valueOf(message))).append(subType).append(" is not assignable to ").append(superType).toString());
        else
            return;
    }

    /**
     * @param expression
     * @param message
     */
    public static void state(boolean expression, String message) {
        if (!expression)
            throw new IllegalStateException(message);
        else
            return;
    }

    /**
     * @param expression
     */
    public static void state(boolean expression) {
        state(expression, "[Assertion failed] - this state invariant must be true");
    }
}