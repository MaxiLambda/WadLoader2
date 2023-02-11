package lincks.maximilian.wadloader2.ddd3domain.wads;

import jakarta.persistence.*;
import lincks.maximilian.wadloader2.ddd3domain.repos.WadPackReadWriteRepo;
import lincks.maximilian.wadloader2.ddd3domain.tags.CustomTag;
import lincks.maximilian.wadloader2.ddd3domain.tags.ImmutableTag;
import lincks.maximilian.wadloader2.ddd3domain.tags.WadPackTag;
import lincks.maximilian.wadloader2.ddd3domain.tags.exception.WadPackTagException;
import lombok.Getter;
import lombok.Setter;

import java.util.*;
import java.util.stream.Stream;

@Table(name = "Wad_Packs")
@Entity
@Getter
public class WadPack implements WadConfig {

    protected WadPack(){}

    public WadPack(String name, IWad iwad, WadPackReadWriteRepo wadPackService) throws WadPackTagException {
        this.name = name;
        this.iwad = iwad.getPath();
        wadPackTag = new WadPackTag(name);
        customTags = new HashSet<>();
        wads = new HashMap<>();

        //validate
        boolean isValidName = wadPackService.findAll()
                .stream()
                .map(WadPack::getWadPackTag)
                .map(WadPackTag::tagName)
                .anyMatch(tagName -> tagName.equals(name));
        if (isValidName)
            throw new WadPackTagException("A WadPack with the name %s already exists!".formatted(name));
    }

    @Id
    private String name;

    @Column(name = "i_wad")
    private String iwad;

    @ManyToMany(fetch = FetchType.EAGER,cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
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
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "Loadorder_Wad_Id_Mapping",
            joinColumns = {@JoinColumn(name = "Wad_Pack_Name", referencedColumnName = "Name")})
    @MapKeyColumn(name = "load_order")
    @Column(name = "Wad_Id")
    private Map<Integer,String> wads;

    public void addWad(Wad wad){
        int maxPosition = wads.keySet()
                .stream()
                .mapToInt(i -> i)
                //if the map is empty return -1 so when 1 is added we input at 0
                        .max().orElse(-1);
        if(maxPosition == Integer.MAX_VALUE) throw new WadPackAddException();
        //increase to write to new max position
        maxPosition++;
        Map<Integer, String> newWads = new HashMap<>(wads);
        newWads.put(maxPosition,wad.getPath());
        wads = newWads;
    }

    @Override
    public List<String> allWadIds() {
        return Stream.concat(
                wads.values()
                        .stream(),
                Stream.of(iwad)
        ).toList();
    }

    @Override
    public List<ImmutableTag> tags() {
        return Stream.of(
                List.of(wadPackTag),
                customTags
        )
                .flatMap(Collection::stream)
                .map(ImmutableTag::new)
                .toList();
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

    @Override
    public String toString() {
        return name;
    }
}
