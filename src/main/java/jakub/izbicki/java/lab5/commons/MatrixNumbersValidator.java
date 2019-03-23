package jakub.izbicki.java.lab5.commons;

import jakub.izbicki.java.lab5.numberfiled.NumberTextField;
import javafx.scene.control.TextInputControl;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class MatrixNumbersValidator {

    public static List<String> validate(NumberTextField... numberTextFields) {
        return validate(Arrays.asList(numberTextFields));
    }

    public static List<String> validate(List<NumberTextField> numberTextFields) {
        try {
            numberTextFields.stream()
                    .map(TextInputControl::getText)
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        } catch (NumberFormatException e) {
            return Collections.singletonList("All fields have to be provided with number values!");
        }

        return Collections.emptyList();
    }
}
