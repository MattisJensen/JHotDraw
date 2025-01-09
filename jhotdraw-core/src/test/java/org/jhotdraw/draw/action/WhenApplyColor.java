package org.jhotdraw.draw.action;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;

public class WhenApplyColor extends Stage<WhenApplyColor> {
    @ProvidedScenarioState
    SelectionColorChooserHandler handler;

    public WhenApplyColor the_color_is_applied() {
        handler.applySelectedColorToFigures();
        return self();
    }
}