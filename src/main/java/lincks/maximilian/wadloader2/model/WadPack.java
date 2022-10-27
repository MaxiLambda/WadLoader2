package lincks.maximilian.wadloader2.model;

import lincks.maximilian.wadloader2.model.tags.CustomTag;
import lincks.maximilian.wadloader2.model.tags.Tag;
import lincks.maximilian.wadloader2.model.tags.WadPackTag;
import lincks.maximilian.wadloader2.wads.WadElement;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

@Table(name = "WadPacks")
@Entity
public class WadPack implements WadElement {
    //Todo: make sure each WadPack contains an IWad
    protected WadPack(){}

    public WadPack(String name){
        this.name = name;
        wadPackTag = new WadPackTag(name);
    }

    @Id
    private String name;

    @ManyToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinTable(
            name = "WadPack_CustomTags",
            joinColumns = {@JoinColumn(name = "packName")},
            inverseJoinColumns = {@JoinColumn(name = "tagName")}
    )
    private Set<CustomTag> customTags;

    @OneToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "WadPackTag", referencedColumnName = "name")
    private WadPackTag wadPackTag;

    @ManyToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinTable(
            name = "WadPack_Wad",
            joinColumns = {@JoinColumn(name = "name")},
            inverseJoinColumns = {@JoinColumn(name = "path")}
    )
    private Set<Wad> wads;

    @Override
    public List<Wad> wads() {
        return wads.stream().toList();
    }

    @Override
    public List<? extends Tag> tags() {
        return Stream.of(
                List.of(wadPackTag),
                customTags
        ).flatMap(Collection::stream).toList();
    }

    public boolean addWad(Wad wad){
        //Todo: return false if a second IWad is added
        return wads.add(wad);
    }
}
