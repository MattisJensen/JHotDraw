package org.jhotdraw.app.action.file;

import org.jhotdraw.api.app.Application;
import org.jhotdraw.api.app.ApplicationModel;
import org.jhotdraw.app.AbstractApplicationModel;
import org.jhotdraw.app.DefaultApplicationModel;
import org.jhotdraw.app.SDIApplication;

import java.awt.event.ActionEvent;
import java.util.Random;

import static org.junit.Assert.*;

public class OpenFileActionTest {
    Application app;
    OpenFileAction openFileAction;
    //final String[] viewClasses = {"org.jhotdraw.samples.draw.DrawView", "org.jhotdraw.samples.net.NetView", "org.jhotdraw.samples.pert.PertView", "org.jhotdraw.samples.svg.SVGView", "org.jhotdraw.samples.teddy.TeddyView"};

    @org.junit.Before
    public void setUp() throws Exception {
        /*
        Create an Application object, pass it to the constructor of an OpenFileAction, to instantiate
        an object of that.
         */
//        app = new SDIApplication(); // Example app, as an SDIApplication. Possibly expand on this later.
//        AbstractApplicationModel model = new DefaultApplicationModel();
//        int rnd = new Random().nextInt(viewClasses.length);
//        model.setViewClassName(viewClasses[rnd]);
//        app.setModel(model);
//        openFileAction = new OpenFileAction(app);

    }

    @org.junit.After
    public void tearDown() throws Exception {
        /*
        Assign null objects to Object variables.
         */
    }

//    @org.junit.Test
//    public void actionPerformed() {
//        System.out.println(app.getViews());
//        assertTrue(app.getViews() == null || app.getViews().isEmpty()); // Replace with exception throw. Not the purpose of this test.
//        openFileAction.actionPerformed(new ActionEvent("Test Source", ActionEvent.ACTION_PERFORMED, null, 0L, 0));
//        assertEquals(1, app.getViews().size()); // If the app is enabled, and the user does not close/cancel
//        // the FileChooser window, a new view should be crated on the Application object passed to OpenFileAction's constructor.
//        // TODO: Automate/mock the user's interaction with the JFileChooser window.
//    }

    @org.junit.Test
    public void testOpenViewFromURI() {

    }

    @org.junit.Test
    public void testHandleFileChoice() {

    }

    @org.junit.Test
    public void showDialog() {
    }
}