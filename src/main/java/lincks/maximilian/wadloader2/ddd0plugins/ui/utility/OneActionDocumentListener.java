package lincks.maximilian.wadloader2.ddd0plugins.ui.utility;

import lombok.RequiredArgsConstructor;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.util.function.Consumer;

@RequiredArgsConstructor
public abstract class OneActionDocumentListener implements DocumentListener {

    public static DocumentListener of(Consumer<DocumentEvent> action) {
        return new OneActionDocumentListener() {
            @Override
            void action(DocumentEvent e) {
                action.accept(e);
            }
        };
    }

    abstract void action(DocumentEvent e);

    @Override
    public void insertUpdate(DocumentEvent e) {
        action(e);
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        action(e);
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        action(e);
    }
}
