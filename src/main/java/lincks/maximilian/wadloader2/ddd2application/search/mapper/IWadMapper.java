package lincks.maximilian.wadloader2.ddd2application.search.mapper;

import lincks.maximilian.wadloader2.ddd2application.search.dto.IWadDto;
import lincks.maximilian.wadloader2.ddd2application.search.mapper.exceptions.IWadNotFoundException;
import lincks.maximilian.wadloader2.ddd3domain.repos.IWadReadWriteRepo;
import lincks.maximilian.wadloader2.ddd3domain.tags.ImmutableTag;
import lincks.maximilian.wadloader2.ddd3domain.wads.IWad;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class IWadMapper {
    private final IWadReadWriteRepo iWadRepo;
    public static IWadDto toDto(IWad iwad){
        return new IWadDto(
                iwad.getPath(),
                new ImmutableTag(iwad.getWadTag()),
                new ImmutableTag(iwad.getDefaultTag()),
                iwad.getCustomTags().stream().map(ImmutableTag::new).collect(Collectors.toUnmodifiableSet()));
    }

    public IWad fromDto(IWadDto dto){
        return iWadRepo.findById(dto.path()).orElseThrow(IWadNotFoundException::new);
    }
}
