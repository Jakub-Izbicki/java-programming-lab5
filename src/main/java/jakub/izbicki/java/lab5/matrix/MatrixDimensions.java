package jakub.izbicki.java.lab5.matrix;

import jakub.izbicki.java.lab5.numberfiled.NumberTextField;
import lombok.Getter;

@Getter
public class MatrixDimensions {

    private int aRows;

    private int aColumns;

    private int bRows;

    private int bColumns;

    public MatrixDimensions(NumberTextField aRows, NumberTextField aColumns, NumberTextField bRows,
                            NumberTextField bColumns) {
        this.aRows = aRows.getInt();
        this.aColumns = aColumns.getInt();
        this.bRows = bRows.getInt();
        this.bColumns = bColumns.getInt();
    }
}
