package lincks.maximilian.wadloader2.ddd2application.search.query;

import lincks.maximilian.wadloader2.ddd2application.search.dto.IWadDto;
import lincks.maximilian.wadloader2.ddd2application.search.mapper.IWadMapper;
import lincks.maximilian.wadloader2.ddd3domain.repos.IWadReadWriteRepo;
import lincks.maximilian.wadloader2.ddd3domain.tags.CustomTag;
import lincks.maximilian.wadloader2.ddd3domain.tags.DefaultTag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IWadQuery implements SingleWadQuery<IWadDto> {
    private final IWadReadWriteRepo iWadRepo;

    public void delete(IWadDto iWad){
        iWadRepo.deleteById(iWad.path());
    }

    @Override
    public Set<IWadDto> getByCustomTags(Set<CustomTag> tags) {
        return iWadRepo.findByCustomTagsIn(tags)
                .stream()
                .map(IWadMapper::toDto)
                .collect(Collectors.toSet());
    }

    @Override
    public Optional<IWadDto> getById(String id) {
        return iWadRepo.findById(id).map(IWadMapper::toDto);
    }

    @Override
    public List<IWadDto> getByDefaultTag(String path) {
        return iWadRepo.findByDefaultTag(new DefaultTag(Path.of(path)))
                .stream()
                .map(IWadMapper::toDto)
                .toList();
    }

    public List<IWadDto> getAll() {
        return iWadRepo.findAll()
                .stream()
                .map(IWadMapper::toDto)
                .toList();
    }
}
