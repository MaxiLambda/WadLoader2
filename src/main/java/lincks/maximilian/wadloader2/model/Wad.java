package lincks.maximilian.wadloader2.model;

import lincks.maximilian.wadloader2.model.tags.*;
import lincks.maximilian.wadloader2.wads.WadElement;
import lombok.Getter;

import javax.persistence.*;
import java.nio.file.Path;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

@Entity
@Table(name = "Wads")
@Getter
public class Wad implements WadElement {
    //Todo: implement boolean isIWad and do a check (in the constructor based on the path)
    //Todo: if the wad id an IWad or not
    protected Wad(){}

    public Wad(Path wadPath) {
        path = wadPath.toAbsolutePath().toString();
        wadTag = new WadTag(wadPath);
        defaultTag = new DefaultTag(wadPath);
    }

    @Column
    @Id
    private String path;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "WadTag", referencedColumnName = "name")
    private WadTag wadTag;

    @ManyToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinTable(
            name = "Wad_WadPackTag",
            joinColumns = {@JoinColumn(name = "path")},
            inverseJoinColumns = {@JoinColumn(name = "name")}
    )
    private Set<WadPackTag> wadPackTags;

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "DefaultTagName", nullable = false)
    private DefaultTag defaultTag;

    @ManyToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinTable(
            name = "Wad_CustomTags",
            joinColumns = {@JoinColumn(name = "path")},
            inverseJoinColumns = {@JoinColumn(name = "name")}
    )
    private Set<CustomTag> customTags;

    @Override
    public List<Wad> wads() {
        return List.of(this);
    }

    @Override
    public List<? extends Tag> tags() {
        return Stream.of(
                List.of(wadTag,defaultTag),
                customTags,
                wadPackTags
        ).flatMap(Collection::stream).toList();
    }
}
