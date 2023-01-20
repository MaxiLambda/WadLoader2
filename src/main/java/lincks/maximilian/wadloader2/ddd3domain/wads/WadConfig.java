package lincks.maximilian.wadloader2.ddd3domain.wads;

import lincks.maximilian.wadloader2.ddd3domain.tags.CustomTag;
import lincks.maximilian.wadloader2.ddd3domain.tags.Tag;
import lincks.maximilian.wadloader2.ddd3domain.tags.TagType;
import lincks.maximilian.wadloader2.ddd4abstraction.StreamUtil;

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
