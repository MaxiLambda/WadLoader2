package lincks.maximilian.wadloader2.ddd0plugins.ui.tabs;

import lincks.maximilian.wadloader2.ddd0plugins.ui.tabs.tag.WadConfigCheckboxList;
import lincks.maximilian.wadloader2.ddd0plugins.ui.utility.CheckboxList;
import lincks.maximilian.wadloader2.ddd1adapter.query.IWadQuery;
import lincks.maximilian.wadloader2.ddd1adapter.query.WadPackQuery;
import lincks.maximilian.wadloader2.ddd1adapter.query.WadQuery;
import lincks.maximilian.wadloader2.ddd2application.tags.CustomTagMarker;
import lincks.maximilian.wadloader2.ddd3domain.wads.IWad;
import lincks.maximilian.wadloader2.ddd3domain.wads.Wad;
import lincks.maximilian.wadloader2.ddd3domain.wads.WadPack;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;

import static lincks.maximilian.wadloader2.ddd0plugins.ui.UIConstants.*;

@Component
public class TagTab extends JFrame implements WadLoader2Tab {

    private final CheckboxList<IWad> iWads;
    private final CheckboxList<Wad> wads;
    private final CheckboxList<WadPack> wadPacks;
    private final transient IWadQuery iWadQuery;
    private final transient WadQuery wadQuery;
    private final transient WadPackQuery wadPackQuery;

    public TagTab(WadQuery wadQuery, IWadQuery iWadQuery, WadPackQuery wadPackQuery, CustomTagMarker customTagMarker) {
        this.iWadQuery = iWadQuery;
        this.wadQuery = wadQuery;
        this.wadPackQuery = wadPackQuery;

        setLayout(new GridLayout(0,3));
        iWads = WadConfigCheckboxList.of(WADS,customTagMarker);
        wads = WadConfigCheckboxList.of(I_WADS,customTagMarker);
        wadPacks = WadConfigCheckboxList.of(WAD_PACKS,customTagMarker);

        add(iWads);
        add(wads);
        add(wadPacks);
    }

    @Override
    public void updateData() {
        iWads.clear();
        iWads.addAll(iWadQuery.getAll());
        wads.clear();
        wads.addAll(wadQuery.getAll());
        wadPacks.clear();
        wadPacks.addAll(wadPackQuery.getAll());
    }
}
