// ThenStage.java
package org.jhotdraw.app.action.file;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import org.jhotdraw.api.app.View;

import java.net.URI;
import java.util.concurrent.CountDownLatch;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

public class ThenStage extends Stage<ThenStage> {
    @ProvidedScenarioState
    View view;
    @ProvidedScenarioState
    CountDownLatch latch;
    @ProvidedScenarioState
    URI fileUri;

    public ThenStage a_new_canvas_is_created_with_file_contents() {
        try {
            latch.await(); // Wait for the SwingWorker to complete
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        verify(view).setURI(fileUri);
        try {
            verify(view).read(any(), any());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }
}