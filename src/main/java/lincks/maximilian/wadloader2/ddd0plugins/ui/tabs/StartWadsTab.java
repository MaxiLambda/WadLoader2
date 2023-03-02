package lincks.maximilian.wadloader2.ddd0plugins.ui.tabs;

import lincks.maximilian.wadloader2.ddd0plugins.ui.tabs.startwads.lists.IWadsCheckboxList;
import lincks.maximilian.wadloader2.ddd0plugins.ui.tabs.startwads.lists.WadPacksCheckBoxList;
import lincks.maximilian.wadloader2.ddd0plugins.ui.tabs.startwads.lists.WadsCheckBoxList;
import lincks.maximilian.wadloader2.ddd0plugins.ui.utility.CheckboxList;
import lincks.maximilian.wadloader2.ddd1adapter.dto.IWadDto;
import lincks.maximilian.wadloader2.ddd1adapter.dto.WadDto;
import lincks.maximilian.wadloader2.ddd1adapter.dto.WadPackDto;
import lincks.maximilian.wadloader2.ddd1adapter.mapper.IWadMapper;
import lincks.maximilian.wadloader2.ddd1adapter.mapper.WadMapper;
import lincks.maximilian.wadloader2.ddd1adapter.mapper.WadPackMapper;
import lincks.maximilian.wadloader2.ddd1adapter.query.IWadQuery;
import lincks.maximilian.wadloader2.ddd1adapter.query.WadPackQuery;
import lincks.maximilian.wadloader2.ddd1adapter.query.WadQuery;
import lincks.maximilian.wadloader2.ddd2application.game.Game;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;

@Component
public class StartWadsTab extends JPanel implements WadLoader2Tab{
    private final CheckboxList<IWadDto> iWads;
    private final CheckboxList<WadDto> wads;
    private final CheckboxList<WadPackDto> wadPacks;
    private final transient IWadQuery iWadQuery;
    private final transient WadQuery wadQuery;
    private final transient WadPackQuery wadPackQuery;
    public StartWadsTab(WadQuery wadQuery, IWadQuery iWadQuery, WadPackQuery wadPackQuery, Game game, IWadMapper iWadMapper, WadMapper wadMapper, WadPackMapper wadPackMapper){
        this.iWadQuery = iWadQuery;
        this.wadQuery = wadQuery;
        this.wadPackQuery = wadPackQuery;

        setLayout(new GridLayout(0,3));
        iWads = IWadsCheckboxList.of(game, iWadMapper);
        wads = WadsCheckBoxList.of(game,iWads,iWadMapper,wadMapper);
        wadPacks = WadPacksCheckBoxList.of(game, wadPackMapper);

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
