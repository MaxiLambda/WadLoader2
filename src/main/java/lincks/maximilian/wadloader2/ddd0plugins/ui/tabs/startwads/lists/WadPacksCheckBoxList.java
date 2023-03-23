package lincks.maximilian.wadloader2.ddd0plugins.ui.tabs.startwads.lists;

import lincks.maximilian.wadloader2.ddd0plugins.ui.tabs.startwads.exceptions.TooManyWadPacksException;
import lincks.maximilian.wadloader2.ddd0plugins.ui.utility.CheckboxList;
import lincks.maximilian.wadloader2.ddd0plugins.ui.utility.WadConfigFilterCheckBoxList;
import lincks.maximilian.wadloader2.ddd2application.search.dto.WadPackDto;
import lincks.maximilian.wadloader2.ddd2application.search.mapper.WadPackMapper;
import lincks.maximilian.wadloader2.ddd2application.game.Game;

import javax.swing.*;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import static lincks.maximilian.wadloader2.ddd0plugins.ui.UIConstants.*;

public class WadPacksCheckBoxList {
    private WadPacksCheckBoxList (){}

    public static CheckboxList<WadPackDto> of(Game game, WadPackMapper mapper){
        return WadConfigFilterCheckBoxList.of(List.of(), WAD_PACKS, Map.of(START_CONFIG, startGameHandler(game, mapper)));
    }

    private static Consumer<List<WadPackDto>> startGameHandler(Game game, WadPackMapper mapper){
        return selectedWadPacks -> {
            if (selectedWadPacks.isEmpty())
                JOptionPane.showMessageDialog(null, NO_WAD_PACK_SELECTED);
            else if (selectedWadPacks.size() > 1)
                //should never be reached because the corresponding CheckboxList should only allow single selection
                throw new TooManyWadPacksException("Too many Wad-Packs selected!");
            game.start(mapper.fromDto(selectedWadPacks.get(0)));
        };
    }
}
