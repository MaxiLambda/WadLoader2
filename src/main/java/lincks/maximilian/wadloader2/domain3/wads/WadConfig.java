package lincks.maximilian.wadloader2.domain3.wads;

import lincks.maximilian.wadloader2.domain3.tags.Tag;

import java.util.List;


/**
 * Everything that consists of Wad(s).
 * Has its unique Tag.
*/
public interface WadConfig {
    List<String> allWadIds();

    List<? extends Tag> tags();

    /**
     *
     * @return true if the tag was added, false otherwise
     */
    boolean addCustomTag(String name) ;

    boolean removeCustomTag(String name) ;
}
