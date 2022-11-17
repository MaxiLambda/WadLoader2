package lincks.maximilian.wadloader2.abstraction;

import java.util.function.Function;
import java.util.function.Predicate;

public class StreamUtil {
    public static <T,R> Predicate<T> filter(Function<T,R> mapper, Predicate<R> predicate){
        return (T obj) -> predicate.test(mapper.apply(obj));
    }
}
