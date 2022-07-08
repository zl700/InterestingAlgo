import java.util.Random;

public class mergeSort {
    
    public static  void merge(int[] arr,int[] spareArr,int ll,int lr,int rl,int rr){
        int i=ll;
        int k=ll;
        int j=rl;
        while(i<=lr&&j<=rr){
            if(arr[i]<arr[j]){
                spareArr[k]=arr[i];
                i++;
                k++;
            }else{
                spareArr[k]=arr[j];
                j++;
                k++;
            }
        }
        for(;i<=lr;i++){
            spareArr[k]=arr[i];
            k++;
        }

        for(;j<=rr;j++){
            spareArr[k]=arr[j];
            k++;
        }
      
    }

    public static void merge1(int[] arr, int[] spareArr, int l, int m, int r) {
        int i = l;
        int k = l;
        int j = m+1;
        while (i <= m && j <= r) {
            if (arr[i] < arr[j]) {
                spareArr[k] = arr[i];
                i++;
                k++;
            } else {
                spareArr[k] = arr[j];
                j++;
                k++;
            }
        }
        for (; i <= m; i++) {
            spareArr[k] = arr[i];
            k++;
        }

        for (; j <= r; j++) {
            spareArr[k] = arr[j];
            k++;
        }

    }
    public static void sortR(int[] arr,int[] spareArr,int l,int r){
        if(l==r){
            return;
        }
        int m_index=(l+r)/2;
        sortR(spareArr,arr,l, m_index);
        sortR(spareArr,arr, m_index+1, r);

        // merge and merge1 have the same logic, the only difference is 
        // merge uses (ll lr) to define left part and(rl rr) to define right left while merge1 uses(l,m) and(m+1,r);
        // merge(spareArr,arr,l,m_index,m_index+1,r);
        merge1(spareArr,arr,l,m_index,r);
    }

    public static void sort(int[] arr){
        int[] spareArr=new int[arr.length];
        for (int i = 0; i < spareArr.length; i++) {
            spareArr[i]=arr[i];
        }
        sortR(arr, spareArr, 0,arr.length-1);
    }

    public static int[] generate(int total){
        int[] arr=new int[total];
        Random random=new Random();
        for(int i=0;i<total;i++){
            arr[i]=random.nextInt();
        } 
        return arr;
    }
    public static void main(String[] args) {
        // int[] arr=new int[]{19,5,3,6,1,4,3,1};
        int[] arr = generate(10000000);
        long start=System.currentTimeMillis();
        mergeSort.sort(arr);
        long end=System.currentTimeMillis();
        System.out.println(end-start);

        // for(int i:arr){
        //     System.out.println(i);
        // }
    }
}
