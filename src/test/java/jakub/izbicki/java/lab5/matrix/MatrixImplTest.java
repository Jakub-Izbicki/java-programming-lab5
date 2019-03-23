package jakub.izbicki.java.lab5.matrix;


import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MatrixImplTest {

    private Matrix a;

    private int[][] aValues = {
            {1, 2, 3, 4},
            {1, 2, 3, 4},
            {1, 2, 3, 4}
    };

    private Matrix b;

    private int[][] bValues = {
            {1, 2, 3},
            {1, 2, 3},
            {1, 2, 3},
            {1, 2, 3}
    };

    private int[][] multiplyResult = {
            {10, 20, 30},
            {10, 20, 30},
            {10, 20, 30}
    };

    private int[][] transposeResult = {
            {1, 1, 1},
            {2, 2, 2},
            {3, 3, 3},
            {4, 4, 4}
    };

    @Before
    public void setUp() {
        a = new MatrixImpl(aValues);
        b = new MatrixImpl(bValues);
    }

    @Test
    public void should_multiply_matrix_when_correct_values() {
        Matrix multiplied = a.multipliedWith(b);

        assertThat(multiplied)
                .extracting(Matrix::getRows, Matrix::getColumns, Matrix::getValues)
                .containsExactly(a.getRows(), b.getColumns(), this.multiplyResult);
    }

    @Test
    public void should_transpose_matrix_when_correct_values() {
        Matrix transposed = a.transposed();

        assertThat(transposed.getValues()).isEqualTo(this.transposeResult);
    }
}