package lincks.maximilian.wadloader2.ddd1adapter.query;

import lincks.maximilian.wadloader2.ddd3domain.repos.WadRepo;
import lincks.maximilian.wadloader2.ddd3domain.wads.Wad;
import lincks.maximilian.wadloader2.ddd3domain.wads.WadPack;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WadQuery implements SingleWadQuery<Wad> {

    private final WadRepo wadRepo;

    public List<Wad> getAll() {
        return wadRepo.findAll();
    }

    @Override
    public Optional<Wad> getById(String id) {
        return wadRepo.findById(id);
    }

    public void delete(Wad wad){
        wadRepo.delete(wad);
    }

    public List<Wad> getAllByWadPack(WadPack pack){
        return getById(pack.allWadIds());
    }
}
