import org.junit.Test;
import ru.spoddubnyak.searchTextInFile.one.ParallelSearch;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class ParallelSearchManyThreadsTest for testing class ParallelSearch search files in tree directory and search by context.
 *
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 2.0
 * @since 22.03.2018
 */
public class ParallelSearchTest {

    /**
     * Test methods of class ParallelSearch implement search files by context.
     */
    @Test
    public void whenSearchFilesThenExpectedResult() {
        final int expectedResult = 5;
        List<String> ext = new ArrayList<>();
        ext.add("txt");
        ext.add("csv");
        ParallelSearch search = new ParallelSearch("D:\\searchFile", "testing", ext);
        search.init();
        List<String> result = search.getPaths();
        assertThat(expectedResult, is(search.getPaths().size()));
    }
}
