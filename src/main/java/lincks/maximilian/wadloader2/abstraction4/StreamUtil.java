package lincks.maximilian.wadloader2.abstraction4;

import java.util.function.Function;
import java.util.function.Predicate;

public class StreamUtil {
    public static <T,R> Predicate<T> filter(Function<T,R> mapper, Predicate<R> predicate){
        return (T obj) -> predicate.test(mapper.apply(obj));
    }

    public static <T,T2,R> Predicate<T> filter(Function<T,T2> mapper1, Function<T2,R> mapper2, Predicate<R> predicate){

        return (T obj) -> predicate.test( mapper2.compose(mapper1).apply(obj));
    }
}
