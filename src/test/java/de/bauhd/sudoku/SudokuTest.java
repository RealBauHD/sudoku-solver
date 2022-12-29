package de.bauhd.sudoku;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public final class SudokuTest {

    @Test
    public void solve() {
        final var solver = new SudokuSolver(new byte[][]{
                new byte[]{2, 4, 0, 0, 0, 0, 1, 0, 9},
                new byte[]{0, 1, 0, 0, 8, 0, 0, 0, 0},
                new byte[]{7, 0, 0, 0, 0, 2, 0, 5, 3},
                new byte[]{0, 3, 9, 0, 4, 0, 0, 0, 0},
                new byte[]{0, 2, 7, 1, 0, 9, 4, 3, 6},
                new byte[]{0, 6, 5, 2, 3, 8, 7, 0, 1},
                new byte[]{3, 0, 0, 0, 0, 0, 5, 0, 0},
                new byte[]{0, 0, 0, 8, 7, 0, 0, 1, 4},
                new byte[]{0, 7, 0, 5, 0, 1, 0, 0, 0}
        });
        solver.solve();
        Assertions.assertTrue(Arrays.deepEquals(solver.grid(), new byte[][]{
                new byte[]{2, 4, 8, 3, 6, 5, 1, 7, 9},
                new byte[]{5, 1, 3, 9, 8, 7, 6, 4, 2},
                new byte[]{7, 9, 6, 4, 1, 2, 8, 5, 3},
                new byte[]{1, 3, 9, 7, 4, 6, 2, 8, 5},
                new byte[]{8, 2, 7, 1, 5, 9, 4, 3, 6},
                new byte[]{4, 6, 5, 2, 3, 8, 7, 9, 1},
                new byte[]{3, 8, 1, 6, 9, 4, 5, 2, 7},
                new byte[]{6, 5, 2, 8, 7, 3, 9, 1, 4},
                new byte[]{9, 7, 4, 5, 2, 1, 3, 6, 8}
        }));
    }

}
