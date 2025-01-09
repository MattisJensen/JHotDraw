package org.jhotdraw.draw.action;

import com.tngtech.jgiven.junit.ScenarioTest;
import org.junit.Test;

public class SelectionColorChooserHandlerJGivenTest extends ScenarioTest<GivenFigureAndColor, WhenApplyColor, ThenFigureShouldChangeColor> {
    @Test
    public void a_figure_can_change_color_to_a_specific_color() {
        given().a_figure_and_a_desired_color();
        when().the_color_is_applied();
        then().the_figure_should_change_to_the_chosen_color();
    }
}
