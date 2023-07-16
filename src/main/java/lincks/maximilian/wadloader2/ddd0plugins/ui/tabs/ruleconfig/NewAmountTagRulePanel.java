package lincks.maximilian.wadloader2.ddd0plugins.ui.tabs.ruleconfig;

import lincks.maximilian.wadloader2.ddd3domain.rules.ContainsMaxTagRule;
import lincks.maximilian.wadloader2.ddd3domain.rules.ContainsMinTagRule;
import lincks.maximilian.wadloader2.ddd3domain.rules.WadPackRule;
import lincks.maximilian.wadloader2.ddd3domain.tags.Tag;
import lombok.RequiredArgsConstructor;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Optional;

import static lincks.maximilian.wadloader2.ddd0plugins.ui.UIConstants.AMOUNT_TAG_RULE_CREATION_TEXT;

public class NewAmountTagRulePanel extends RulePanel {

    private final Type type;
    private final JSpinner amountSpinner;
    private final JComboBox<Tag> tagJComboBox;

    private final JComboBox<Tag> triggerTagJComboBox;

    public NewAmountTagRulePanel(Type type, List<Tag> availableTags) {
        setLayout(new GridLayout(0, 1));

        this.type = type;

        JLabel ruleExplanation = new JLabel(AMOUNT_TAG_RULE_CREATION_TEXT.formatted(type.quantifier));
        amountSpinner = new JSpinner(new SpinnerNumberModel(1, 0, Integer.MAX_VALUE, 1));
        tagJComboBox = new JComboBox<>(availableTags.toArray(new Tag[0]));
        triggerTagJComboBox = new JComboBox<>(availableTags.toArray(new Tag[0]));

        add(ruleExplanation);
        add(amountSpinner);
        add(tagJComboBox);
        add(triggerTagJComboBox);
    }

    @Override
    public Optional<WadPackRule> getRule() {
        return Optional.ofNullable(tagJComboBox.getSelectedItem())
                .map(Tag.class::cast)
                .flatMap(tag ->
                        Optional.ofNullable(tagJComboBox.getSelectedItem())
                                .map(Tag.class::cast)
                                .map(triggerTag ->
                                        switch (type) {
                                            case maxTag -> new ContainsMaxTagRule((int) amountSpinner.getValue(), tag, triggerTag);
                                            case minTag -> new ContainsMinTagRule((int) amountSpinner.getValue(), tag, triggerTag);
                                        }
                                ));
    }

    @RequiredArgsConstructor
    public enum Type {
        maxTag("most"),
        minTag("least");

        public final String quantifier;
    }


}
