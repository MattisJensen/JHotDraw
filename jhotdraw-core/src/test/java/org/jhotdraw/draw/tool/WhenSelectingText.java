package org.jhotdraw.draw.tool;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import org.jhotdraw.draw.DrawingEditor;
import org.jhotdraw.draw.figure.TextFigure;

import java.awt.event.MouseEvent;

public class WhenSelectingText extends Stage<WhenSelectingText> {
    @ProvidedScenarioState
    DrawingEditor editor;
    @ProvidedScenarioState
    TextFigure textFigure;
    @ProvidedScenarioState
    TextEditingTool textEditingTool;

    public WhenSelectingText i_select_the_text_tool_and_click_on_the_text() {
        // Simulate selecting the text tool and clicking on the text
        textEditingTool = new TextEditingTool(textFigure);
        editor.setTool(textEditingTool);

        // Create a MouseEvent with a valid Component
        MouseEvent clickEvent = new MouseEvent(new java.awt.Canvas(), MouseEvent.MOUSE_PRESSED, System.currentTimeMillis(), 0, 0, 0, 1, false);
        textEditingTool.mousePressed(clickEvent);
        return self();
    }
}