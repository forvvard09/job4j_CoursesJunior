package ru.spoddubnyak.searchTextInFile.multi;

import ru.spoddubnyak.searchTextInFile.one.MySearchContent;

import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.EnumSet;

/**
 * Class Threads implement threads for search files and search content in files.
 *
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 28.03.2018
 */
public class Threads {

    /**
     * property psm - link for class ParallelSearchManyThreads.
     */
    private final ParallelSearchManyThreads psm;
    /**
     * property thread for search files.
     */
    private Thread search;
    /**
     * property thread for search by content.
     */
    private Thread read;

    /**
     * Constructor it creates a new object Threads with the specified.
     *
     * @param psm - property psm
     */
    public Threads(ParallelSearchManyThreads psm) {
        this.psm = psm;
        search = null;
        read = null;
    }

    /**
     * Gettter for property search, return search thread.
     *
     * @return property search
     */
    public Thread getSearchThread() {
        this.search = new SearchThread();
        return this.search;
    }

    /**
     * Gettter for property thread, return read thread.
     *
     * @return property search
     */
    public Runnable getReadThread() {
        this.read = new Thread(new ReadThread());
        return this.read;
    }

    /**
     * Inner class SearchThread implement thread for search files.
     */
    class SearchThread extends Thread {
        @Override
        public void run() {
            while (!psm.getQueueDir().isEmpty()) {
                Path path = psm.pollFromDirQueue();
                try {
                    Files.walkFileTree(path, EnumSet.of(FileVisitOption.FOLLOW_LINKS), 1, new MySearchAllDirFiles(psm.getParallelSearchMultiClass()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Inner class ReadThread implement thread for search content.
     */
    class ReadThread implements Runnable {
        @Override
        public void run() {
            Path path = psm.pollFromFilesQueue();
            try {
                Files.walkFileTree(path, new MySearchContent(psm.getText(), psm.getParallelSearchMultiClass()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}