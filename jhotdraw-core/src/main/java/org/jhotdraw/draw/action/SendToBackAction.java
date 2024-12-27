/*
 * @(#)SendToBackAction.java
 *
 * Copyright (c) 2003-2008 The authors and contributors of JHotDraw.
 * You may not use, copy or modify this file, except in compliance with the
 * accompanying license terms.
 */
package org.jhotdraw.draw.action;

import org.jhotdraw.draw.figure.Figure;
import java.util.*;
import javax.swing.*;
import javax.swing.undo.*;
import org.jhotdraw.draw.*;
import org.jhotdraw.util.ResourceBundleUtil;

/**
 * SendToBackAction.
 *
 * @author Werner Randelshofer
 * @version $Id$
 */
// Updated SendToBackAction
public class SendToBackAction extends AbstractSelectedAction {
    private static final long serialVersionUID = 1L;
    public static final String ID = "edit.sendToBack";
    private final ArrangeService arrangeService;

    public SendToBackAction(DrawingEditor editor, ArrangeService arrangeService) {
        super(editor);
        this.arrangeService = arrangeService;
        ResourceBundle labels = ResourceBundle.getBundle("org.jhotdraw.draw.Labels");
        putValue(Action.NAME, labels.getString(ID));
        updateEnabledState();
    }



    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        final DrawingView view = getView();
        final LinkedList<Figure> figures = new LinkedList<>(view.getSelectedFigures());
        arrangeService.sendToBack(view, figures);
        fireUndoableEditHappened(arrangeService.createUndoableEdit(ID,
                () -> arrangeService.sendToBack(view, figures),
                () -> arrangeService.bringToFront(view, figures)));
    }
}

