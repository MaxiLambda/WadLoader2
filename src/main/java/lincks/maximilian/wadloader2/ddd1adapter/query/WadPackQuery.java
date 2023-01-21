package lincks.maximilian.wadloader2.ddd1adapter.query;

import lincks.maximilian.wadloader2.ddd3domain.repos.WadPackRepo;
import lincks.maximilian.wadloader2.ddd3domain.wads.WadPack;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WadPackQuery implements WadConfigQuery<WadPack> {
    private final WadPackRepo wadPackRepo;

    public List<WadPack> getAll() {
        return wadPackRepo.findAll();
    }
}
