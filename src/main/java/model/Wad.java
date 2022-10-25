package model;

import model.tags.*;
import wads.WadElement;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Stream;

public class Wad implements WadElement {

    private Wad(){}

    public Wad(Path wadPath) {
        this.path = wadPath;
        wadTag = new WadTag(path);
        defaultTag = new DefaultTag(path);
    }

    private Path path;

    private WadTag wadTag;
    private HashSet<WadPackTag> wadPackTags;

    private DefaultTag defaultTag;

    private HashSet<CustomTag> customTags;

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
