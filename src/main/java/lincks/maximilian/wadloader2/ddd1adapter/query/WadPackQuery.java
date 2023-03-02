package lincks.maximilian.wadloader2.ddd1adapter.query;

import lincks.maximilian.wadloader2.ddd1adapter.WadMapper;
import lincks.maximilian.wadloader2.ddd1adapter.WadPackMapper;
import lincks.maximilian.wadloader2.ddd1adapter.dto.WadPackDto;
import lincks.maximilian.wadloader2.ddd3domain.repos.WadPackReadWriteRepo;
import lincks.maximilian.wadloader2.ddd3domain.tags.CustomTag;
import lincks.maximilian.wadloader2.ddd3domain.tags.Tag;
import lincks.maximilian.wadloader2.ddd3domain.wads.WadPack;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WadPackQuery implements WadConfigQuery<WadPackDto> {
    private final WadPackReadWriteRepo wadPackRepo;

    public void delete(WadPackDto wadPack){
        wadPackRepo.deleteById(wadPack.name());
    }

    @Override
    public Set<WadPackDto> getByCustomTags(Set<CustomTag> tags) {
        return wadPackRepo.findByCustomTagsIn(tags)
                .stream()
                .map(WadPackMapper::toDto)
                .collect(Collectors.toSet());
    }

    public List<WadPackDto> getAll() {
        return wadPackRepo.findAll()
                .stream()
                .map(WadPackMapper::toDto)
                .toList();
    }
}
