package lincks.maximilian.wadloader2.ddd3domain.rules;

import lincks.maximilian.wadloader2.ddd3domain.repos.WadReadWriteRepo;
import lincks.maximilian.wadloader2.ddd3domain.tags.Tag;
import lincks.maximilian.wadloader2.ddd3domain.wads.Wad;
import lincks.maximilian.wadloader2.ddd3domain.wads.WadPack;

import java.util.List;
import java.util.Optional;

public class TagRuleDomainService {

    private TagRuleDomainService(){}
    public static List<String> getWadTagIds(WadPack wadPack, WadReadWriteRepo wadRepo){
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
