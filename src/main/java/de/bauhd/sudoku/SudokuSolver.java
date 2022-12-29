package de.bauhd.sudoku;

import java.nio.file.Path;

public final class SudokuSolver {

    private static final SudokuFileHandler FILE_HANDLER = new SudokuFileHandler();

    public static void main(String[] args) {
        final var solver = new SudokuSolver(Path.of(System.getProperty("sudoku.file", "sudoku.txt")));
        solver.solve();
        FILE_HANDLER.write(Path.of(System.getProperty("sudoku.solved", "solved_sudoku.txt")), solver.grid);
    }

    private final byte[][] grid;

    SudokuSolver(final Path path) {
        this(FILE_HANDLER.read(path));
    }

    SudokuSolver(final byte[][] grid) {
        this.grid = grid;
        this.solve();
    }

    public boolean solve() {
        for (byte y = 0; y < 9; y++) {
            for (byte x = 0; x < 9; x++) {
                if (this.grid[y][x] == 0) {
                    for (byte i = 1; i < 10; i++) {
                        if (this.isPossible(y, x, i)) {
                            this.grid[y][x] = i;
                            if (this.solve()) {
                                return true;
                            }
                            this.grid[y][x] = 0;
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isPossible(final byte y, final byte x, final byte i) {
        for (var j = 0; j < 9; j++) {
            if (this.grid[y][j] == i) {
                return false;
            }
            if (this.grid[j][x] == i) {
                return false;
            }
        }
        final var x0 = Math.floorDiv(x, 3) * 3;
        final var y0 = Math.floorDiv(y, 3) * 3;
        for (var j = 0; j < 3; j++) {
            for (var k = 0; k < 3; k++) {
                if (this.grid[y0 + j][x0 + k] == i) {
                    return false;
                }
            }
        }
        return true;
    }

    public byte[][] grid() {
        return this.grid;
    }
}
