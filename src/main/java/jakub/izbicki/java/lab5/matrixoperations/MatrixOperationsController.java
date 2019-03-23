package jakub.izbicki.java.lab5.matrixoperations;

import jakub.izbicki.java.lab5.commons.Controller;
import jakub.izbicki.java.lab5.commons.Properties;
import jakub.izbicki.java.lab5.matrix.Matrix;
import jakub.izbicki.java.lab5.matrix.exception.MatrixException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import org.apache.commons.lang.StringUtils;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.TimeoutException;

public class MatrixOperationsController extends Controller {

    private static final String WINDOW_WIDTH_BIG_KEY = "window.big.width";

    private static final String WINDOW_HEIGHT_BIG_KEY = "window.big.height";
    public static final String MATRIX_MULTIPLY_ERROR = "Cannot multiply provided matrices!";

    private final Properties prop = new Properties();

    private Matrix matrixA;

    private Matrix matrixB;

    private Matrix matrixMultiplied;

    @FXML
    private Button transposeAbutton;

    @FXML
    private Button transposeBbutton;

    @FXML
    private Button multiplyButton;

    @FXML
    private GridPane matrixAGridPane;

    @FXML
    private GridPane matrixBGridPane;

    @FXML
    private GridPane matrixMultiplyGridPane;

    @FXML
    private Text errorInfo;

    @FXML
    public void onMultiplyButtonPress() {
        clearErrors();

        Matrix matrix;
        try {
            matrix = matrixA.multipliedWith(matrixB);
        } catch (MatrixException e) {
            onMultiplicationError();
            return;
        }

        matrixMultiplied = matrix;
        populateMatrixMultipliedFields();
    }

    @FXML
    public void onTransposeAbuttonPress() {
        clearErrors();

        matrixA = matrixA.transposed();
        populateMatrixAFields();
    }

    @FXML
    public void onTransposeBbuttonPress() {
        clearErrors();

        matrixB = matrixB.transposed();
        populateMatrixBFields();
    }

    public void showLater(Matrix matrixA, Matrix matrixB) {
        this.matrixA = matrixA;
        this.matrixB = matrixB;
        showLater(Double.parseDouble(prop.get(WINDOW_WIDTH_BIG_KEY)),
                Double.parseDouble(prop.get(WINDOW_HEIGHT_BIG_KEY)));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        populateMatrixAFields();
        populateMatrixBFields();
    }

    private void populateMatrixMultipliedFields() {
        populateMatrixFields(matrixMultiplied, matrixMultiplyGridPane);
    }

    private void populateMatrixAFields() {
        populateMatrixFields(matrixA, matrixAGridPane);
    }

    private void populateMatrixBFields() {
        populateMatrixFields(matrixB, matrixBGridPane);
    }

    private void populateMatrixFields(Matrix matrix, GridPane gridPane) {
        gridPane.getChildren().removeAll(gridPane.getChildren());

        for (int i = 0; i < matrix.getRows(); i++) {
            for (int j = 0; j < matrix.getColumns(); j++) {
                Text text = new Text();
                text.setText(String.valueOf(matrix.getValues()[i][j]));
                gridPane.add(text, j, i, 1, 1);
            }
        }
    }

    private void onMultiplicationError() {
        errorInfo.setText(MATRIX_MULTIPLY_ERROR);
    }

    private void clearErrors() {
        errorInfo.setText(StringUtils.EMPTY);
    }
}
