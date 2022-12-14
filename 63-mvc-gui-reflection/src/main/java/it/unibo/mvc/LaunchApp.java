package it.unibo.mvc;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import it.unibo.mvc.api.DrawNumberController;
import it.unibo.mvc.api.DrawNumberView;
import it.unibo.mvc.controller.DrawNumberControllerImpl;
import it.unibo.mvc.model.DrawNumberImpl;

/**
 * Application entry-point.
 */
public final class LaunchApp {

    private LaunchApp() { }

    /**
     * Runs the application.
     *
     * @param args ignored
     * @throws ClassNotFoundException if the fetches class does not exist
     * @throws NoSuchMethodException if the 0-ary constructor do not exist
     * @throws InvocationTargetException if the constructor throws exceptions
     * @throws InstantiationException if the constructor throws exceptions
     * @throws IllegalAccessException in case of reflection issues
     * @throws IllegalArgumentException in case of reflection issues
     */
     public static void main(final String... args) throws
                                    ClassNotFoundException,
                                    NoSuchMethodException,
                                    InvocationTargetException,
                                    InstantiationException,
                                    IllegalAccessException {
        final var model = new DrawNumberImpl();
        final DrawNumberController app = new DrawNumberControllerImpl(model);

        // very hard coded solution using reflection taken from the solutions
        for (final var viewType: List.of("Stdout", "Swing")) {
            // getting class instance
            final var clazz = Class.forName("it.unibo.mvc.view.DrawNumber" + viewType + "View");

            // creating 3 instances of the class just found
            for (int i = 0; i < 3; i++) {
                // creating a new instance
                final var newView = clazz.getConstructor().newInstance();

                // if the just created instance can be assigned to a DrawNumberView variable
                if (DrawNumberView.class.isAssignableFrom(newView.getClass())) {
                    // adding view to controller
                    app.addView((DrawNumberView) newView);
                } else {
                    // we mistakenly got the wrong class
                    throw new IllegalStateException(
                        newView.getClass() + " is not a subclass of " + DrawNumberView.class
                    );
                }
            }
        }
    }
}
