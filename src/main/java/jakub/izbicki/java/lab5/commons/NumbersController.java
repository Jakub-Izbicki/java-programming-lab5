package jakub.izbicki.java.lab5.commons;

import jakub.izbicki.java.lab5.matrix.MatrixDimensions;
import jakub.izbicki.java.lab5.numberfiled.NumberTextField;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.util.List;
import java.util.StringJoiner;

public abstract class NumbersController extends Controller {

    protected void doIfNoNumberErrors(Button button, List<NumberTextField> numberTextFields, Text errorTextField,
                                      Runnable runnable) {
        button.setDisable(false);
        List<String> errors = getErrors(numberTextFields);
        if (errors.isEmpty()) {
            runnable.run();
        } else {
            showErrors(errors, errorTextField);
            button.setDisable(false);
        }
    }

    private List<String> getErrors(List<NumberTextField> numberTextFields) {
        return MatrixNumbersValidator.validate(numberTextFields);
    }

    private void showErrors(List<String> errors, Text errorTextField) {
        StringJoiner joiner = new StringJoiner("\n");
        errors.forEach(joiner::add);

        errorTextField.setText(joiner.toString());
    }

}
