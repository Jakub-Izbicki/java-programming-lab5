package jakub.izbicki.java.lab5.matrixvalues;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import jakub.izbicki.java.lab5.commons.NumbersController;
import jakub.izbicki.java.lab5.commons.Properties;
import jakub.izbicki.java.lab5.matrix.Matrix;
import jakub.izbicki.java.lab5.matrix.MatrixDimensions;
import jakub.izbicki.java.lab5.matrix.MatrixImpl;
import jakub.izbicki.java.lab5.matrixoperations.MatrixOperationsController;
import jakub.izbicki.java.lab5.numberfiled.NumberTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Singleton
public class MatrixValuesController extends NumbersController {

    private static final int FIELD_SIZE_MULTIPLIER = 50;

    private static final String WINDOW_WIDTH_MEDIUM_KEY = "window.medium.width";

    private final Properties prop = new Properties();

    private MatrixOperationsController matrixOperationsController;

    @FXML
    private Button matrixValuesReadyButton;

    @FXML
    private Text errorInfo;

    @FXML
    private GridPane valuesAGridPane;

    @FXML
    private GridPane valuesBGridPane;

    private MatrixDimensions dimensions;

    private NumberTextField[][] matrixAvalues;

    private NumberTextField[][] matrixBvalues;

    @Inject
    public MatrixValuesController(MatrixOperationsController matrixOperationsController) {
        this.matrixOperationsController = matrixOperationsController;
    }

    @FXML
    void onMatrixValuesReadyButton() {
        List<NumberTextField> textFields = Stream.concat(
                Arrays.stream(matrixAvalues)
                        .flatMap(Arrays::stream),
                Arrays.stream(matrixBvalues)
                        .flatMap(Arrays::stream))
                .collect(Collectors.toList());

        List<NumberTextField> fields = new ArrayList<>();
        doIfNoNumberErrors(matrixValuesReadyButton, textFields, errorInfo, this::openOperationWindow);
    }

    private void openOperationWindow() {
        Matrix matrixA = getMatrix(matrixAvalues);
        Matrix matrixB = getMatrix(matrixBvalues);

        closeLater();
        matrixOperationsController.showLater(matrixA, matrixB);
    }

    private Matrix getMatrix(NumberTextField[][] textFields) {
        int[][] values = Stream.of(textFields)
                .map(fields -> Stream.of(fields).mapToInt(NumberTextField::getInt).toArray())
                .toArray(int[][]::new);

        return new MatrixImpl(values);
    }

    public void showLater(MatrixDimensions dimensions) {
        this.dimensions = dimensions;

        double calcWidth = (dimensions.getAColumns() + dimensions.getBColumns()) * FIELD_SIZE_MULTIPLIER;
        double calcHeight = (dimensions.getARows() + dimensions.getBRows()) * FIELD_SIZE_MULTIPLIER;

        double width = Math.max(calcWidth, Double.parseDouble(prop.get(WINDOW_WIDTH_MEDIUM_KEY)));
        double height = Math.max(calcHeight, Double.parseDouble(prop.get(WINDOW_HEIGHT_SMALL_KEY)));
        showLater(width, height);
    }

    private NumberTextField[][] createMatrixValuesFields(int rows, int columns, GridPane gridPane, String name) {
        Text text = new Text(name);
        gridPane.add(text, columns / 2, 0);

        NumberTextField[][] fields = new NumberTextField[columns][rows];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                NumberTextField field = new NumberTextField();
                gridPane.add(field, j, i, 1, 1);

                fields[j][i] = field;
            }
        }
        return fields;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.matrixAvalues = createMatrixValuesFields(dimensions.getARows(), dimensions.getAColumns(), valuesAGridPane, "A");
        this.matrixBvalues = createMatrixValuesFields(dimensions.getBRows(), dimensions.getBColumns(), valuesBGridPane, "B");
    }
}
