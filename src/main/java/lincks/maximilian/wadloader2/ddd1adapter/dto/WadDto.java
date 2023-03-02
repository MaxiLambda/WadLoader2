package lincks.maximilian.wadloader2.ddd1adapter.dto;

import lincks.maximilian.wadloader2.ddd3domain.tags.ImmutableTag;

import java.util.Set;

public record WadDto(String path,
                     ImmutableTag wadTag,
                     ImmutableTag defaultTag,
                     Set<ImmutableTag> customTag) {
}
