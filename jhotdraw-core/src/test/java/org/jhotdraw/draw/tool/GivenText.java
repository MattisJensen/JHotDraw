package org.jhotdraw.draw.tool;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import org.jhotdraw.draw.DefaultDrawingEditor;
import org.jhotdraw.draw.DrawingView;
import org.jhotdraw.draw.figure.TextFigure;

public class GivenText extends Stage<GivenText> {
    @ProvidedScenarioState
    DefaultDrawingEditor editor;
    @ProvidedScenarioState
    TextFigure textFigure;

    public GivenText there_is_existing_text_in_my_drawing() {
        // Initialize the editor and text figure with existing text
        editor = new DefaultDrawingEditor();
        textFigure = new TextFigure();
        textFigure.setText("Existing text");

        // Assuming you have a DrawingView to add the figure to
        DrawingView view = editor.getActiveView();
        if (view != null) {
            view.getDrawing().add(textFigure);
        }

        return self();
    }
}