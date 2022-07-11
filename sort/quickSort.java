import java.util.Random;

public class quickSort {

    public static void swap(int[] arr,int i,int j) {
        int temp_val=arr[i];
        arr[i]=arr[j];
        arr[j]=temp_val;
    }
    public static int partition(int[] arr,int l,int r){
        int placeToExchange=l+1;
        for(int i=l+1;i<=r;i++){
            if(arr[i]<arr[l]){
                swap(arr, i,placeToExchange);
                placeToExchange++;
            }
        }
        swap(arr, l, placeToExchange-1);
        return placeToExchange-1;
    }

    public static void sort(int[] arr,int l,int r){
        if(l>=r){
            return;
        }
        int pivot=partition(arr,l,r);
        sort(arr, l, pivot-1);
        sort(arr, pivot+1, r);
    }

    public static int[] generate(int total){
        int[] arr=new int[total];
        Random r=new Random();
        for (int i = 0; i < total; i++) {
            arr[i]=r.nextInt(total);
        }
        return arr;
    }

    public static void show(int[] arr){
        for(int i:arr){
            System.out.printf("%d,",i);
        }
        System.out.println();
    }
    public static boolean isSorted(int[] arr){
        int pre=arr[0];
        for (int i = 1; i < arr.length; i++) {
            if(pre>arr[i]){
                return false;
            }
            pre=arr[i];
        }
        return true;
    }

    public static void test(int total){
        Random r=new Random();
        for (int i = 0; i < total; i++) {
            int[] arr=generate(r.nextInt(1,total));
            // show(arr);                
            sort(arr, 0, arr.length-1);
            // show(arr);
            if(isSorted(arr)){
                System.out.println("true");
            }else{
                System.out.println("false");
                show(arr);
            }

        }
    }
    public static void main(String[] args) {
        int[] arr=generate(100000000);
        long start=System.currentTimeMillis();
        sort(arr, 0, arr.length-1);
        long end=System.currentTimeMillis();
        System.out.println(end-start);
        // for(int i:arr){
        //     System.out.printf("%d ", i);
        // }

        // test(100);

    }
}
