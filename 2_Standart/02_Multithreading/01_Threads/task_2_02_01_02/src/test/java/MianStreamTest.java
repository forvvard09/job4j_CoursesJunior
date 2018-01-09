import org.junit.Test;
import ru.spoddubnyak.MainStream;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Tests class MianStream.
 *
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 09.01.2018
 */
public class MianStreamTest {
    /**
     * property -  expected result.
     */
    private String expectedResponse;
    /**
     * property -  start time.
     */
    private long startTime;
    /**
     * property -  duration work.
     */
    private int durationWork;
    /**
     * property -  timer for testing.
     */
    private int timerForTesting;

    /**
     * Method generation tests data for run tetst.
     *
     * @param startTime       - field by specification.
     * @param durationWork    - field by specification.
     * @param timerForTesting - field by specification.
     */
    private void generationTestData(final long startTime, final int durationWork, final int timerForTesting) {
        this.startTime = startTime;
        this.durationWork = durationWork;
        this.timerForTesting = timerForTesting;
    }

    /**
     * Test get count symbols in string.
     */
    @Test
    public void whenRuntimeLessThenSpecifirltimeTimeGetCountNumber() {
        final int duration = 999;
        final int timer = 333;
        generationTestData(System.currentTimeMillis(), duration, timer);
        expectedResponse = String.format("%s", "Count symbols = 34.");
        MainStream app = new MainStream(startTime, durationWork, "1 22 333 4444 55555 666666 7777777", timerForTesting);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Thread testThread = null;
        try {
            testThread = app.initMainStrim();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            testThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertThat(out.toString(), is(expectedResponse));
    }

    /**
     * Test get message completion of the flow time for counting time.
     */
    @Test
    public void wheTimeCompleteMoreThanSetTimenGetShowMessageFinishTime() {
        final int duration = 100;
        final int timer = 999;
        generationTestData(System.currentTimeMillis(), duration, timer);
        expectedResponse = String.format("%s", "Finish time.");
        MainStream app = new MainStream(startTime, durationWork, "1 22 333 4444 55555 666666 7777777", timerForTesting);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Thread testThread = null;
        try {
            testThread = app.initMainStrim();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            testThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertThat(out.toString(), is(expectedResponse));
    }
}
