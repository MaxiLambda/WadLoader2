package lincks.maximilian.wadloader2.ddd1adapter.dto;

import lincks.maximilian.wadloader2.ddd3domain.tags.ImmutableTag;

public sealed interface SingleWadDto extends WadConfigDto permits IWadDto, WadDto {
    ImmutableTag getDefaultTag();
}
