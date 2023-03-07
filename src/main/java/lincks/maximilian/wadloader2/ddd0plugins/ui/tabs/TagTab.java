package lincks.maximilian.wadloader2.ddd0plugins.ui.tabs;

import lincks.maximilian.wadloader2.ddd0plugins.ui.tabs.tag.WadConfigCheckboxList;
import lincks.maximilian.wadloader2.ddd0plugins.ui.utility.CheckboxList;
import lincks.maximilian.wadloader2.ddd2application.search.dto.IWadDto;
import lincks.maximilian.wadloader2.ddd2application.search.dto.WadDto;
import lincks.maximilian.wadloader2.ddd2application.search.dto.WadPackDto;
import lincks.maximilian.wadloader2.ddd2application.search.mapper.WadConfigMapper;
import lincks.maximilian.wadloader2.ddd2application.search.query.IWadQuery;
import lincks.maximilian.wadloader2.ddd2application.search.query.WadPackQuery;
import lincks.maximilian.wadloader2.ddd2application.search.query.WadQuery;
import lincks.maximilian.wadloader2.ddd2application.tags.CustomTagMarker;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;

import static lincks.maximilian.wadloader2.ddd0plugins.ui.UIConstants.*;

@Component
public class TagTab extends JPanel implements WadLoader2Tab {

    private final CheckboxList<IWadDto> iWads;
    private final CheckboxList<WadDto> wads;
    private final CheckboxList<WadPackDto> wadPacks;
    private final transient IWadQuery iWadQuery;
    private final transient WadQuery wadQuery;
    private final transient WadPackQuery wadPackQuery;

    public TagTab(WadQuery wadQuery, IWadQuery iWadQuery, WadPackQuery wadPackQuery, CustomTagMarker customTagMarker, WadConfigMapper wadConfigMapper) {
        this.iWadQuery = iWadQuery;
        this.wadQuery = wadQuery;
        this.wadPackQuery = wadPackQuery;

        setLayout(new GridLayout(0,3));
        iWads = WadConfigCheckboxList.of(I_WADS,customTagMarker, wadConfigMapper);
        wads = WadConfigCheckboxList.of(WADS,customTagMarker, wadConfigMapper);
        wadPacks = WadConfigCheckboxList.of(WAD_PACKS,customTagMarker, wadConfigMapper);

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
