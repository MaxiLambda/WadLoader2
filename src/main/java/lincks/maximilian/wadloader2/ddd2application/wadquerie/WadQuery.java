package lincks.maximilian.wadloader2.ddd2application.wadquerie;

import lincks.maximilian.wadloader2.ddd3domain.repos.WadRepo;
import lincks.maximilian.wadloader2.ddd3domain.tags.CustomTag;
import lincks.maximilian.wadloader2.ddd3domain.wads.Wad;
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

    public Map<String, List<Wad>> getAllWadsGroupedByCustomTag() {
        HashMap<String,List<Wad>> groupedList = new HashMap<>();
                wadRepo.findAll().forEach(wad -> wad.customTags()
                .stream()
                .map(CustomTag::tagName)
                .forEach(name -> {
                    if (groupedList.containsKey(name))
                        groupedList.get(name).add(wad);
                    else groupedList.put(name, new ArrayList<>(List.of(wad)));
                }));
        return groupedList;
    }
}
