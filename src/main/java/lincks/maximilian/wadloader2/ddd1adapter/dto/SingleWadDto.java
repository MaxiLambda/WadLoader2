package lincks.maximilian.wadloader2.ddd1adapter.dto;

import lincks.maximilian.wadloader2.ddd3domain.tags.ImmutableTag;

public interface SingleWadDto extends WadConfigDto{
    ImmutableTag getDefaultTag();
}
