package lincks.maximilian.wadloader2.ddd2application.search.mapper;

import lincks.maximilian.wadloader2.ddd2application.search.dto.WadDto;
import lincks.maximilian.wadloader2.ddd2application.search.mapper.exceptions.WadNotFoundException;
import lincks.maximilian.wadloader2.ddd3domain.repos.WadReadWriteRepo;
import lincks.maximilian.wadloader2.ddd3domain.tags.ImmutableTag;
import lincks.maximilian.wadloader2.ddd3domain.wads.Wad;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WadMapper {
    private final WadReadWriteRepo wadRepo;
    public static WadDto toDto(Wad wad){
        return new WadDto(wad.getPath(),
                new ImmutableTag(wad.getWadTag()),
                new ImmutableTag(wad.getDefaultTag()),
                wad.getCustomTags().stream().map(ImmutableTag::new).collect(Collectors.toUnmodifiableSet()));
    }

    public Wad fromDto(WadDto dto){
        return wadRepo.findById(dto.path()).orElseThrow(WadNotFoundException::new);
    }
}
