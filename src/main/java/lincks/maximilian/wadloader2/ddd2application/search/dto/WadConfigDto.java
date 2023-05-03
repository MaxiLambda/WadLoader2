package lincks.maximilian.wadloader2.ddd2application.search.dto;

import lincks.maximilian.wadloader2.ddd3domain.tags.ImmutableTag;
import lincks.maximilian.wadloader2.ddd3domain.tags.Tag;
import lincks.maximilian.wadloader2.ddd3domain.tags.TagType;
import lincks.maximilian.wadloader2.ddd4abstraction.StreamUtil;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public sealed interface WadConfigDto permits SingleWadDto, WadPackDto {

    List<ImmutableTag> tags();
    default Set<ImmutableTag> customTags(){
        return tags()
                .stream()
                .filter(StreamUtil.filter(Tag::tagType, TagType.CUSTOM_TAG::equals))
                .collect(Collectors.toUnmodifiableSet());
    }
}
