package ru.spoddubnyak.searchTextInFile.one;

import ru.spoddubnyak.searchTextInFile.multi.ParallelSearchManyThreads;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * Class MySearchContent implement search by content.
 *
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 25.03.2018
 */
public class MySearchContent extends SimpleFileVisitor<Path> {
    /**
     * property textForSearch - text for search in file.
     */
    private String textForSearch;
    /**
     * property ps - link for class ParallelSearch.
     */
    private ParallelSearch ps;
    /**
     * property psm - link for class ParallelSearchManyThreads.
     */
    private ParallelSearchManyThreads psm;

    /**
     * Constructor it creates a new object MySearchContent with the specified.
     *
     * @param textForSearch - text for search
     * @param ps            - property ps
     */
    public MySearchContent(String textForSearch, ParallelSearch ps) {
        this.textForSearch = textForSearch;
        this.ps = ps;
        this.psm = null;
    }

    /**
     * Constructor it creates a new object MySearchContent with the specified.
     *
     * @param textForSearch - text for search
     * @param psm           - property psm
     */
    public MySearchContent(String textForSearch, ParallelSearchManyThreads psm) {
        this.textForSearch = textForSearch;
        this.ps = null;
        this.psm = psm;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        String content = new String(Files.readAllBytes(file), "UTF-8");
        if (textForSearch != null && content.contains(textForSearch)) {
            if (this.ps != null) {
                ps.addResultList(String.valueOf(file));
            } else if (this.psm != null) {
                psm.addResultList(String.valueOf(file));
            }
        }
        return FileVisitResult.TERMINATE;
    }
}