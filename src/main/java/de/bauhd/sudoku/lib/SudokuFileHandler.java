package de.bauhd.sudoku.lib;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public final class SudokuFileHandler {

    /**
     * @param path the path of a file that contains a sudoku
     * @return the grid of the sudoku
     */
    public byte[][] read(final Path path) {
        final var chars = Arrays.asList('1', '2', '3', '4', '5', '6', '7', '8', '9');
        final var bytes = new byte[9][9];
        var x = (byte) 0;
        var y = (byte) 0;
        try (final var reader = Files.newBufferedReader(path)) {
            String line;
            while ((line = reader.readLine()) != null) {
                x = 0;
                for (final var c : line.toCharArray()) {
                    if (c == ' ') continue;
                    if (chars.contains(c)) {
                        final var b = (byte) Character.digit(c, 10);
                        bytes[y][x] = b;
                    }
                    x++;
                }
                y++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return bytes;
    }

    public void write(final Path path, final byte[][] grid) {
        try (final var writer = Files.newBufferedWriter(path)) {
            for (final var row : grid) {
                for (final var b : row) {
                    writer.write(b + " ");
                }
                writer.write("\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
