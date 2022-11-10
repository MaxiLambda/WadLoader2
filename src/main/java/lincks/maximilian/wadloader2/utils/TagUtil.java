package lincks.maximilian.wadloader2.utils;

import lincks.maximilian.wadloader2.model.tags.Tag;
import lincks.maximilian.wadloader2.repos.services.AbstractService;

import java.util.function.Function;

public class TagUtil {
    public static <T extends Tag> boolean existsTagName(AbstractService<T,String> service, String name){
        return existsTag(service,name,Tag::tagName);
    }

    public static <T extends Tag> boolean existsTagId(AbstractService<T,String> service, String tagId){
        return existsTag(service,tagId,Tag::tagId);
    }

    private static <T extends Tag> boolean existsTag(AbstractService<T,String> service, String stringToCheck, Function<T,String> comparedValueMapper){
        return service.findAll()
                .stream()
                .map(comparedValueMapper)
                .noneMatch(stringToCheck::equals);
    }
}
