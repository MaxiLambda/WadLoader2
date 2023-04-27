package lincks.maximilian.wadloader2.ddd2application.search.query;

import lincks.maximilian.wadloader2.ddd3domain.repos.CustomTagReadWriteRepo;
import lincks.maximilian.wadloader2.ddd3domain.repos.ReadRepo;
import lincks.maximilian.wadloader2.ddd3domain.repos.readonly.DefaultTagRepo;
import lincks.maximilian.wadloader2.ddd3domain.repos.readonly.IWadTagRepo;
import lincks.maximilian.wadloader2.ddd3domain.repos.readonly.WadPackTagRepo;
import lincks.maximilian.wadloader2.ddd3domain.repos.readonly.WadTagRepo;
import lincks.maximilian.wadloader2.ddd3domain.tags.ImmutableTag;
import lincks.maximilian.wadloader2.ddd3domain.tags.Tag;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagQuery {

    private final List<ReadRepo<? extends Tag, String>> tagRepos;
    private final WadTagRepo wadTagRepo;
    public TagQuery(CustomTagReadWriteRepo customTagRepo, DefaultTagRepo defaultTagRepo, WadPackTagRepo wadPackTagRepo, IWadTagRepo iWadTagRepo, WadTagRepo wadTagRepo) {
        this.wadTagRepo  =wadTagRepo;
        tagRepos = List.of(
                customTagRepo,
                defaultTagRepo,
                wadTagRepo,
                wadPackTagRepo,
                iWadTagRepo);
    }

    public List<Tag> findAllInRepos(){
        return tagRepos.stream()
                .map(ReadRepo::findAll)
                .flatMap(List::stream)
                .map(ImmutableTag::new)
                .map(Tag.class::cast)
                .toList();
    }

    public List<Tag> findAllInWadTagRepo(){
        return wadTagRepo.findAll()
                .stream()
                .map(ImmutableTag::new)
                .map(Tag.class::cast)
                .toList();
    }
}