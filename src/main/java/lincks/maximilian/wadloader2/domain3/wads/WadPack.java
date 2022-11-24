package lincks.maximilian.wadloader2.domain3.wads;

import lincks.maximilian.wadloader2.domain3.tags.CustomTag;
import lincks.maximilian.wadloader2.domain3.tags.Tag;
import lincks.maximilian.wadloader2.domain3.tags.WadPackTag;
import lincks.maximilian.wadloader2.domain3.tags.exception.TagException;
import lincks.maximilian.wadloader2.plugins0.jpa.repository.bridge.WadPackTagBridge;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Stream;

@Table(name = "Wad_Packs")
@Entity
@Getter
public class WadPack implements WadConfig {

    protected WadPack(){}

    public WadPack(String name, IWad iwad, WadPackTagBridge wadPackTagService) throws TagException {
        this.name = name;
        this.iwad = iwad;
        wadPackTag = new WadPackTag(name);
        customTags = new HashSet<>();
        wads = new HashMap<>();

        //validate
        if (wadPackTagService.exists(name))
            throw new TagException("A WadPack with the name %s already exists!".formatted(name));
    }

    @Id
    private String name;

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "i_wad")
    private IWad iwad;

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

    @Setter
    @ManyToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinTable(
            name = "Wad_Pack_Wad",
            joinColumns = {@JoinColumn(name = "name")},
            inverseJoinColumns = {@JoinColumn(name = "path")}
    )
    private Map<Integer,Wad> wads;

    public void addWad(Wad wad){
        int maxPosition = wads.keySet()
                .stream()
                .mapToInt(i -> i)
                //if the map is empty return -1 so when 1 is added we input at 0
                        .max().orElse(-1);
        if(maxPosition == Integer.MAX_VALUE) throw new WadPackAddException();
        Map<Integer, Wad> newWads = new HashMap<>(wads);
        newWads.put(maxPosition,wad);
        wads = newWads;
    }

    @Override
    public List<? extends SingleWad> allWads() {
        return Stream.concat(wads.values().stream(),Stream.of(iwad)).toList();
    }

    @Override
    public List<? extends Tag> tags() {
        return Stream.of(
                List.of(wadPackTag),
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

    @Override
    public boolean equals(Object o){
        if (Objects.isNull(o) || !(o instanceof WadPack)) return false;
        else return name.equals(((WadPack) o).name);
    }
}
