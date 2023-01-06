package lincks.maximilian.wadloader2.domain3.wads;

import lincks.maximilian.wadloader2.abstraction4.StreamUtil;
import lincks.maximilian.wadloader2.domain3.tags.CustomTag;
import lincks.maximilian.wadloader2.domain3.tags.Tag;
import lincks.maximilian.wadloader2.domain3.tags.TagType;

import java.util.List;


/**
 * Everything that consists of Wad(s).
 * Has its unique Tag.
*/
public interface WadConfig {
    List<String> allWadIds();

    List<? extends Tag> tags();

    /**
     *
     * @return true if the tag was added, false otherwise
     */
    boolean addCustomTag(String name) ;

    boolean removeCustomTag(String name) ;

    default List<CustomTag> customTags(){
        return (List<CustomTag>) tags()
                .stream()
                .filter(StreamUtil.filter(Tag::tagType, TagType.CUSTOM_TAG::equals))
                .toList();
    }

    default boolean removeCustomTag(CustomTag customTag){
        return removeCustomTag(customTag.tagName());
    }
}
