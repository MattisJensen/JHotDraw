// WhenStage.java
package org.jhotdraw.app.action.file;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;

import java.awt.event.ActionEvent;

public class WhenStage extends Stage<WhenStage> {
    @ProvidedScenarioState
    OpenFileAction openFileAction;

    public WhenStage the_file_is_opened() {
        openFileAction.actionPerformed(new ActionEvent("Test Source", ActionEvent.ACTION_PERFORMED, null));
        return this;
    }
}