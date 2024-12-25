package org.jhotdraw.draw.action;

import org.jhotdraw.draw.Drawing;
import org.jhotdraw.draw.DrawingView;
import org.jhotdraw.draw.figure.Figure;

import javax.swing.undo.AbstractUndoableEdit;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.ResourceBundle;

/**
 * ArrangeService encapsulates logic for arranging figures in the drawing.
 */
public class ArrangeService {

    public void bringToFront(DrawingView view, Collection<Figure> figures) {
        Drawing drawing = view.getDrawing();
        for (Figure figure : drawing.sort(figures)) {
            drawing.bringToFront(figure);
        }
    }

    public void sendToBack(DrawingView view, Collection<Figure> figures) {
        Drawing drawing = view.getDrawing();
        for (Figure figure : figures) { // Ensure proper sorting if needed
            drawing.sendToBack(figure);
        }
    }

    public AbstractUndoableEdit createUndoableEdit(String actionName, Runnable redoAction, Runnable undoAction) {
        return new AbstractUndoableEdit() {
            private static final long serialVersionUID = 1L;

            @Override
            public String getPresentationName() {
                ResourceBundle labels = ResourceBundle.getBundle("org.jhotdraw.draw.Labels");
                return labels.getString(actionName);
            }

            @Override
            public void redo() throws CannotRedoException {
                super.redo();
                redoAction.run();
            }

            @Override
            public void undo() throws CannotUndoException {
                super.undo();
                undoAction.run();
            }
        };
    }
}
