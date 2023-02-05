package lincks.maximilian.wadloader2.ddd0plugins.ui.tabs.tag;

import lincks.maximilian.wadloader2.ddd0plugins.ui.utility.CheckboxList;
import lincks.maximilian.wadloader2.ddd0plugins.ui.utility.WadConfigFilterCheckBoxList;
import lincks.maximilian.wadloader2.ddd2application.tags.CustomTagMarker;
import lincks.maximilian.wadloader2.ddd3domain.wads.WadConfig;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import static lincks.maximilian.wadloader2.ddd0plugins.ui.UIConstants.ADD_TAGS;
import static lincks.maximilian.wadloader2.ddd0plugins.ui.UIConstants.REMOVE_TAGS;


public class WadConfigCheckboxList {
    private WadConfigCheckboxList(){}
    public static  <T extends WadConfig> CheckboxList<T> of(String name,CustomTagMarker marker){
        return WadConfigFilterCheckBoxList.of(List.of(), name, Map.of(REMOVE_TAGS, remove(marker), ADD_TAGS, add(marker)));
    }

    private static <T  extends WadConfig> Consumer<List<T>> remove(CustomTagMarker marker){
        //TODO open Dialog to remove Custom-Tags from all WadConfigs in list
        return list -> {};
    }

    private static <T  extends WadConfig> Consumer<List<T>> add(CustomTagMarker marker){
        //TODO open Dialog to add Custom-Tags from all WadConfigs in list
        return list -> {};
    }
}
