package lincks.maximilian.wadloader2.application2.wadquerie;

import lincks.maximilian.wadloader2.domain3.repos.WadRepo;
import lincks.maximilian.wadloader2.domain3.tags.CustomTag;
import lincks.maximilian.wadloader2.domain3.wads.Wad;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WadQuery {

    private final WadRepo wadRepo;

    public List<Wad> getAllWads() {
        return wadRepo.findAll();
    }

    /**
     * Returns all Wads grouped by their parent Folders
     */
    public Map<String, List<Wad>> getAllWadsGroupedByFolder() {
        return wadRepo.findAll().stream().collect(Collectors.groupingBy(wad -> wad.getDefaultTag().tagName()));
    }

    public Map<String, Set<Wad>> getAllWadsGroupedByCustomTag() {
        return wadRepo.findAll().stream().reduce(
                new HashMap<>(),
                (acc, wad) -> {
                        wad.customTags()
                                .stream()
                                .map(CustomTag::tagName)
                                .forEach(name -> {
                                    if (acc.containsKey(name))
                                        acc.get(name).add(wad);
                                    else acc.put(name, new HashSet<>(Set.of(wad)));
                                });
                        return acc;
                },
                (acc1, acc2) -> {
                    acc2.forEach((key, value) -> {
                        if (acc1.containsKey(key))
                            acc1.get(key).addAll(acc2.get(key));
                        else acc1.put(key, acc2.get(key));
                    });
                    return acc1;
                }
                );


    }
}
