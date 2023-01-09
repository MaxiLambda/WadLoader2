package lincks.maximilian.wadloader2.domain3.rules;

import lincks.maximilian.wadloader2.domain3.repos.WadRepo;
import lincks.maximilian.wadloader2.domain3.tags.Tag;
import lincks.maximilian.wadloader2.domain3.wads.Wad;
import lincks.maximilian.wadloader2.domain3.wads.WadPack;

import java.util.List;
import java.util.Optional;

public class TagRuleDomainService {
    public static List<String> getWadTagIds(WadPack wadPack, WadRepo wadRepo){
        return wadPack.getWads()
                .values()
                .stream()
                .map(wadRepo::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(Wad::tags)
                .flatMap(List::stream)
                .map(Tag::tagId)
                .toList();
    }
}
