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

public class NewExclusiveRulePanel extends RulePanel{

    private final CheckboxList<Tag> firstSet;
    private final CheckboxList<Tag> secondSet;

    public NewExclusiveRulePanel(Type type, List<Tag> availableTags1, List<Tag> availableTags2){
        setLayout(new BorderLayout());
        JPanel innerPanel = new JPanel(new GridLayout(0,2));
        JLabel ruleExplanation = new JLabel("Soem Text");
        firstSet = new CheckboxList<>(availableTags1,"name", Map.of(),type.multiAllowed);
        secondSet = new CheckboxList<>(availableTags2,"name", Map.of(), true);

        innerPanel.add(new JScrollPane(firstSet),new JScrollPane(secondSet));

        add(ruleExplanation, BorderLayout.NORTH);
        add(innerPanel, BorderLayout.CENTER);
    }

    //TODO
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
