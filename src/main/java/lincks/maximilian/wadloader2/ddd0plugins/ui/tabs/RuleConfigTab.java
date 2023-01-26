package lincks.maximilian.wadloader2.ddd0plugins.ui.tabs;

import lincks.maximilian.wadloader2.ddd0plugins.ui.utility.CheckboxList;
import lincks.maximilian.wadloader2.ddd2application.wadpack.rules.RuleFactory;
import lincks.maximilian.wadloader2.ddd3domain.rules.WadPackRule;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import static lincks.maximilian.wadloader2.ddd0plugins.ui.UIConstants.DELETE_RULES;
import static lincks.maximilian.wadloader2.ddd0plugins.ui.UIConstants.RULES;

@Component
public class RuleConfigTab extends JPanel implements WadLoader2Tab{

    private final RuleFactory ruleFactory;
    private final List<WadPackRule> rules;
    private final CheckboxList<WadPackRule> rulesCheckboxList;

    public RuleConfigTab(RuleFactory ruleFactory) {
        this.ruleFactory = ruleFactory;

        setLayout(new BorderLayout());

        rules = ruleFactory.allRules();
        rulesCheckboxList = new CheckboxList<>(rules,RULES, Map.of(DELETE_RULES, deleteRules()),true);


        add(rulesCheckboxList, BorderLayout.CENTER);
    }

    private Consumer<List<WadPackRule>> deleteRules(){
        return selectedRules -> selectedRules.forEach(rule -> {
            rulesCheckboxList.remove(rule);
            ruleFactory.deleteRule(rule);
        });
    }

    @Override
    public void updateData() {
        //do nothing because the UI doesn't depend on data mutated by other tabs
    }

}
