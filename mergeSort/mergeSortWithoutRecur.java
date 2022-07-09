import java.util.Random;

public class mergeSortWithoutRecur {

    // left[ll,lr] inclusive, right[rl,rr] inclusive
    // this is better than left(l,m), right(m+1,r) when writting out sort without recursion
    public static void merge(int[] arr, int[] sparearr, int ll, int lr, int rl, int rr) {
        int i = ll;
        int k = ll;
        int j = rl;
        // we need to adjust index which goes beyond the whole arr.
        if(ll>arr.length-1){
            ll=arr.length-1;
            lr=arr.length-1;
            rl=arr.length-1;
            rr=arr.length-1;
        }else if(lr>arr.length-1){
            lr = arr.length - 1;
            rl = arr.length - 1;
            rr = arr.length - 1;
        }else if(rl>arr.length-1){
            rl = arr.length - 1;
            rr=arr.length - 1;
        }else if(rr>arr.length-1){
            rr=arr.length - 1;
        }
        while (i <= lr && j <= rr) {
            if (arr[i]<arr[j]) {
                sparearr[k] = arr[i];
                i++;
                k++;
            } else {
                sparearr[k] = arr[j];
                j++;
                k++;
            }
        }
        for (; i <= lr; i++) {
            sparearr[k] = arr[i];
            k++;
        }

        for (; j <= rr; j++) {
            sparearr[k] = arr[j];
            k++;
        }

    }

    public static int[] generate(int total) {
        int[] arr = new int[total];
        Random random = new Random();
        for (int i = 0; i < total; i++) {
            arr[i] = random.nextInt(50);
        }
        return arr;
    }
    public static int[] sort(int[] arr){
        int[] spareArr=new int[arr.length];
        for (int i = 0; i < spareArr.length; i++) {
            spareArr[i]=arr[i];
        }
        int step=0;int count=0;
        while(true){
            //this is tricky, we need to find the law by ourselves:
            //loop0        loop1           loop2
            //ll lr rl rr  ll lr rl rr   ll lr rl rr
            //0  0  1  1   0  1  2  3    0  3  4  7
            //2  2  3  3==>4  5  6  7==> 8  11 12 15
            //4  4  5  5   8  9  10 11   16 19 20 23
            //6  6  7  7   12 13 14 15   24 27 28 32  
            step=(int)Math.pow(2, count)-1;
            
            for(int i=0; i<arr.length; i=i+step*2+2){
                if(count%2==0){
                    merge(arr, spareArr, i, i+step,i+step+1,i+step*2+1);
                }else{
                    merge(spareArr, arr, i, i+step,i+step+1,i+step*2+1);
                }
            }
            count++;
            // when we sort the array with one merge, we can break
            // the last element is step*2+1,check for lookloop for deduction of last element 
            // i+step*2+1 when i==0;
            // so when step*2+1 is larger than the last index arr.length-1
            if (step * 2 + 2 > arr.length) {
                break;
            }
        }
        
        if(count%2==0){
            return arr;
        }else{
            return spareArr;
        }
    }

    public static boolean isSorted(int[] arr){
        int previous=arr[0];
        for(int i=1;i<arr.length;i++){
            if(previous>arr[i]){
                return false;
            }
            previous=arr[i];
        }
        return true;
    }
    public static void show(int[] arr){
        for (int i : arr) {
            System.out.printf("%d,", i);
        }
        System.out.println("\n");
    }
    public static void test(int total){
        Random r=new Random();
        for(int i=0;i<total;i++){
            int[] arr=generate(r.nextInt(1,1000000));
            int[] res=sort(arr);
            System.out.println(isSorted(res));
            if(isSorted(res)==false){
                show(res);
            }
        } 
    }
    public static void main(String[] args) {
        //it can sort 100million data in around 7s
        // int[] arr=generate(100000000);
        // long start=System.currentTimeMillis();
        // int[] res=mergeSortWithoutRecur.sort(arr);
        // long end=System.currentTimeMillis();
        // System.out.println(end-start);
        // System.out.println(isSorted(res));
        test(100);
    }

}
