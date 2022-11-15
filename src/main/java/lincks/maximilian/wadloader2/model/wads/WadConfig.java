package lincks.maximilian.wadloader2.model.wads;

import lincks.maximilian.wadloader2.model.tags.Tag;

import java.util.List;


/**
 * Everything that consists of Wad(s).
 * Has its unique Tag.
*/
public interface WadConfig {
    List<? extends SingleWad> allWads();

    List<? extends Tag> tags();

    /**
     *
     * @return true if the tag was added, false otherwise
     */
    boolean addCustomTag(String name) ;

    boolean removeCustomTag(String name) ;
}
