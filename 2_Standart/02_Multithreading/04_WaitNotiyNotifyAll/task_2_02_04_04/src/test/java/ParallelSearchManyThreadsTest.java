import org.junit.Test;
import ru.spoddubnyak.searchTextInFile.multi.ParallelSearchManyThreads;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class ParallelSearchManyThreadsTest for testing class ParallelSearchManyThreads search files in tree directory and search by context.
 *
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 2.0
 * @since 27.03.2018
 */
public class ParallelSearchManyThreadsTest {

    /**
     * Test methods of class ParallelSearchManyThreads implement search files by context.
     */
    @Test
    public void whenSearchFilesThenExpectedResult() {
        final int expectedResult = 5;
        List<String> ext = new ArrayList<>();
        ext.add("txt");
        ext.add("csv");
        ParallelSearchManyThreads search = new ParallelSearchManyThreads("D:\\searchFile", "testing", ext);
        search.init();
        final int delay = 999;
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertThat(expectedResult, is(search.getPaths().size()));
    }
}

