package lincks.maximilian.wadloader2.model.wads;

import lincks.maximilian.wadloader2.model.tags.Tag;

import java.util.List;


/**
 * Everything that consists of Wad(s).
 * Has its unique Tag.
*/
public interface WadElement {
    List<Wad> wads();

    List<? extends Tag> tags();
}
