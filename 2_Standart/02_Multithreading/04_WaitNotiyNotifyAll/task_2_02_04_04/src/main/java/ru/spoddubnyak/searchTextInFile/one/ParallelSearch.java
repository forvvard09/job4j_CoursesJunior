package ru.spoddubnyak.searchTextInFile.one;


import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Class ParallelSearchManyThreads implement search files and search by context in two threads.
 *
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 22.03.2018
 */
@ThreadSafe
public class ParallelSearch {
    /**
     * property root - start path for search files.
     */
    private final String root;
    /**
     * property text - text for search by context.
     */
    private final String text;
    /**
     * property exts - extension for files for search files.
     */
    private final List<String> exts;
    /**
     * property files - for save path files for search by content.
     */
    @GuardedBy("itself")
    private final Queue<String> files = new LinkedList<>();
    /**
     * property paths - for save result.
     */
    @GuardedBy("itself")
    private final List<String> paths = new ArrayList<>();
    /**
     * property for finish search files.
     */
    private volatile boolean finish;

    /**
     * Constructor it creates a new object ParallelSearchManyThreads with the specified.
     *
     * @param root - property root
     * @param text - property text
     * @param exts - property exts
     */
    public ParallelSearch(String root, String text, List<String> exts) {
        this.root = root;
        this.text = text;
        this.exts = exts;
        this.finish = false;
    }

    /**
     * Gettter for property paths, return property paths.
     *
     * @return property paths
     */
    public List<String> getPaths() {
        return this.paths;
    }

    /**
     * Gettter for class ParallelSearch, return this class.
     *
     * @return this class
     */
    public ParallelSearch getParallelSearchClass() {
        return this;
    }

    /**
     * Gettter for property finish, return property finish.
     *
     * @return property finish
     */
    public boolean isFinish() {
        return this.finish;
    }

    /**
     * Gettter for class result, return property paths.
     *
     * @return property paths
     */
    public List<String> result() {
        return this.paths;
    }

    /**
     * Gettter for property exts, return property exts.
     *
     * @return property exts
     */
    public List<String> getExts() {
        return this.exts;
    }

    /**
     * Method is adding path to files.
     *
     * @param path path for files
     */
    public void addToQueue(String path) {
        synchronized (this.files) {
            this.files.add(path);
            this.files.notify();
        }
    }

    /**
     * Method is adding path to path.
     *
     * @param path path for files
     */
    public void addResultList(String path) {
        synchronized (this.result()) {
            this.result().add(path);
        }
    }

    /**
     * Method is polling from file.
     *
     * @return path out file
     */
    private String pollQueue() {
        String result;
        synchronized (this.files) {
            while (this.files.isEmpty()) {
                try {
                    System.out.printf("%s.%s", "Queue is empty. Waiting", System.getProperty("line.separator"));
                    this.files.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            result = this.files.poll();
        }
        return result;
    }

    /**
     * Method create thread for search files and create thread for search by context and start this threads.
     */
    public void init() {

        Thread search = new Thread() {
            @Override
            public void run() {
                Path path = Paths.get(root);
                while (!finish) {
                    try {
                        Files.walkFileTree(path, new MySearchAllFiles(getParallelSearchClass()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    finish = true;
                }
            }
        };

        Thread read = new Thread() {
            @Override
            public void run() {
                boolean end = isFinish();
                while (!end || !files.isEmpty()) {
                    Path path = Paths.get(pollQueue());
                    try {
                        Files.walkFileTree(path, new MySearchContent(text, getParallelSearchClass()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    end = isFinish();
                    final int timeout = 100;
                    if (files.isEmpty()) {
                        try {
                            Thread.sleep(timeout);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        };
        read.start();
        search.start();
        try {
            read.join();
            search.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}