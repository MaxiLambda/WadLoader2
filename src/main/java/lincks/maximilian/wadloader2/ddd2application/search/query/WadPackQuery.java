package lincks.maximilian.wadloader2.ddd2application.search.query;

import lincks.maximilian.wadloader2.ddd2application.search.dto.WadPackDto;
import lincks.maximilian.wadloader2.ddd2application.search.mapper.WadPackMapper;
import lincks.maximilian.wadloader2.ddd3domain.repos.WadPackReadWriteRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WadPackQuery implements WadConfigQuery<WadPackDto> {
    private final WadPackReadWriteRepo wadPackRepo;

    public void delete(WadPackDto wadPack){
        wadPackRepo.deleteById(wadPack.wadPackName());
    }

    public List<WadPackDto> getAll() {
        return wadPackRepo.findAll()
                .stream()
                .map(WadPackMapper::toDto)
                .toList();
    }
}
