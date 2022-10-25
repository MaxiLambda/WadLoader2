package wads;

import model.tags.Tag;
import model.Wad;

import java.util.List;


/**
 * Everything that consists of Wad(s).
 * Has its unique Tag.
*/
public interface WadElement {
    List<Wad> wads();

    List<? extends Tag> tags();
}
