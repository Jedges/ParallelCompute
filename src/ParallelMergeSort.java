
import java.util.concurrent.CountDownLatch;

public class ParallelMergeSort {

    private MergeSort mst = new MergeSort();
    private int maxDepth = (int) (Math.log(Runtime.getRuntime().availableProcessors()) / Math.log(2));
    int asynDepth;
    long time1, time2;

    ParallelMergeSort(int[] data) {
        mst.arr = data;
    }

    public void sort() {
        time1 = System.currentTimeMillis();
        sortParallel(0, mst.arr.length - 1, maxDepth, 1);
        time2 = System.currentTimeMillis();
    }

    public void sortParallel(int pos, int end, int maxnDepth, int depth) {
        if ((end - pos) > 1) {
            CountDownLatch mergeSignal = new CountDownLatch(2);
            int offset = (end + pos) / 2;
            Thread thread1 = new SortThread(depth, maxDepth, mergeSignal, pos, offset);
            Thread thread2 = new SortThread(depth, maxDepth, mergeSignal, offset + 1, end);
            thread1.start();
            thread2.start();
            try {
                mergeSignal.await();
            } catch (InterruptedException e) {
            }
            mst.merge(pos, offset + 1, end);
        }
    }

    class SortThread extends Thread {

        private int depth;

        private int maxDepth;

        private CountDownLatch mergeSignal;

        private int pos;

        private int end;

        public SortThread(int depth, int maxDepth, CountDownLatch mergeSignal, int pos, int end) {
            super();
            this.depth = depth;
            this.maxDepth = maxDepth;
            this.mergeSignal = mergeSignal;
            this.pos = pos;
            this.end = end;
        }

        @Override
        public void run() {
            if (depth < asynDepth) {
                sortParallel(pos, end, maxDepth, (depth + 1));
            } else {
                mst.sort(pos, end);
            }
            mergeSignal.countDown();
        }

    }

    public int[] OrderData() {
        return mst.arr;
    }

    public double runtime() {
        return (time2 - time1);
    }

}