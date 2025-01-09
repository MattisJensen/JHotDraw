package org.jhotdraw.draw.tool;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import org.jhotdraw.draw.figure.TextHolderFigure;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ThenTextEditable extends Stage<ThenTextEditable> {
    @ProvidedScenarioState
    TextHolderFigure textHolderFigure;
    @ProvidedScenarioState
    TextEditingTool textEditingTool;

    public ThenTextEditable the_text_should_become_editable() {
        // Verify that the text is now editable
        assertTrue(textEditingTool.isEditing());
        return self();
    }

    public ThenTextEditable i_should_be_able_to_modify_the_text_content() {
        // Modify the text content and verify the change
        textEditingTool.endEdit();
        textHolderFigure.setText("Modified text");
        assertTrue(textHolderFigure.getText().equals("Modified text"));
        return self();
    }
}