package jakub.izbicki.java.lab5.commons;

import jakub.izbicki.java.lab5.App;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public abstract class Controller implements Initializable {

    public static final String WINDOW_WIDTH_SMALL_KEY = "window.small.width";

    public static final String WINDOW_HEIGHT_SMALL_KEY = "window.small.height";

    private static final String DEFAULT_BUNDLE = "bundles.messages";

    private static final String STAGE_TITLE = "App";

    private static final String DEFAULT_CSS_FILE_PATH = "/styles/general.css";

    private static final String COULD_NOT_LOAD_FXML = "Could not load FXML file from location: ";

    protected Stage stage;

    private Parent fxmlRoot;

    public Stage createStage() {
        Properties prop = new Properties();
        return createStage(
                Double.parseDouble(prop.get(WINDOW_WIDTH_SMALL_KEY)),
                Double.parseDouble(prop.get(WINDOW_HEIGHT_SMALL_KEY)));
    }

    public void showLater(double windowWidth, double windowHeight) {
        Platform.runLater(() -> {
            createStage(windowWidth, windowHeight).show();
        });
    }

    protected void closeLater() {
        Platform.runLater(() -> {
            stage.close();
        });
    }

    private Stage createStage(double windowWidth, double windowHeight) {
        final Parent root = loadFxml();
        final Scene scene =
                isRootAlreadyRegisteredInStage(root) ? root.getScene() : new Scene(root, windowWidth, windowHeight);
        scene.getStylesheets().add(App.class.getResource(DEFAULT_CSS_FILE_PATH).toExternalForm());

        final Stage stage = new Stage();
        stage.setTitle(STAGE_TITLE);
        stage.setScene(scene);
        stage.sizeToScene();
        this.stage = stage;

        return stage;
    }

    private synchronized Parent loadFxml() {
        if (fxmlRoot == null) {
            final FXMLLoader loader = createFxmlLoader();
            try {
                fxmlRoot = loader.load();
            } catch (IOException e) {
                throw new IllegalStateException(COULD_NOT_LOAD_FXML + loader.getLocation(), e);
            }
        }
        return fxmlRoot;
    }

    private FXMLLoader createFxmlLoader() {
        final FXMLLoader loader = new FXMLLoader(getFxmlResourceUrl(), getFxmlResourceBundle());
        loader.setController(this);
        return loader;
    }

    private URL getFxmlResourceUrl() {
        return getClass().getResource(FxmlResolver.from(this.getClass()));
    }

    private ResourceBundle getFxmlResourceBundle() {
        return ResourceBundle.getBundle(DEFAULT_BUNDLE);
    }

    private boolean isRootAlreadyRegisteredInStage(Parent root) {
        return root.getScene() != null;
    }
}
