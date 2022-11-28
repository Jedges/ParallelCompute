import java.util.concurrent.CountDownLatch;

public class ParallelEnumerationSort {
    int[] arr;
    int[] orderarr;
    long time1, time2;
    int threadnum = Main.threadnum;
    CountDownLatch mergeSignal = new CountDownLatch(threadnum);

    /**
     * @param data
     */
    public void PEsort(int[] data) {
        time1 = System.currentTimeMillis();
        arr = data;
        orderarr = new int[arr.length];

        int part = arr.length / threadnum;
        int begin = 0;
        int end = part;
        for (int i = 0; i < threadnum; i++) {
            Thread thread = new SortThread(begin, end);
            thread.run();
            begin = end;
            end += part;
            if (i == threadnum - 2)
                end = arr.length - 1;
            // System.out.println(begin + "----" + end + "\n");
        }
        try {
            mergeSignal.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        time2 = System.currentTimeMillis();
    }

    class SortThread extends Thread {
        private int begin, end;

        SortThread(int begin, int end) {
            this.begin = begin;
            this.end = end;

        }

        @Override
        public void run() {
            for (int i = begin; i < end; i++) {
                int rank = 0;
                for (int j = 0; j <= arr.length - 1; j++) {
                    if ((arr[i] > arr[j]) || ((arr[i] == arr[j] && i > j)))
                        rank++;
                }

                orderarr[rank] = arr[i];

            }
            mergeSignal.countDown();
        }

    }

    public double runtime() {
        return (time2 - time1);
    }

    public int[] OrderData() {
        return orderarr;
    }
}
