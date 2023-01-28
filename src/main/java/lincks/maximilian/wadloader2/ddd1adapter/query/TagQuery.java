package lincks.maximilian.wadloader2.ddd1adapter.query;

import lincks.maximilian.wadloader2.ddd3domain.repos.readonly.*;
import lincks.maximilian.wadloader2.ddd3domain.tags.ImmutableTag;
import lincks.maximilian.wadloader2.ddd3domain.tags.Tag;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagQuery {


    private final List<ReadOnlyRepo<? extends Tag, String>> tagRepos;
    private final List<ReadOnlyRepo<? extends Tag, String>> notUniqueTagRepos;

    public TagQuery(CustomTagRepo customTagRepo, DefaultTagRepo defaultTagRepo, WadPackTagRepo wadPackTagRepo, IWadTagRepo iWadTagRepo, WadTagRepo wadTagRepo) {
        tagRepos = List.of(
                customTagRepo,
                defaultTagRepo,
                wadTagRepo,
                wadPackTagRepo,
                iWadTagRepo);

        notUniqueTagRepos = List.of(customTagRepo,defaultTagRepo);
    }

    public List<ImmutableTag> findByNameUniqueRepos(String name){
        return tagRepos.stream()
                .map(repo -> repo.findByNameContaining(name))
                .flatMap(List::stream)
                .map(ImmutableTag::new)
                .toList();
    }

    public List<ImmutableTag> findAllUniqueRepos(){
        return tagRepos.stream()
                .map(ReadOnlyRepo::findAll)
                .flatMap(List::stream)
                .map(ImmutableTag::new)
                .toList();
    }

    public List<ImmutableTag> findByNameNotUniqueRepos(String name){
        return notUniqueTagRepos.stream()
                .map(repo -> repo.findByNameContaining(name))
                .flatMap(List::stream)
                .map(ImmutableTag::new)
                .toList();
    }

    public List<ImmutableTag> findAllNotUniqueRepos(){
        return notUniqueTagRepos.stream()
                .map(ReadOnlyRepo::findAll)
                .flatMap(List::stream)
                .map(ImmutableTag::new)
                .toList();
    }
}