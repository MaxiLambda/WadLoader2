package lincks.maximilian.wadloader2.wads;

import lincks.maximilian.wadloader2.model.tags.*;
import lombok.Getter;

import javax.persistence.*;
import java.nio.file.Path;
import java.util.Collection;
import java.util.HashSet;
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
        wadPackTags = new HashSet<>();
        customTags = new HashSet<>();
    }

    @Column
    @Id
    private String path;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "Wad_Tag", referencedColumnName = "name")
    private WadTag wadTag;

    @ManyToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinTable(
            name = "Wad_Wad_Pack_Tag",
            joinColumns = {@JoinColumn(name = "path")},
            inverseJoinColumns = {@JoinColumn(name = "name")}
    )
    private Set<WadPackTag> wadPackTags;

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "Default_Tag_Name", nullable = false)
    private DefaultTag defaultTag;

    @ManyToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinTable(
            name = "Wad_Custom_Tags",
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
