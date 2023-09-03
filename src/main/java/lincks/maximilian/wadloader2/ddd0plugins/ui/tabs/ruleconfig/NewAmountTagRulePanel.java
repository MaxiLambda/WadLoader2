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
        tagJComboBox.setToolTipText("Which Tag is the rule about?");
        triggerTagJComboBox = new JComboBox<>(availableTags.toArray(new Tag[0]));
        triggerTagJComboBox.setToolTipText("Which Tag should trigger the rule?");

        add(ruleExplanation);
        add(amountSpinner);
        add(tagJComboBox);
        add(triggerTagJComboBox);
    }

    @Override
    public Optional<WadPackRule> getRule() {
        Optional<Tag> ruleTag = Optional.ofNullable(tagJComboBox.getSelectedItem()).map(Tag.class::cast);
        Optional<Tag> triggerTag = Optional.ofNullable(triggerTagJComboBox.getSelectedItem()).map(Tag.class::cast);

        if (ruleTag.isEmpty() || triggerTag.isEmpty())
            return Optional.empty();
        else if(type.equals(Type.maxTag))
            return Optional.of(new ContainsMaxTagRule((int) amountSpinner.getValue(), ruleTag.get(), triggerTag.get()));
        else if(type.equals(Type.minTag))
            return Optional.of(new ContainsMinTagRule((int) amountSpinner.getValue(), ruleTag.get(), triggerTag.get()));

        return Optional.empty();
    }

    @RequiredArgsConstructor
    public enum Type {
        maxTag("most"),
        minTag("least");

        public final String quantifier;
    }


}
