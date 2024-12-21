// GivenStage.java
package org.jhotdraw.app.action.file;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import org.jhotdraw.api.app.Application;
import org.jhotdraw.api.app.ApplicationModel;
import org.jhotdraw.api.app.View;
import org.jhotdraw.api.gui.URIChooser;
import org.mockito.Mockito;

import java.awt.*;
import java.net.URI;
import java.util.concurrent.CountDownLatch;

import static org.mockito.Mockito.mock;

public class GivenStage extends Stage<GivenStage> {
    @ProvidedScenarioState
    Application app;
    @ProvidedScenarioState
    View view;
    @ProvidedScenarioState
    URIChooser uriChooser;
    @ProvidedScenarioState
    OpenFileAction openFileAction;
    @ProvidedScenarioState
    URI fileUri;
    @ProvidedScenarioState
    CountDownLatch latch;

    public GivenStage a_view() {
        view = mock(View.class);
        Mockito.when(view.isEmpty()).thenReturn(true);
        Mockito.when(view.isEnabled()).thenReturn(true);
        return this;
    }

    public GivenStage a_uri_chooser() {
        uriChooser = mock(URIChooser.class);
        Mockito.when(uriChooser.getSelectedURI()).thenReturn(fileUri);
        return this;
    }

    public GivenStage a_file_of_supported_format(String format) {
        fileUri = URI.create("src/test/java/org/jhotdraw/app/action/file/testFiles/test." + format);
        Mockito.when(uriChooser.getSelectedURI()).thenReturn(fileUri);
        return this;
    }

    public GivenStage an_open_file_action() {
        latch = new CountDownLatch(1);
        openFileAction = new OpenFileAction(app) {
            @Override
            protected void openViewFromURI(View view, URI uri, URIChooser chooser) {
                super.openViewFromURI(view, uri, chooser);
                latch.countDown(); // Signal that the SwingWorker has completed
            }

            @Override
            public int showDialog(URIChooser chooser, Component parent) {
                return 0; // 0 means the user approved of a selected file, in the custom file chooser dialog.
            }
        };
        return this;
    }

    public GivenStage an_application() {
        app = mock(Application.class);
        Mockito.when(app.isEnabled()).thenReturn(true);

        Mockito.when(app.getOpenChooser(null)).thenReturn(uriChooser);

        ApplicationModel model = mock(ApplicationModel.class);
        Mockito.when(app.getModel()).thenReturn(model);
        Mockito.when(model.isAllowMultipleViewsPerURI()).thenReturn(false);

        Mockito.when(app.getActiveView()).thenReturn(view);
        return this;
    }
}