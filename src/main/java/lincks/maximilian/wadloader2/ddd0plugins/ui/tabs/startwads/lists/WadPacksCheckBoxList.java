package lincks.maximilian.wadloader2.ddd0plugins.ui.tabs.startwads.lists;

import lincks.maximilian.wadloader2.ddd0plugins.ui.tabs.startwads.exceptions.TooManyWadPacksException;
import lincks.maximilian.wadloader2.ddd0plugins.ui.utility.CheckboxList;
import lincks.maximilian.wadloader2.ddd2application.game.Game;
import lincks.maximilian.wadloader2.ddd3domain.wads.WadPack;

import javax.swing.*;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import static lincks.maximilian.wadloader2.ddd0plugins.ui.UIConstants.*;

public class WadPacksCheckBoxList {
    private WadPacksCheckBoxList (){}

    public static CheckboxList<WadPack> of(Game game){
        return new CheckboxList<>(List.of(), WAD_PACKS, Map.of(START_CONFIG, startGameHandler(game)));
    }

    private static Consumer<List<WadPack>> startGameHandler(Game game){
        return selectedWadPacks -> {
            if (selectedWadPacks.isEmpty())
                JOptionPane.showMessageDialog(null, NO_WAD_PACK_SELECTED);
            else if (selectedWadPacks.size() > 1)
                //should never be reached because the corresponding CheckboxList should only allow single selection
                throw new TooManyWadPacksException("Too many Wad-Packs selected!");
            game.start(selectedWadPacks.get(0));
        };
    }
}
