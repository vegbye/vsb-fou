package vsb.kladd.autoboxing;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Counter implements Iterable<Integer> {

    private int m_start = 0;
    private int m_step = 1;
    private int m_end = 0;

    private Counter(int start, int step, int end) {
        m_start = start;
        m_step = step;
        m_end = end;

        if (step == 0) {
            throw new IllegalArgumentException("step == 0 in loop");
        }
    }

    public static Counter upTo(int n) {
        return new Counter(0, 1, n);
    }

    public static Counter fromTo(int fromAndWith, int toNotIncluding) {
        return new Counter(fromAndWith, 1, toNotIncluding);
    }

    public static Counter downFrom(int n) {
        return new Counter(n, -1, 0);
    }

    private class MyIt implements Iterator<Integer> {

        public boolean hasNext() {
            if (m_step > 0) {
                return m_start < m_end;
            }
            return m_start > 0;
        }

        public Integer next() {

            if (hasNext()) {
                int retval = m_start;
                m_start += m_step;
                return retval;
            }
            throw new NoSuchElementException();
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public Iterator<Integer> iterator() {
        return new MyIt();
    }
}
