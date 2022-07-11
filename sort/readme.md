The hardest part of mergeSort is to cut down the needless operations (copy data from the original array in order to merge them)
the key idea is:
1. to create a spareArr at the beginning and never create another one when merging.
2. exchange the role of spareArr and the originalArr each time we do recurrsion(the icing on the cake). if the original array uses tempArray as its backup at one time, then in the next recursion(or loop) the tempArr uses the originalArr as its backup.This avoid needless operations tremendously.
-------------------------------------------------------------------
extra experiments:
1. I notice a significant performance difference between generic and non-generic, non-generic one is 4-5 times faster than the generic one(data size 10 million), no significant difference between T[] and List<T>;
2. mergeSort without recursion is 2 times faster than the recursion one(data size 10 million);
3. sort with red black tree is even slower
4. java version 17, default sort, arrays.sort and collections.sort are quicker than mergeSort now.