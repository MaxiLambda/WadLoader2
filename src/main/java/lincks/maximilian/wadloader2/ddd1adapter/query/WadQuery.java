package lincks.maximilian.wadloader2.ddd1adapter.query;

import lincks.maximilian.wadloader2.ddd3domain.repos.WadRepo;
import lincks.maximilian.wadloader2.ddd3domain.wads.Wad;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WadQuery implements SingleWadQuery<Wad> {

    private final WadRepo wadRepo;

    public List<Wad> getAll() {
        return wadRepo.findAll();
    }
}
