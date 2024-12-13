/**
 * @(#)SelectionColorChooserHandler.java
 *
 * Copyright (c) 2010 The authors and contributors of JHotDraw.
 * You may not use, copy or modify this file, except in compliance with the
 * accompanying license terms.
 */
package org.jhotdraw.draw.action;

import org.jhotdraw.draw.figure.Figure;
import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.undo.*;
import org.jhotdraw.draw.*;

/**
 * SelectionColorChooserHandler.
 *
 * @author Werner Randelshofer
 * @version $Id$
 */
public class SelectionColorChooserHandler extends AbstractSelectedAction
        implements ChangeListener {

    private static final long serialVersionUID = 1L;
    protected AttributeKey<Color> key;
    protected JColorChooser colorChooser;
    protected JPopupMenu popupMenu;
    protected boolean isUpdating = false;
    private UndoableEdit lastEdit;

    /**
     * Creates a new instance.
     */
    public SelectionColorChooserHandler(DrawingEditor editor, AttributeKey<Color> key, JColorChooser colorChooser, JPopupMenu popupMenu) {
        super(editor);
        this.key = key;
        this.colorChooser = colorChooser;
        this.popupMenu = popupMenu;
        colorChooser.getSelectionModel().addChangeListener(this);
        updateEnabledState();
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent evt) {
        popupMenu.setVisible(false);
    }

    protected void applySelectedColorToFigures() {
        final ArrayList<Figure> selectedFigures = new ArrayList<>(getView().getSelectedFigures());
        final ArrayList<Object> restoreData = new ArrayList<>(selectedFigures.size());
        Color selectedColor = colorChooser.getColor();
        if (selectedColor != null && selectedColor.getAlpha() == 0) {
            selectedColor = null;
        }
        for (Figure figure : selectedFigures) {
            restoreData.add(figure.getAttributesRestoreData());
            figure.willChange();
            figure.set(key, selectedColor);
            figure.changed();
        }
        getEditor().setDefaultAttribute(key, selectedColor);
        setLastEdit(selectedFigures, restoreData, selectedColor);

        fireUndoableEditHappened(lastEdit);
    }

    private void setLastEdit(ArrayList<Figure> selectedFigures, ArrayList<Object> restoreData, Color undoValue) {
        lastEdit = new AbstractUndoableEdit() {
            private static final long serialVersionUID = 1L;

            @Override
            public String getPresentationName() {
                return AttributeKeys.FONT_FACE.getPresentationName();
            }

            @Override
            public void undo() {
                super.undo();
                Iterator<Object> iRestore = restoreData.iterator();
                for (Figure figure : selectedFigures) {
                    figure.willChange();
                    figure.restoreAttributesTo(iRestore.next());
                    figure.changed();
                }
            }

            @Override
            public void redo() {
                super.redo();
                for (Figure figure : selectedFigures) {
                    figure.willChange();
                    figure.set(key, undoValue);
                    figure.changed();
                }
            }
        };
    }

    @Override
    protected void updateEnabledState() {
        setEnabled(getEditor().isEnabled());
        if (getView() != null && colorChooser != null && popupMenu != null) {
            colorChooser.setEnabled(getView().getSelectionCount() > 0);
            popupMenu.setEnabled(getView().getSelectionCount() > 0);
            isUpdating = true;
            if (getView().getSelectionCount() > 0) {
                for (Figure f : getView().getSelectedFigures()) {
                    Color figureColor = f.get(key);
                    colorChooser.setColor(figureColor == null ? new Color(0, true) : figureColor);
                    break;
                }
            }
            isUpdating = false;
        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if (!isUpdating) {
            applySelectedColorToFigures();
        }
        isUpdating = false;
    }
}
