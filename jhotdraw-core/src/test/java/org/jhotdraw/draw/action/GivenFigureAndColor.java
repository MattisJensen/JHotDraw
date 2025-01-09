package org.jhotdraw.draw.action;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import org.jhotdraw.draw.AttributeKeys;
import org.jhotdraw.draw.Drawing;
import org.jhotdraw.draw.DrawingEditor;
import org.jhotdraw.draw.DrawingView;
import org.jhotdraw.draw.figure.Figure;
import org.jhotdraw.draw.figure.RectangleFigure;
import org.mockito.Mockito;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.mock;

public class GivenFigureAndColor extends Stage<GivenFigureAndColor> {
    @ProvidedScenarioState
    DrawingEditor editor;
    @ProvidedScenarioState
    DrawingView view;
    @ProvidedScenarioState
    Drawing drawing;
    @ProvidedScenarioState
    JColorChooser colorChooser;
    @ProvidedScenarioState
    JPopupMenu popupMenu;
    @ProvidedScenarioState
    SelectionColorChooserHandler handler;
    @ProvidedScenarioState
    Figure figure;
    @ProvidedScenarioState
    Color desiredColor;

    public GivenFigureAndColor a_figure_and_a_desired_color() {
        editor = mock(DrawingEditor.class);
        view = mock(DrawingView.class);
        drawing = mock(Drawing.class);
        colorChooser = new JColorChooser();
        popupMenu = new JPopupMenu();
        handler = new SelectionColorChooserHandler(editor, AttributeKeys.FILL_COLOR, colorChooser, popupMenu);

        Mockito.when(editor.getActiveView()).thenReturn(view);
        Mockito.when(view.getDrawing()).thenReturn(drawing);

        figure = new RectangleFigure();
        Set<Figure> selectedFigures = new HashSet<>();
        selectedFigures.add(figure);

        Mockito.when(view.getSelectedFigures()).thenReturn(selectedFigures);

        desiredColor = Color.RED;
        colorChooser.setColor(desiredColor);

        return self();
    }
}
