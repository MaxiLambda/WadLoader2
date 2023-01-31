package lincks.maximilian.wadloader2.ddd1adapter.query;

import lincks.maximilian.wadloader2.ddd3domain.repos.CustomTagReadWriteRepo;
import lincks.maximilian.wadloader2.ddd3domain.repos.NamedItemsRepo;
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


    private final List<NamedItemsRepo<? extends Tag, String>> tagRepos;
    private final List<NamedItemsRepo<? extends Tag, String>> notUniqueTagRepos;

    public TagQuery(CustomTagReadWriteRepo customTagRepo, DefaultTagRepo defaultTagRepo, WadPackTagRepo wadPackTagRepo, IWadTagRepo iWadTagRepo, WadTagRepo wadTagRepo) {
        tagRepos = List.of(
                customTagRepo,
                defaultTagRepo,
                wadTagRepo,
                wadPackTagRepo,
                iWadTagRepo);

        notUniqueTagRepos = List.of(customTagRepo,defaultTagRepo);
    }

    public List<Tag> findByNameUniqueRepos(String name){
        return tagRepos.stream()
                .map(repo -> repo.findByNameContaining(name))
                .flatMap(List::stream)
                .map(ImmutableTag::new)
                .map(Tag.class::cast)
                .toList();
    }

    public List<Tag> findAllUniqueRepos(){
        return tagRepos.stream()
                .map(ReadRepo::findAll)
                .flatMap(List::stream)
                .map(ImmutableTag::new)
                .map(Tag.class::cast)
                .toList();
    }

    public List<Tag> findByNameNotUniqueRepos(String name){
        return notUniqueTagRepos.stream()
                .map(repo -> repo.findByNameContaining(name))
                .flatMap(List::stream)
                .map(ImmutableTag::new)
                .map(Tag.class::cast)
                .toList();
    }

    public List<Tag> findAllNotUniqueRepos(){
        return notUniqueTagRepos.stream()
                .map(ReadRepo::findAll)
                .flatMap(List::stream)
                .map(ImmutableTag::new)
                .map(Tag.class::cast)
                .toList();
    }
}