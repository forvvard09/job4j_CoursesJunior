package ru.spoddubnyak.searchTextInFile.multi;


import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

import static com.google.common.io.Files.getFileExtension;

/**
 * Class MySearchAllDirFiles implement search directory, files.
 *
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 27.03.2018
 */
public class MySearchAllDirFiles extends SimpleFileVisitor<Path> {
    /**
     * property psm - link for class ParallelSearchManyThreads.
     */
    private final ParallelSearchManyThreads psm;

    /**
     * Constructor it creates a new object MySearchAllDirFiles with the specified.
     *
     * @param psm - property psm
     */
    public MySearchAllDirFiles(ParallelSearchManyThreads psm) {
        this.psm = psm;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        return FileVisitResult.SKIP_SUBTREE;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (Files.isDirectory(file, LinkOption.NOFOLLOW_LINKS)) {
            psm.addToDirQueue(file);
        } else if (Files.isRegularFile(file, LinkOption.NOFOLLOW_LINKS)) {
            String extension = getFileExtension(String.valueOf(file.getFileName()));
            for (String ext : this.psm.getExts()) {
                if (ext.equals(extension)) {
                    psm.addToFiles(file);
                }
            }
        }
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        return FileVisitResult.CONTINUE;
    }
}