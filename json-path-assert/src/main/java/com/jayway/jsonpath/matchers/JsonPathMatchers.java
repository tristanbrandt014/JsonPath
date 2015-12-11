package com.jayway.jsonpath.matchers;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.Predicate;
import com.jayway.jsonpath.ReadContext;
import org.hamcrest.Matcher;

import java.io.File;

import static org.hamcrest.Matchers.*;

public class JsonPathMatchers {

    private JsonPathMatchers() {
        throw new AssertionError("prevent instantiation");
    }

    public static Matcher<? super Object> hasJsonPath(String jsonPath) {
        return hasJsonPath(jsonPath, not(anyOf(nullValue(), empty())));
    }

    public static <T> Matcher<? super Object> hasJsonPath(final String jsonPath, final Matcher<T> resultMatcher) {
        return isJson(withJsonPath(jsonPath, resultMatcher));
    }

    public static Matcher<Object> isJson() {
        return isJson(withJsonPath("$..*"));
    }

    public static Matcher<Object> isJson(final Matcher<? super ReadContext> matcher) {
        return new IsJson<Object>(matcher);
    }

    public static Matcher<String> isJsonString(final Matcher<? super ReadContext> matcher) {
        return new IsJson<String>(matcher);
    }

    public static Matcher<File> isJsonFile(final Matcher<? super ReadContext> matcher) {
        return new IsJson<File>(matcher);
    }

    public static Matcher<? super ReadContext> withJsonPath(String jsonPath, Predicate... filters) {
        return withJsonPath(JsonPath.compile(jsonPath, filters));
    }

    public static Matcher<? super ReadContext> withJsonPath(JsonPath jsonPath) {
        return withJsonPath(jsonPath, not(anyOf(nullValue(), empty())));
    }

    public static <T> Matcher<? super ReadContext> withJsonPath(String jsonPath, Matcher<T> resultMatcher) {
        return withJsonPath(JsonPath.compile(jsonPath), resultMatcher);
    }

    public static <T> Matcher<? super ReadContext> withJsonPath(final JsonPath jsonPath, final Matcher<T> resultMatcher) {
        return new WithJsonPath<T>(jsonPath, resultMatcher);
    }
}
