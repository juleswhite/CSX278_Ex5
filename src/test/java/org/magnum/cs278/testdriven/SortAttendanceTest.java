package org.magnum.cs278.testdriven;

import org.joda.time.DateTime;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by Joseph on 9/20/2016.
 */
public class SortAttendanceTest {

    private App app = new App();
    // sortAttendance is descending order (largest first)

    @Test
    public void testLargestAttendance() throws Exception {
        List<Event> sortedList = app.sortAttendance();
        Event topEvent = sortedList.get(0);
        assertEquals((Integer.parseInt(topEvent.getAttendance())), 284000);
    }

    @Test
    public void testLeastAttendance() throws Exception {
        List<Event> sortedList = app.sortAttendance();
        Event leastEvent = sortedList.get(sortedList.size() - 1);
        assertEquals(Integer.parseInt(leastEvent.getAttendance()), 0);
    }

    @Test
    public void testIncremental() throws Exception {
        List<Event> sortedList = app.sortAttendance();
        int randomPos = (int) (Math.random() * sortedList.size()) - 2;
        Event largerEvent = sortedList.get(randomPos);
        Event smallerEvent = sortedList.get(randomPos + 1);
        assertTrue(Integer.parseInt(largerEvent.getAttendance()) >= Integer.parseInt(smallerEvent.getAttendance()));
    }

}
