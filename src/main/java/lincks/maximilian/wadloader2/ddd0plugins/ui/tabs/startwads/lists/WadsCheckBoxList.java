package lincks.maximilian.wadloader2.ddd0plugins.ui.tabs.startwads.lists;

import lincks.maximilian.wadloader2.ddd0plugins.ui.tabs.startwads.exceptions.TooManyIWadsException;
import lincks.maximilian.wadloader2.ddd0plugins.ui.utility.CheckboxList;
import lincks.maximilian.wadloader2.ddd0plugins.ui.utility.WadConfigFilterCheckBoxList;
import lincks.maximilian.wadloader2.ddd2application.game.Game;
import lincks.maximilian.wadloader2.ddd3domain.wads.IWad;
import lincks.maximilian.wadloader2.ddd3domain.wads.Wad;

import javax.swing.*;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import static lincks.maximilian.wadloader2.ddd0plugins.ui.UIConstants.*;

public class WadsCheckBoxList {
    private WadsCheckBoxList(){}

    public static CheckboxList<Wad> of(Game game, CheckboxList<IWad> iWads){
        return WadConfigFilterCheckBoxList.of(List.of(), WADS, Map.of(START_CONFIG, startGameHandler(game, iWads)),true);
    }

    private static Consumer<List<Wad>> startGameHandler(Game game, CheckboxList<IWad> iWadsCheckboxList) {
        return selectedWads -> {
            var selectedIWads = iWadsCheckboxList.getSelected();
            if (selectedIWads.isEmpty()){
                JOptionPane.showMessageDialog(null, NO_I_WAD_SELECTED);
                return;
            }
            else if (selectedIWads.size() > 1)
                //should never be reached because the corresponding CheckboxList should only allow single selection
                throw new TooManyIWadsException("Too many IWads selected!");
            game.start(selectedIWads.get(0), selectedWads.toArray(new Wad[0]));
        };
    }
}
