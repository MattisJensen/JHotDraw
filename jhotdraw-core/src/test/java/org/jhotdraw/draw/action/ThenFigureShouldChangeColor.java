package org.jhotdraw.draw.action;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import org.jhotdraw.draw.AttributeKeys;
import org.jhotdraw.draw.figure.Figure;

import java.awt.*;

import static org.junit.Assert.*;

public class ThenFigureShouldChangeColor extends Stage<ThenFigureShouldChangeColor> {
    @ProvidedScenarioState
    Figure figure;
    @ProvidedScenarioState
    Color desiredColor;

    public ThenFigureShouldChangeColor the_figure_should_change_to_the_chosen_color() {
        assertEquals(desiredColor, figure.get(AttributeKeys.FILL_COLOR));
        return self();
    }
}