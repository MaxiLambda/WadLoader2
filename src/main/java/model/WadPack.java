package model;

import jakarta.persistence.*;
import model.tags.CustomTag;
import model.tags.Tag;
import model.tags.WadPackTag;
import wads.WadElement;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
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
            name = "Wad_CustomTags",
            joinColumns = {@JoinColumn(name = "name")},
            inverseJoinColumns = {@JoinColumn(name = "name")}
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
