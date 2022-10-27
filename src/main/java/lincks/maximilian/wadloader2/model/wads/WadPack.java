package lincks.maximilian.wadloader2.model.wads;

import lincks.maximilian.wadloader2.model.tags.CustomTag;
import lincks.maximilian.wadloader2.model.tags.Tag;
import lincks.maximilian.wadloader2.model.tags.WadPackTag;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

@Table(name = "Wad_Packs")
@Entity
public class WadPack implements WadElement {
    //Todo: make sure each WadPack contains an IWad
    protected WadPack(){}

    public WadPack(String name){
        this.name = name;
        wadPackTag = new WadPackTag(name);
        customTags = new HashSet<>();
        wads = new HashSet<>();
    }

    @Id
    private String name;

    @ManyToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinTable(
            name = "Wad_Pack_Custom_Tags",
            joinColumns = {@JoinColumn(name = "pack_Name")},
            inverseJoinColumns = {@JoinColumn(name = "tag_Name")}
    )
    private Set<CustomTag> customTags;

    @OneToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "Wad_Pack_Tag", referencedColumnName = "name")
    private WadPackTag wadPackTag;

    @ManyToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinTable(
            name = "Wad_Pack_Wad",
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
