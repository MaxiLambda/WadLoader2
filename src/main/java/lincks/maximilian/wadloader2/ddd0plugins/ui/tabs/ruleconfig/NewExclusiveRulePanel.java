package lincks.maximilian.wadloader2.ddd0plugins.ui.tabs.ruleconfig;

import lincks.maximilian.wadloader2.ddd0plugins.ui.utility.CheckboxList;
import lincks.maximilian.wadloader2.ddd3domain.rules.ExclusiveTagRule;
import lincks.maximilian.wadloader2.ddd3domain.rules.WadPackRule;
import lincks.maximilian.wadloader2.ddd3domain.tags.Tag;
import lombok.RequiredArgsConstructor;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static lincks.maximilian.wadloader2.ddd0plugins.ui.UIConstants.*;

public class NewExclusiveRulePanel extends RulePanel{

    private final CheckboxList<Tag> firstSet;
    private final CheckboxList<Tag> secondSet;

    public NewExclusiveRulePanel(Type type, List<Tag> availableTags1, List<Tag> availableTags2){
        setLayout(new BorderLayout());
        JPanel innerPanel = new JPanel();
        innerPanel.setLayout(new GridLayout(0,2));
        JLabel ruleExplanation = new JLabel(NEW_EXCLUSIVE_RULE_LABEL);

        firstSet = new CheckboxList<>(availableTags1,FIRST_SET, Map.of(),type.multiAllowed);
        secondSet = new CheckboxList<>(availableTags2,SECOND_SET, Map.of(), true);

        add(ruleExplanation, BorderLayout.NORTH);
        add(innerPanel, BorderLayout.CENTER);
        innerPanel.add(firstSet);
        innerPanel.add(secondSet);
    }

    @Override
    Optional<WadPackRule> getRule() {
        if(firstSet.getSelected().isEmpty() || secondSet.getSelected().isEmpty()) return Optional.empty();
        return Optional.of(new ExclusiveTagRule(firstSet.getSelected(), secondSet.getSelected()));
    }

    @RequiredArgsConstructor
    public enum Type {
        exclusiveWad(false),
        exclusiveTags(true);

    public final boolean multiAllowed;
    }
}
