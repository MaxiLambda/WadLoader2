package lincks.maximilian.wadloader2.ddd0plugins.ui.tabs.ruleconfig;

import lincks.maximilian.wadloader2.ddd1adapter.query.TagQuery;
import lincks.maximilian.wadloader2.ddd3domain.rules.WadPackRule;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import static lincks.maximilian.wadloader2.ddd0plugins.ui.UIConstants.CREATE_NEW_RULE;

public class NewRuleDialog extends JDialog {

    private final CompletableFuture<WadPackRule> ruleFuture;
    private Optional<RulePanel> rulePanel = Optional.empty();

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
        ruleTypeBox.addActionListener(e -> {
            RuleType typeOfNewRule = (RuleType) ruleTypeBox.getSelectedItem();
            optionsPanelWrapper.removeAll();
            rulePanel = switch (typeOfNewRule) {
                case null -> Optional.empty();
                case MinTagRule ->
                        Optional.of(new NewAmountTagRulePanel(NewAmountTagRulePanel.Type.minTag, tagQuery.findAllNotUniqueRepos()));
                case MaxTagRule ->
                        Optional.of(new NewAmountTagRulePanel(NewAmountTagRulePanel.Type.maxTag, tagQuery.findAllUniqueRepos()));
                //TODO
                case ExclusiveTagRule -> Optional.empty();
                case ExclusiveWadRule -> Optional.empty();
            };
            rulePanel.ifPresent(optionsPanelWrapper::add);
            pack();

        });

        add(ruleTypeBox, BorderLayout.NORTH);
        add(optionsPanelWrapper);
        add(createRuleBtn, BorderLayout.SOUTH);

        createRuleBtn.addActionListener(e -> {
            rulePanel
                    .flatMap(RulePanel::getRule)
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
        setSize(300, 150);
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
