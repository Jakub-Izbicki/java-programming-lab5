package jakub.izbicki.java.lab5.matrixdimensions;

import com.google.common.collect.ImmutableList;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import jakub.izbicki.java.lab5.commons.NumbersController;
import jakub.izbicki.java.lab5.commons.Properties;
import jakub.izbicki.java.lab5.matrix.Matrix;
import jakub.izbicki.java.lab5.matrix.MatrixDimensions;
import jakub.izbicki.java.lab5.matrixvalues.MatrixValuesController;
import jakub.izbicki.java.lab5.numberfiled.NumberTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Singleton
public class MatrixDimensionsController extends NumbersController {

    @FXML
    private Button matrixDimensionsReadyButton;

    @FXML
    private NumberTextField matrixAdimensionsX;

    @FXML
    private NumberTextField matrixAdimensionsY;

    @FXML
    private NumberTextField matrixBdimensionsX;

    @FXML
    private NumberTextField matrixBdimensionsY;

    @FXML
    private Text errorInfo;

    private MatrixValuesController matrixValuesController;

    @Inject
    public MatrixDimensionsController(MatrixValuesController matrixValuesController) {
        this.matrixValuesController = matrixValuesController;
    }

    @FXML
    void onMatrixDimensionsReadyButtonPress() {
        doIfNoNumberErrors(matrixDimensionsReadyButton,
                ImmutableList.of(matrixAdimensionsX, matrixAdimensionsY, matrixBdimensionsX, matrixBdimensionsY),
                errorInfo,
                () -> {
                    MatrixDimensions dimensions = new MatrixDimensions(matrixAdimensionsX, matrixAdimensionsY,
                            matrixBdimensionsX, matrixBdimensionsY);
                    openMatrixValuesWindow(dimensions);
                });
    }

    private void openMatrixValuesWindow(MatrixDimensions dimensions) {
        closeLater();
        matrixValuesController.showLater(dimensions);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
