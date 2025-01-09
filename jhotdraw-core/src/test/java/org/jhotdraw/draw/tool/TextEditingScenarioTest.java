package org.jhotdraw.draw.tool;

import com.tngtech.jgiven.junit5.ScenarioTest;
import org.junit.jupiter.api.Test;

public class TextEditingScenarioTest extends ScenarioTest<GivenText, WhenSelectingText, ThenTextEditable> {

    @Test
    public void text_should_become_editable_and_modifiable() {
        given().there_is_existing_text_in_my_drawing();
        when().i_select_the_text_tool_and_click_on_the_text();
        then().the_text_should_become_editable()
                .and().i_should_be_able_to_modify_the_text_content();
    }
}