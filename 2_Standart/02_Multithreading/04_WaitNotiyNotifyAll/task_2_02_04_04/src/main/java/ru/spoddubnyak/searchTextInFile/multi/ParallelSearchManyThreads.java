package ru.spoddubnyak.searchTextInFile.multi;

import net.jcip.annotations.GuardedBy;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Class ParallelSearchManyThreads implement search files and search by context in many threads.
 *
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 27.03.2018
 */
public class ParallelSearchManyThreads {
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
     * property countSearchThreads - count threads for search files.
     */
    private final int countSearchThreads = Runtime.getRuntime().availableProcessors() / 2;
    /**
     * property countReadThreads - count threads for search by content.
     */
    private final int countReadThreads = Runtime.getRuntime().availableProcessors() - this.countSearchThreads;
    /**
     * property queueDir - for save path directory's for search files.
     */
    @GuardedBy("itself")
    private final Queue<Path> queueDir = new LinkedList<>();
    /**
     * property files - for save path files for search by content.
     */
    private final Queue<Path> files = new LinkedList<>();
    /**
     * property paths - for save result.
     */
    @GuardedBy("itself")
    private final List<String> paths = new ArrayList<>();
    /**
     * property threads - for run threads search and read.
     */
    private Threads threads;
    /**
     * property threadsSaerch - array for create threads search.
     */
    private Thread[] threadsSaerch;

    /**
     * Constructor it creates a new object ParallelSearchManyThreads with the specified.
     *
     * @param root - property root
     * @param text - property text
     * @param exts - property exts
     */
    public ParallelSearchManyThreads(String root, String text, List<String> exts) {
        this.root = root;
        this.text = text;
        this.exts = exts;
        this.threads = new Threads(this);
        this.threadsSaerch = new Thread[this.countSearchThreads];
    }

    /**
     * Gettter for property text, return property text.
     *
     * @return property text
     */
    public String getText() {
        return this.text;
    }

    /**
     * Gettter for property queueDir, return property queueDir.
     *
     * @return property queueDir
     */
    public Queue getQueueDir() {
        Queue result;
        synchronized (this.queueDir) {
            result = this.queueDir;
        }
        return result;
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
     * Gettter for property paths, return property paths.
     *
     * @return property paths
     */
    public List<String> getPaths() {
        List<String> result;
        synchronized (this.paths) {
            result = this.paths;
        }
        return result;
    }

    /**
     * Gettter for class ParallelSearchManyThreads, return this class.
     *
     * @return this class
     */
    public ParallelSearchManyThreads getParallelSearchMultiClass() {
        return this;
    }

    /**
     * Method is adding path to paths.
     *
     * @param path path for add
     */
    public void addResultList(String path) {
        synchronized (this.paths) {
            this.paths.add(path);
        }
    }

    /**
     * Method is adding path to queueDir.
     *
     * @param pathDir path for add
     */
    public void addToDirQueue(Path pathDir) {
        synchronized (this.queueDir) {
            this.queueDir.add(pathDir);
        }
    }

    /**
     * Method is polling from queueDir.
     *
     * @return path out queueDir
     */
    public Path pollFromDirQueue() {
        Path resultDir;
        synchronized (this.queueDir) {
            resultDir = this.queueDir.poll();
        }
        return resultDir;
    }

    /**
     * Method is polling from files.
     *
     * @return path out files
     */
    public Path pollFromFilesQueue() {
        Path resultDir;
        if (this.files.isEmpty()) {
            System.out.printf("%s.%s", "Queue is empty. Waiting", System.getProperty("line.separator"));
        }
        while (this.files.isEmpty()) {
            try {
                this.files.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        resultDir = this.files.poll();
        return resultDir;
    }

    /**
     * Method is adding path to files.
     *
     * @param pathDir path for files
     */
    public void addToFiles(Path pathDir) {
        this.files.add(pathDir);
    }

    /**
     * Method checked did the work of file search threads.
     *
     * @return true, if threads finished works, false if work
     */
    private boolean isWorkSearch() {
        int i = 0;
        for (Thread thread : this.threadsSaerch) {
            if (thread != null && thread.isAlive()) {
                i++;
            }
        }
        return i == 0;
    }

    /**
     * Method start threads by content.
     */
    private void startRead() {
        ExecutorService service = Executors.newFixedThreadPool(this.countReadThreads);
        while (!this.files.isEmpty() || !this.isWorkSearch()) {
            service.submit(this.threads.getReadThread());
        }
        service.shutdown();
    }

    /**
     * Method start thread Run, starting search run all.
     */
    public void init() {
        Thread run = new Thread(new Run());
        run.start();
    }

    /**
     * Method start threads by files.
     */
    private void startSearch() {
        for (int i = 0; i < this.threadsSaerch.length; i++) {
            this.threadsSaerch[i] = this.threads.getSearchThread();
            this.threadsSaerch[i].start();
        }
    }

    /**
     * Inner class run search files and search by context.
     */
    class Run implements Runnable {
        @Override
        public void run() {
            addToDirQueue(Paths.get(root));
            startSearch();
            startRead();
        }
    }
}
