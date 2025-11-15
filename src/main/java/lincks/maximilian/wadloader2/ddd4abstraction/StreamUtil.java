package lincks.maximilian.wadloader2.ddd4abstraction;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Gatherer;

public interface StreamUtil {
    static <T, R> Predicate<T> filter(Function<T, R> mapper, Predicate<R> predicate) {
        return (T obj) -> predicate.test(mapper.apply(obj));
    }

    static <T, T2, R> Predicate<T> filter(Function<T, T2> mapper1, Function<T2, R> mapper2, Predicate<R> predicate) {

        return (T obj) -> predicate.test(mapper2.compose(mapper1).apply(obj));
    }

    interface ThrowingPredicate<T, E extends Exception> {
        boolean test(T t) throws E;
    }

    interface ThrowingFunction<T, R, E extends Exception> {
        R apply(T t) throws E;
    }

    static <T, E extends Exception> Predicate<T> falseOnError(ThrowingPredicate<T, E> predicate) {
        return (T t) -> {
            try {
                return predicate.test(t);
            } catch (Exception e) {
                return false;
            }
        };
    }

    static <T, R, E extends Exception> Function<T, Optional<R>> emptyOnError(ThrowingFunction<T, R, E> f) {
        return (T t) -> {
            try {
                return Optional.of(f.apply(t));
            } catch (Exception e) {
                return Optional.empty();
            }
        };
    }

    static <T, R> Gatherer<T, ?, R> filterMap(Function<T, Optional<R>> mapper) {
        return Gatherer.of((_, element, downstream) ->
                mapper.apply(element)
                        .map(downstream::push)
                        .orElse(true)
        );
    }

}
