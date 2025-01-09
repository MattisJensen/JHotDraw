package org.jhotdraw.draw.action;

import org.jhotdraw.draw.*;
import org.jhotdraw.draw.figure.Figure;
import org.jhotdraw.draw.figure.RectangleFigure;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class SelectionColorChooserHandlerTest {
    private DrawingEditor editor;
    private DrawingView view;
    private Drawing drawing;
    private JColorChooser colorChooser;
    private JPopupMenu popupMenu;
    private SelectionColorChooserHandler handler;

    @Before
    public void setup() {
        editor = mock(DrawingEditor.class);
        view = mock(DrawingView.class);
        drawing = mock(Drawing.class);
        colorChooser = new JColorChooser();
        popupMenu = new JPopupMenu();
        handler = new SelectionColorChooserHandler(editor, AttributeKeys.FILL_COLOR, colorChooser, popupMenu);

        when(editor.getActiveView()).thenReturn(view);
        when(view.getDrawing()).thenReturn(drawing);
    }

    @Test
    public void testApplySelectedColorToFigures() {
        // Arrange
        Figure figure = new RectangleFigure();
        Set<Figure> selectedFigures = new HashSet<>();
        selectedFigures.add(figure);

        when(view.getSelectedFigures()).thenReturn(selectedFigures);

        Color newColor = Color.RED;
        colorChooser.setColor(newColor);

        // Act
        handler.applySelectedColorToFigures();

        // Assert
        assertEquals(newColor, figure.get(AttributeKeys.FILL_COLOR));
    }
}