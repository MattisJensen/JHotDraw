package org.jhotdraw.app.action.file;

import org.jhotdraw.api.app.Application;
import org.jhotdraw.api.app.View;
import org.jhotdraw.api.gui.URIChooser;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class OpenFileActionTest {
    Application app;
    OpenFileAction openFileAction;
    View view;
    URIChooser uriChooser;

    @org.junit.After
    public void tearDown() throws Exception {
    }


    @Before
    public void setUp() {
        app = mock(Application.class);
        view = mock(View.class);
        uriChooser = mock(URIChooser.class);
        openFileAction = new OpenFileAction(app);
    }

    // Test the two regular cases, for findOrCreateEmptyView():
    @Test
    public void testFindOrCreateEmptyView_NonEmptyView() {
        when(app.getActiveView()).thenReturn(view);
        assertEquals(null, OpenFileAction.findOrCreateEmptyView(app));
    }

    @Test
    public void testFindOrCreateEmptyView_EmptyView() {
        when(app.getActiveView()).thenReturn(null);
        assertEquals(null, OpenFileAction.findOrCreateEmptyView(app));
    }

    // Test a standard case for setMultipleOpenId():
    @Test
    public void testSetMultipleOpenId() {
        View[] otherViews = new View[]{mock(View.class), mock(View.class), mock(View.class), mock(View.class), mock(View.class)};

        for (int i = 0; i < otherViews.length; i++) {
            when(otherViews[i].isEmpty()).thenReturn(true);
            when(otherViews[i].getMultipleOpenId()).thenReturn(i);
        }

        when(app.views()).thenReturn(Arrays.asList(otherViews));

        OpenFileAction.setMultipleOpenId(view, app);

        verify(view).setMultipleOpenId(otherViews.length);
    }

    // Test non-standard cases for setMultipleOpenId():
    @Test
    public void testSetMultipleOpenId_highIds() {
        View otherview = mock(View.class);
        when(otherview.isEmpty()).thenReturn(true);
        when(otherview.getMultipleOpenId()).thenReturn(Integer.MAX_VALUE);

        View[] otherViews = new View[]{otherview};

        when(app.views()).thenReturn(Arrays.asList(otherViews));

        OpenFileAction.setMultipleOpenId(view, app);

        verify(view).setMultipleOpenId(otherViews.length);
    }

    @Test
    public void testSetMultipleOpenId_noViews() {
        View[] otherViews = new View[]{};

        when(app.views()).thenReturn(Arrays.asList(otherViews));

        OpenFileAction.setMultipleOpenId(view, app);

        verify(view).setMultipleOpenId(1);
    }

    @Test
    public void testSetMultipleOpenId_sameView() {
        View[] otherViews = new View[]{view};

        when(app.views()).thenReturn(Arrays.asList(otherViews));

        OpenFileAction.setMultipleOpenId(view, app);

        verify(view).setMultipleOpenId(1);
    }
}