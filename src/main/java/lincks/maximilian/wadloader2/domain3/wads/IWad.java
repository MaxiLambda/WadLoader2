package lincks.maximilian.wadloader2.domain3.wads;

import lincks.maximilian.wadloader2.domain3.tags.ImmutableTag;
import lincks.maximilian.wadloader2.domain3.tags.Tag;
import lombok.Getter;

import javax.persistence.*;
import java.nio.file.Path;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

@Entity
@Table(name = "I_Wads")
@Getter
public class IWad implements SingleWad {

    protected IWad(){}

    public IWad(Path wadPath){
        path = wadPath.toAbsolutePath().toString();
        iWadTag = Tag.iWadTag(wadPath);
        defaultTag = Tag.defaultTag(wadPath);
        customTags = new HashSet<>();
    }

    @Id
    private String path;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "I_Wad_Tag", referencedColumnName = "name")
    private Tag iWadTag;

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "Default_Tag_Name", nullable = false)
    private Tag defaultTag;

    @ManyToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinTable(
            name = "I_Wad_Custom_Tags",
            joinColumns = {@JoinColumn(name = "path")},
            inverseJoinColumns = {@JoinColumn(name = "name")}
    )
    private Set<Tag> customTags;

    @Override
    public List<String> allWadIds() {
        return List.of(path);
    }

    @Override
    public List<? extends SingleWad> allWads() {
        return List.of(this);
    }
    @Override
    public List<ImmutableTag> tags() {
        return Stream.of(
                List.of(iWadTag,defaultTag),
                customTags
        )
                .flatMap(Collection::stream)
                .map(ImmutableTag::new)
                .toList();
    }

    @Override
    public boolean addCustomTag(String name) {
        return customTags.add(Tag.customTag(name));
    }

    @Override
    public boolean removeCustomTag(String name) {
        return customTags.removeIf(tag -> tag.getName().equals(name));
    }
}
