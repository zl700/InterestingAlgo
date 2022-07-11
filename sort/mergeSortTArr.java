import java.util.Arrays;
import java.util.Random;

public class mergeSortTArr {
    
    public static <T extends Comparable<T>> void merge(T[] list,T[] spareList,int ll,int lr,int rl,int rr){
        int i=ll;
        int k=ll;
        int j=rl;
        while(i<=lr&&j<=rr){
            if(list[i].compareTo(list[j])<0){
                spareList[k]=list[i];
                i++;
                k++;
            }else{
                spareList[k]=list[j];
                j++;
                k++;
            }
        }
        for(;i<=lr;i++){
            spareList[k]=list[i];
            k++;
        }

        for(;j<=rr;j++){
            spareList[k]=list[j];
            k++;
        }
      
    }

    public static <T extends Comparable<T>> void sortR(T[] list,T[] spareList,int l,int r){
        if(l==r){
            return;
        }
        int m_index=(l+r)/2;
        sortR(spareList,list,l, m_index);
        sortR(spareList,list, m_index+1, r);
        merge(spareList,list,l,m_index,m_index+1,r);
    }

    // public static <T extends Comparable<T>> void sort(Class<T> clazz,T[] list) {
    //     T[] spareList=(T[])Array.newInstance(clazz,list.length);
    //     for (int i = 0; i < spareList.length; i++) {
    //         spareList[i] = list[i];
    //     }
    //     sortR(list, spareList, 0, list.length - 1);
    // }
    public static <T extends Comparable<T>> void sort(T[] list){
        T[] spareList=Arrays.copyOf(list, list.length);
        for (int i = 0; i < spareList.length; i++) {
            spareList[i]=list[i];
        }
        sortR(list, spareList, 0,list.length-1);
    }

    public static Integer[] generate(int total){
        Integer[] arr=new Integer[total];
        Random random=new Random();
        for(int i=0;i<total;i++){
            arr[i]=random.nextInt();
        } 
        return arr;
    }
    public static void main(String[] args) {
        Integer[] list = generate(10000000);
        long start=System.currentTimeMillis();
        mergeSortTArr.sort(list);
        long end=System.currentTimeMillis();
        System.out.println(end-start);
        // for(Integer i:list){
        //     System.out.println(i);
        // }
    }
}
