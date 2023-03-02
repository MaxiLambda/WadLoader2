package lincks.maximilian.wadloader2.ddd1adapter;

import lincks.maximilian.wadloader2.ddd1adapter.dto.IWadDto;
import lincks.maximilian.wadloader2.ddd3domain.tags.ImmutableTag;
import lincks.maximilian.wadloader2.ddd3domain.wads.IWad;

import java.util.stream.Collectors;


public class IWadMapper {
    private IWadMapper(){}
    public static IWadDto toDto(IWad iwad){
        return new IWadDto(
                iwad.getPath(),
                new ImmutableTag(iwad.getWadTag()),
                new ImmutableTag(iwad.getDefaultTag()),
                iwad.getCustomTags().stream().map(ImmutableTag::new).collect(Collectors.toUnmodifiableSet()));
    }
}
