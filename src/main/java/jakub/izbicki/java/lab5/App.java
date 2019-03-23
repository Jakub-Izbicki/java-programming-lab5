package jakub.izbicki.java.lab5;

import com.google.inject.Guice;
import com.google.inject.Inject;
import jakub.izbicki.java.lab5.commons.GuiceModule;
import jakub.izbicki.java.lab5.matrixdimensions.MatrixDimensionsController;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

    @Inject
    private MatrixDimensionsController matrixDimensionsController;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Guice.createInjector(new GuiceModule()).injectMembers(this);
        matrixDimensionsController.createStage().show();
    }
}
