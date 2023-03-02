package lincks.maximilian.wadloader2.ddd1adapter.dto;

import lincks.maximilian.wadloader2.ddd3domain.tags.ImmutableTag;

import java.util.Map;
import java.util.Set;

public record WadPackDto(String name,
                         String iWad,
                         Set<ImmutableTag> customTags,
                         ImmutableTag wadPackTag,
                         Map<Integer, String> wads
                         ) {
}
