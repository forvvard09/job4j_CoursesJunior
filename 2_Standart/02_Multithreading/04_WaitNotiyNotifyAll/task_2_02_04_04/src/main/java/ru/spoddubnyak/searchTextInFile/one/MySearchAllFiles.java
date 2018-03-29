package ru.spoddubnyak.searchTextInFile.one;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

import static com.google.common.io.Files.getFileExtension;

/**
 * Class MySearchAllFiles implement search files.
 *
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 25.03.2018
 */
public class MySearchAllFiles extends SimpleFileVisitor<Path> {
    /**
     * property ps - link for class ParallelSearch.
     */
    private final ParallelSearch ps;

    /**
     * Constructor it creates a new object Threads with the specified.
     *
     * @param ps - property ps
     */
    public MySearchAllFiles(ParallelSearch ps) {
        this.ps = ps;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        return FileVisitResult.SKIP_SUBTREE;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
        String extension = getFileExtension(String.valueOf(file.getFileName()));
        for (String ext : this.ps.getExts()) {
            if (ext.equals(extension)) {
                ps.addToQueue(String.valueOf(file));
            }
        }
        return FileVisitResult.CONTINUE;
    }
}

