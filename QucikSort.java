public class QucikSort {

    int[] arr;
    long beign, end;

    public void quicksort(int[] data) {
        beign = System.currentTimeMillis();
        arr = data;
        sort(arr, 0, arr.length - 1);
        end = System.currentTimeMillis();
    }

    public double runtime() {
        return (end - beign);
    }

    public void sort(int[] arr, int left, int right) {
        if (right >= left) {
            // 保存基数
            int basic = arr[left];
            // 定义左右指针
            int i = left;
            int j = right;
            while (i < j) { // 左指针小于右指针
                while (i < j && arr[j] > basic) {// 操作右指针找到小于基数的下标
                    j--;



                    
                }
                if (i < j) {
                    arr[i] = arr[j]; // 将右指针对应小于基数的值放到左指针所指的位置
                    i++; // 左指针自加
                }
                while (i < j && arr[i] < basic) {// 相反，找到大于基数的下标
                    i++;
                }
                if (i < j) {
                    arr[j] = arr[i]; // 大于基数的值赋给右指针所指的位置
                    j--; // 右指针自减
                }
            }
            arr[i] = basic; // 将基数放入到指针重合处
            sort(arr, left, i - 1); // 重复调用，对左半部分数组进行排序
            sort(arr, i + 1, right); // 对右半部分数组进行排序
        }
    }

    public int[] OrderData() {
        return arr;
    }

}
