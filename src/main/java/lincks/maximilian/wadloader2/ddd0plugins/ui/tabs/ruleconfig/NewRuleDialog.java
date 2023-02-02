package lincks.maximilian.wadloader2.ddd0plugins.ui.tabs.ruleconfig;

import lincks.maximilian.wadloader2.ddd1adapter.query.TagQuery;
import lincks.maximilian.wadloader2.ddd3domain.rules.WadPackRule;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

import static lincks.maximilian.wadloader2.ddd0plugins.ui.UIConstants.CREATE_NEW_RULE;

public class NewRuleDialog extends JDialog {

    private final CompletableFuture<WadPackRule> ruleFuture;
    private RulePanel rulePanel;

    private NewRuleDialog(TagQuery tagQuery) {
        setTitle(CREATE_NEW_RULE);
        setLayout(new BorderLayout());
        setModal(true);
        ruleFuture = new CompletableFuture<>();
        JButton createRuleBtn = new JButton(CREATE_NEW_RULE);
        JPanel optionsPanelWrapper = new JPanel();
        JComboBox<RuleType> ruleTypeBox = new JComboBox<>(RuleType.values());

        //TODO Maybe use selection Listener instead
        // show the right panel depending on selection
        Consumer<ActionEvent> handler = e -> {
            RuleType typeOfNewRule = (RuleType) ruleTypeBox.getSelectedItem();
            optionsPanelWrapper.removeAll();
            rulePanel = switch (typeOfNewRule) {
                case MinTagRule, null ->
                        new NewAmountTagRulePanel(NewAmountTagRulePanel.Type.minTag, tagQuery.findAllInRepos());
                case MaxTagRule ->
                                new NewAmountTagRulePanel(NewAmountTagRulePanel.Type.maxTag, tagQuery.findAllInRepos());
                case ExclusiveTagRule ->
                        new NewExclusiveRulePanel(
                                NewExclusiveRulePanel.Type.exclusiveTags,
                                tagQuery.findAllInRepos(),
                                tagQuery.findAllInRepos());
                case ExclusiveWadRule -> new NewExclusiveRulePanel(
                        NewExclusiveRulePanel.Type.exclusiveWad,
                        tagQuery.findAllInWadTagRepo(),
                        tagQuery.findAllInRepos());
            };
            optionsPanelWrapper.add(rulePanel);
            pack();
        };
        ruleTypeBox.addActionListener(handler::accept);

        add(ruleTypeBox, BorderLayout.NORTH);
        add(optionsPanelWrapper);
        add(createRuleBtn, BorderLayout.SOUTH);

        ruleTypeBox.setSelectedIndex(0);

        createRuleBtn.addActionListener(e -> {
            rulePanel.getRule()
                    .ifPresent(ruleFuture::complete);
            dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                ruleFuture.cancel(true);
            }
        });

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static CompletableFuture<WadPackRule> of(TagQuery tagQuery) {
        return new NewRuleDialog(tagQuery).ruleFuture;
    }

    private enum RuleType {
        MinTagRule,
        MaxTagRule,
        ExclusiveTagRule,
        ExclusiveWadRule
    }
}
