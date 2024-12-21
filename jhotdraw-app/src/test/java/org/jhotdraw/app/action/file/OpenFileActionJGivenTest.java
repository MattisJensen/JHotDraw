// OpenFileActionJGivenTest.java
package org.jhotdraw.app.action.file;

import com.tngtech.jgiven.junit.ScenarioTest;
import org.junit.Test;

public class OpenFileActionJGivenTest extends ScenarioTest<GivenStage, WhenStage, ThenStage> {

    @Test
    public void testOpenFileAction_with_supported_file_format() {
        given().a_view()
                .and().a_uri_chooser()
                .and().a_file_of_supported_format("png")
                .and().an_application()
                .and().an_open_file_action();

        when().the_file_is_opened();

        then().a_new_canvas_is_created_with_file_contents();
    }
}