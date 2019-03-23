package jakub.izbicki.java.lab5.matrix;

import jakub.izbicki.java.lab5.matrix.exception.MatrixException;
import lombok.Getter;

@Getter
public class MatrixImpl implements Matrix {

    private final int rows;

    private final int columns;

    private final int[][] values;

    public MatrixImpl(int[][] values) {
        this.values = values;
        this.rows = values.length;
        this.columns = values[0].length;
    }


    @Override
    public Matrix multipliedWith(Matrix matrix) throws MatrixException {
        validateMultiplication(matrix);
        return getMultiplied(matrix);
    }

    private void validateMultiplication(Matrix matrix) throws MatrixException {
        if (columns != matrix.getRows()) {
            throw new MatrixException("Matrix dimensions are incorrect for multiplication");
        }
    }

    private Matrix getMultiplied(Matrix matrix) {
        int[][] product = new int[rows][matrix.getColumns()];
        for (int row = 0; row < rows; row++) {
            for (int bColumn = 0; bColumn < matrix.getColumns(); bColumn++) {
                for (int aColumn = 0; aColumn < columns; aColumn++) {
                    product[row][bColumn] += values[row][aColumn] * matrix.getValues()[aColumn][bColumn];
                }
            }
        }
        return new MatrixImpl(product);
    }

    @Override
    public Matrix transposed() {
        int[][] values = new int[this.values[0].length][this.values.length];
        for (int i = 0; i < this.values.length; i++) {
            for (int j = 0; j < this.values[0].length; j++) {
                values[j][i] = this.values[i][j];
            }
        }

        return new MatrixImpl(values);
    }

    @Override
    public void print() {

    }
}
