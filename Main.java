import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static int threadnum = 168;
    public static int[] data;
    public static int[][] orderData = new int[6][];
    static boolean check = true;
    static boolean error = false;

    public static void main(String[] args) {
        read();
        QuickSort qs = new QuickSort(data.clone(), 0, data.length - 1, null);
        qs.sort();
        orderData[0] = qs.OrderData();
        System.out.print("-------------------排序成功---------------\n");
        System.out.println("快速排序运行时间" + qs.runtime() + "ms");

        MergeSort ms = new MergeSort();
        ms.mergesort(data.clone());
        orderData[1] = ms.OrderData();
        System.out.print("-------------------排序成功--------------\n");
        System.out.println("归并排序运行时间" + ms.runtime() + "ms");

        EnumerationSort es = new EnumerationSort();
        es.enumerationSort(data.clone());
        orderData[2] = es.OrderData();
        System.out.print("-------------------排序成功--------------\n");
        System.out.println("枚举排序运行时间" + es.runtime() + "ms");

        ParallelQuickSort pqs = new ParallelQuickSort();
        pqs.pqsort(data.clone(), 0, data.length - 1);
        orderData[3] = pqs.OrderData();
        System.out.print("-------------------排序成功---------------\n");
        System.out.println("并行快速排序运行时间" + pqs.runtime() + "ms");

        ParallelEnumerationSort pes = new ParallelEnumerationSort();
        pes.PEsort(data.clone());
        orderData[4] = pes.OrderData();
        System.out.print("-------------------排序成功--------------\n");
        System.out.println("并行枚举排序运行时间" + pes.runtime() + "ms");

        ParallelMergeSort pms = new ParallelMergeSort(data.clone());
        pms.sort();
        orderData[5] = pms.OrderData();
        System.out.print("-------------------排序成功--------------\n");
        System.out.println("并行归并排序运行时间" + pms.runtime() + "ms");

        if (check) {
            for (int i = 0; i < orderData[0].length; i++)
                if (orderData[5][i] != orderData[1][i] || orderData[2][i] != orderData[1][i]
                        || orderData[0][i] != orderData[2][i]) {
                    System.out.printf("不等处:" + i + orderData[0][i] + orderData[1][i] + orderData[2][i] + "\n");
                    error = true;
                }
            if (!error)
                System.out.print("-------------------排序检查通过--------------\n");
        }
        write();
    }

    public static void read() {
        String path = "./random.txt";
        try {
            File file = new File(path);
            InputStreamReader input = new InputStreamReader(new FileInputStream(file));
            BufferedReader bf = new BufferedReader(input);
            String str = bf.readLine();
            String[] strdata = str.split(" ");
            data = new int[strdata.length];
            for (int i = 0; i < strdata.length; i++) {
                data[i] = Integer.parseInt(strdata[i]);
            }
            bf.close();
            input.close();
            System.out.print("-------------------读取数据成功-------------\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void write() {
        try {
            for (int i = 0; i < 6; i++) {
                BufferedWriter bw = new BufferedWriter(new FileWriter(("order" + (i + 1) + ".txt")));
                for (int j = 0; j < orderData[i].length; j++)
                    bw.write(orderData[i][j] + " ");
                bw.close();
            }

            /*
             * /BufferedWriter bw2 = new BufferedWriter(new FileWriter("time.txt"));
             * bw2.write(" 快速排序 枚举排序 归并排序\n");
             * bw2.write("串行" + " " + globalTime[0][0] + " " + globalTime[0][1] + " " +
             * globalTime[0][2] + "\n");
             * bw2.write("并行" + " " + globalTime[1][0] + " " + globalTime[1][1] + " " +
             * globalTime[1][2]);
             * bw2.close();
             */
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}