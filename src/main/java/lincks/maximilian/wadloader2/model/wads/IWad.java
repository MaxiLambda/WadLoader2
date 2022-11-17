package lincks.maximilian.wadloader2.model.wads;

import lincks.maximilian.wadloader2.model.tags.CustomTag;
import lincks.maximilian.wadloader2.model.tags.DefaultTag;
import lincks.maximilian.wadloader2.model.tags.IWadTag;
import lincks.maximilian.wadloader2.model.tags.Tag;
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
        wadTag = new IWadTag(wadPath);
        defaultTag = new DefaultTag(wadPath);
        customTags = new HashSet<>();
    }

    @Id
    private String path;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "I_Wad_Tag", referencedColumnName = "name")
    private IWadTag wadTag;

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "Default_Tag_Name", nullable = false)
    private DefaultTag defaultTag;

    @ManyToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinTable(
            name = "I_Wad_Custom_Tags",
            joinColumns = {@JoinColumn(name = "path")},
            inverseJoinColumns = {@JoinColumn(name = "name")}
    )
    private Set<CustomTag> customTags;

    @Override
    public List<SingleWad> allWads() {
        return List.of(this);
    }

    @Override
    public List<? extends Tag> tags() {
        return Stream.of(
                List.of(wadTag,defaultTag),
                customTags
        ).flatMap(Collection::stream).toList();
    }

    @Override
    public boolean addCustomTag(String name) {
        return customTags.add(new CustomTag(name));
    }

    @Override
    public boolean removeCustomTag(String name) {
        return customTags.removeIf(tag -> tag.tagName().equals(name));
    }
}
