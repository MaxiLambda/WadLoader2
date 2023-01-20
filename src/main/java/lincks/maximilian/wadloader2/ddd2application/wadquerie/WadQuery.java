package lincks.maximilian.wadloader2.ddd2application.wadquerie;

import lincks.maximilian.wadloader2.ddd3domain.repos.WadRepo;
import lincks.maximilian.wadloader2.ddd3domain.tags.ImmutableTag;
import lincks.maximilian.wadloader2.ddd3domain.wads.Wad;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WadQuery implements SingleWadQuery<Wad> {

    private final WadRepo wadRepo;

    public List<Wad> getAll() {
        return wadRepo.findAll();
    }
}
