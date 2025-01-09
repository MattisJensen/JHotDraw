package org.jhotdraw.draw.handle;

import org.jhotdraw.draw.DrawingEditor;
import org.jhotdraw.draw.DrawingView;
import org.jhotdraw.draw.figure.TextHolderFigure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TextOverflowHandleTest {

    private TextOverflowHandle handle;
    private TextHolderFigure owner;
    private DrawingEditor editor;
    private DrawingView view;

    @BeforeEach
    void setUp() {
        owner = mock(TextHolderFigure.class);
        editor = mock(DrawingEditor.class);
        view = mock(DrawingView.class);
        handle = new TextOverflowHandle(owner);
        handle.setView(view);
        when(view.getEditor()).thenReturn(editor);
    }

    @Test
    void getOwner() {
        assertEquals(owner, handle.getOwner());
    }

    @Test
    void contains() {
        assertFalse(handle.contains(new Point(0, 0)));
    }

    @Test
    void draw() {
        Graphics2D g = mock(Graphics2D.class);
        when(owner.isTextOverflow()).thenReturn(true);
        when(editor.getHandleAttribute(HandleAttributeKeys.OVERFLOW_HANDLE_FILL_COLOR)).thenReturn(Color.RED);
        when(editor.getHandleAttribute(HandleAttributeKeys.OVERFLOW_HANDLE_STROKE_COLOR)).thenReturn(Color.BLACK);
        when(owner.getBounds()).thenReturn(new Rectangle2D.Double(0, 0, 10, 10));
        when(view.drawingToView(any(Point2D.Double.class))).thenReturn(new Point(10, 10));
        when(editor.getHandleAttribute(HandleAttributeKeys.HANDLE_SIZE)).thenReturn(5);

        handle.draw(g);

        verify(g, times(2)).setColor(Color.BLACK);
        verify(g, times(2)).drawLine(anyInt(), anyInt(), anyInt(), anyInt());
    }

    @Test
    void basicGetBounds() {
        Rectangle2D.Double bounds = new Rectangle2D.Double(0, 0, 10, 10);
        when(owner.getBounds()).thenReturn(bounds);
        when(view.drawingToView(any(Point2D.Double.class))).thenReturn(new Point(10, 10));
        when(editor.getHandleAttribute(HandleAttributeKeys.HANDLE_SIZE)).thenReturn(5);

        Rectangle result = handle.basicGetBounds();

        assertEquals(new Rectangle(5, 5, 5, 5), result);
    }

    @Test
    void getToolTipText() {
        when(owner.isTextOverflow()).thenReturn(true);
        assertNotNull(handle.getToolTipText(new Point(0, 0)));

        when(owner.isTextOverflow()).thenReturn(false);
        assertNull(handle.getToolTipText(new Point(0, 0)));
    }
}