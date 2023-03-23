package lincks.maximilian.wadloader2.ddd0plugins.ui.tabs.startwads.lists;

import lincks.maximilian.wadloader2.ddd0plugins.ui.tabs.startwads.exceptions.TooManyIWadsException;
import lincks.maximilian.wadloader2.ddd0plugins.ui.utility.CheckboxList;
import lincks.maximilian.wadloader2.ddd0plugins.ui.utility.WadConfigFilterCheckBoxList;
import lincks.maximilian.wadloader2.ddd2application.search.dto.IWadDto;
import lincks.maximilian.wadloader2.ddd2application.search.mapper.IWadMapper;
import lincks.maximilian.wadloader2.ddd2application.game.Game;

import javax.swing.*;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import static lincks.maximilian.wadloader2.ddd0plugins.ui.UIConstants.*;

public class IWadsCheckboxList {

    private IWadsCheckboxList(){}
    public static CheckboxList<IWadDto> of(Game game, IWadMapper mapper){
        return WadConfigFilterCheckBoxList.of(List.of(), I_WADS, Map.of( START_CONFIG, gameStartHandler(game, mapper)));
    }

    private static Consumer<List<IWadDto>> gameStartHandler(Game game, IWadMapper mapper) {
        return iwads -> {
            if (iwads.isEmpty())
                JOptionPane.showMessageDialog(null, NO_I_WAD_SELECTED);
            else if (iwads.size() > 1)
                //should never be reached because the corresponding CheckboxList should only allow single selection
                throw new TooManyIWadsException("Too many IWads selected!");
            game.start(mapper.fromDto(iwads.get(0)));
        };
    }
}
