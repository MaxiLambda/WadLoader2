package lincks.maximilian.wadloader2.ddd0plugins.ui.utility;

import javax.swing.*;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class FilterCheckBoxList<T> extends CheckboxList<T>{

    private FilterCheckBoxList(List<T> items, String name, Map<String, Consumer<List<T>>> callbacks, boolean allowMultiSelection, JComponent component) {
        super(items,name,callbacks,allowMultiSelection,component);
    }

    public static <T> FilterCheckBoxList<T> of(List<T> items, String name, Map<String, Consumer<List<T>>> callbacks, boolean allowMultiSelection){
        JTextField searchBar = new JTextField();
        return new FilterCheckBoxList<>(items,name, callbacks, allowMultiSelection, searchBar);
    }

    public static <T> FilterCheckBoxList<T> of(List<T> items, String name, Map<String, Consumer<List<T>>> callbacks){
        return of(items,name,callbacks,false);
    }

}
