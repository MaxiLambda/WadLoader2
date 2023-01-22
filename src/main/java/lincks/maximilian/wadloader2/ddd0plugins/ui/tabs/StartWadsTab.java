package lincks.maximilian.wadloader2.ddd0plugins.ui.tabs;

import lincks.maximilian.wadloader2.ddd0plugins.ui.tabs.startwads.exceptions.TooManyIWadsException;
import lincks.maximilian.wadloader2.ddd0plugins.ui.tabs.startwads.exceptions.TooManyWadPacksException;
import lincks.maximilian.wadloader2.ddd0plugins.ui.utility.CheckboxList;
import lincks.maximilian.wadloader2.ddd1adapter.query.IWadQuery;
import lincks.maximilian.wadloader2.ddd1adapter.query.WadPackQuery;
import lincks.maximilian.wadloader2.ddd1adapter.query.WadQuery;
import lincks.maximilian.wadloader2.ddd2application.game.Game;
import lincks.maximilian.wadloader2.ddd3domain.wads.IWad;
import lincks.maximilian.wadloader2.ddd3domain.wads.Wad;
import lincks.maximilian.wadloader2.ddd3domain.wads.WadPack;
import org.springframework.stereotype.Component;

import javax.swing.*;


import java.awt.*;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import static lincks.maximilian.wadloader2.ddd0plugins.ui.UIConstants.*;

@Component
public class StartWadsTab extends JPanel {
    //TODO add Searchbar and Logic to Search for Wads by Name and Tag
    public StartWadsTab(WadQuery wadQuery, IWadQuery iWadQuery, WadPackQuery wadPackQuery, Game game) {
        setLayout(new GridLayout(0,3));
        CheckboxList<IWad> iWads = new CheckboxList<>(iWadQuery.getAll(), I_WADS, Map.of( START_CONFIG, iWadsSelectedHandler(game)));
        CheckboxList<Wad> wads = new CheckboxList<>(wadQuery.getAll(), WADS, Map.of(START_CONFIG, wadSelectedHandler(game, iWads)),true);
        CheckboxList<WadPack> wadPacks = new CheckboxList<>(wadPackQuery.getAll(), WAD_PACKS, Map.of(START_CONFIG, wadPackSelectedHandler(game)));

        add(iWads);
        add(wads);
        add(wadPacks);
    }


    private Consumer<List<IWad>> iWadsSelectedHandler(Game game) {
        return iwads -> {
            if (iwads.isEmpty())
                JOptionPane.showConfirmDialog(null, NO_I_WAD_SELECTED);
            else if (iwads.size() > 1)
                //should never be reached because the corresponding CheckboxList should only allow single selection
                throw new TooManyIWadsException("Too many IWads selected!");
            game.start(iwads.get(0));
        };
    }

    private Consumer<List<Wad>> wadSelectedHandler(Game game, CheckboxList<IWad> iWadsCheckboxList) {
        return wads -> {
            var iwads = iWadsCheckboxList.getSelected();
            if (iwads.isEmpty())
                JOptionPane.showConfirmDialog(null, NO_I_WAD_SELECTED);
            else if (iwads.size() > 1)
                //should never be reached because the corresponding CheckboxList should only allow single selection
                throw new TooManyIWadsException("Too many IWads selected!");
            game.start(iwads.get(0), wads.toArray(new Wad[0]));
        };
    }

    private Consumer<List<WadPack>> wadPackSelectedHandler(Game game){
        return wadPacks -> {
            if (wadPacks.isEmpty())
                JOptionPane.showConfirmDialog(null, NO_WAD_PACK_SELECTED);
            else if (wadPacks.size() > 1)
                //should never be reached because the corresponding CheckboxList should only allow single selection
                throw new TooManyWadPacksException("Too many Wad-Packs selected!");
            game.start(wadPacks.get(0));
        };
    }
}
