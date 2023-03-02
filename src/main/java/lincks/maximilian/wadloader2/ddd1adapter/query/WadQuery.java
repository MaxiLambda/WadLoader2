package lincks.maximilian.wadloader2.ddd1adapter.query;

import lincks.maximilian.wadloader2.ddd1adapter.dto.WadDto;
import lincks.maximilian.wadloader2.ddd1adapter.mapper.WadMapper;
import lincks.maximilian.wadloader2.ddd3domain.repos.WadReadWriteRepo;
import lincks.maximilian.wadloader2.ddd3domain.tags.CustomTag;
import lincks.maximilian.wadloader2.ddd3domain.tags.DefaultTag;
import lincks.maximilian.wadloader2.ddd3domain.wads.WadPack;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WadQuery implements SingleWadQuery<WadDto> {

    private final WadReadWriteRepo wadRepo;

    public List<WadDto> getAll() {
        return wadRepo.findAll()
                .stream()
                .map(WadMapper::toDto)
                .toList();
    }

    @Override
    public Optional<WadDto> getById(String id) {
        return wadRepo.findById(id)
                .map(WadMapper::toDto);
    }

    @Override
    public List<WadDto> getByDefaultTag(String path) {
        return wadRepo.findByDefaultTag(new DefaultTag(Path.of(path)))
                .stream()
                .map(WadMapper::toDto)
                .toList();
    }

    public void delete(WadDto wad){
        wadRepo.deleteById(wad.path());
    }

    @Override
    public Set<WadDto> getByCustomTags(Set<CustomTag> tags) {
        return wadRepo.findByCustomTagsIn(tags)
                .stream()
                .map(WadMapper::toDto)
                .collect(Collectors.toSet());
    }

    public List<WadDto> getAllByWadPack(WadPack pack){
        return getById(pack.allWadIds());
    }
}
