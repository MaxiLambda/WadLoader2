package lincks.maximilian.wadloader2.ddd0plugins.ui.tabs.startwads.lists;

import lincks.maximilian.wadloader2.ddd0plugins.ui.tabs.startwads.exceptions.TooManyIWadsException;
import lincks.maximilian.wadloader2.ddd0plugins.ui.utility.CheckboxList;
import lincks.maximilian.wadloader2.ddd0plugins.ui.utility.WadConfigFilterCheckBoxList;
import lincks.maximilian.wadloader2.ddd2application.search.dto.IWadDto;
import lincks.maximilian.wadloader2.ddd2application.search.dto.WadDto;
import lincks.maximilian.wadloader2.ddd2application.search.mapper.IWadMapper;
import lincks.maximilian.wadloader2.ddd2application.search.mapper.WadMapper;
import lincks.maximilian.wadloader2.ddd2application.game.Game;
import lincks.maximilian.wadloader2.ddd3domain.wads.Wad;

import javax.swing.*;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import static lincks.maximilian.wadloader2.ddd0plugins.ui.UIConstants.*;

public class WadsCheckBoxList {
    private WadsCheckBoxList(){}

    public static CheckboxList<WadDto> of(Game game, CheckboxList<IWadDto> iWads, IWadMapper iWadMapper, WadMapper wadMapper){
        return WadConfigFilterCheckBoxList.of(List.of(), WADS, Map.of(START_CONFIG, startGameHandler(game, iWads, iWadMapper, wadMapper)),true);
    }

    private static Consumer<List<WadDto>> startGameHandler(Game game, CheckboxList<IWadDto> iWadsCheckboxList, IWadMapper iWadMapper, WadMapper wadMapper) {
        return selectedWads -> {
            var selectedIWads = iWadsCheckboxList.getSelected();
            if (selectedIWads.isEmpty()){
                JOptionPane.showMessageDialog(null, NO_I_WAD_SELECTED);
                return;
            }
            else if (selectedIWads.size() > 1)
                //should never be reached because the corresponding CheckboxList should only allow single selection
                throw new TooManyIWadsException("Too many IWads selected!");
            game.start(
                    iWadMapper.fromDto(selectedIWads.get(0)),
                    selectedWads.stream()
                            .map(wadMapper::fromDto).toArray(wads -> new Wad[0]));
        };
    }
}
