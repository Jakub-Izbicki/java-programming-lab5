package jakub.izbicki.java.lab5.matrix;

import jakub.izbicki.java.lab5.matrix.exception.MatrixException;

public interface Matrix {

    Matrix multipliedWith(Matrix matrix) throws MatrixException;

    Matrix transposed();

    void print();

    int[][] getValues();

    int getRows();

    int getColumns();
}
