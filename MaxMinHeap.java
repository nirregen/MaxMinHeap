public class MaxMinHeap {
    private int heap_size;//the size of the heap
    private int array_size;//An array containing the haep
    private int[] arr;

    /**
     * Build the Max-Min-Heaa
     * @param A is array to build from the heap. 
     */
    public MaxMinHeap(int[] A) {
        heap_size = A.length;
        if (heap_size == 0) {
            array_size = 1;
            heap_size = 0;
        } else {
            array_size = heap_size * 2;// Doubles the size of the array so that there was place to add the values to the array
        }
        arr = new int[array_size];
        for (int i = (heap_size / 2); i >= 0; i--) {
            HEAPIFY(A, i);
        }
        CopyArray(arr, A);//Deep copy of array values

    }

    /**
     * Deep copy of array values from B to A.
     * @param A is array the take the values. 
     * @param B array to put the num inside from A.
     */
    private void CopyArray(int[] A, int B[]) {
        for (int i = 0; i < B.length; i++) {
            A[i] = B[i];
        }
    }

    /**
     * Swape places in array
     * @param a array to cange inside.
     * @param i first int place inside array.
     * @param j secand int place inside array.
     */
    private void swape(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    /**
     * Check if you have grandFather
     * @param c the place of the child to check from 
     * @param p the place of the parths of the child
     * @return True if have grandFather,False if not. 
     */
    private boolean grand(int c, int p) {
        for (int i = 0; i <= 1; i++) {
            if (c % 2 == 0) {
                c--;
            }
            c = c / 2;
        }
        return p == c;
    }
    
    /**
     * Increases the value if necessary up the heap
     * @param i the palce in the heape 
     */
    private void BubleUp(int i) {
        if (i != 0) {// check if the place is not the root for check the father and grandFather.  
            int p = (i - 1) / 2;
            int grandFather = (p - 1) / 2;
            if ((int) (Math.log(i + 1) / Math.log(2)) % 2 == 0) {//Checks if its height is even
                if (arr[p] > arr[i]) {
                    swape(arr, i, p);
                    BubleUp(p);
                }
                if (grandFather >= 0) {
                    if (arr[grandFather] < arr[i]) {
                        swape(arr, i, grandFather);
                    }
                }

            }
            if (((int) (Math.log(i + 1) / Math.log(2)) % 2 != 0)) {//Checks if its height is odd
                if (arr[p] < arr[i]) {
                    swape(arr, i, p);
                    BubleUp(p);
                }

                else if (grandFather >= 0) {//if the i have GrandFather check if you smallar then the grand.
                    if (arr[grandFather] > arr[i]) {
                        swape(arr, i, grandFather);
                    }
                }

            }
        }
    }

    /**
     * Heapify to the array to make the heap.
     * @param A array int.
     * @param i the place in the array to make him heapfiy.
     */
    public void HEAPIFY(int[] A, int i) {
        int l = (2 * (i + 1) - 1);//left son
        int r = 2 * (i + 1);// right son 
        int lr = 2 * (l + 1);// The right son of the left son
        int ll = (2 * (l + 1) - 1);//The left son of the left son
        int rr = 2 * (r + 1);//The right son of the right son
        int rl = (2 * (r + 1) - 1);//The left son of the right son


        if ((int) (Math.log(i + 1) / Math.log(2)) % 2 == 0) {//Checks if its height is even
            //Check form 6 places if there exists and who have the max value.
            int max = i;
            if (l < heap_size) {
                if (A[l] > A[max]) {
                    max = l;
                }
            }
            if (r < heap_size) {
                if (A[r] > A[max]) {
                    max = r;
                }
            }
            if (lr < heap_size) {
                if (A[lr] > A[max]) {
                    max = lr;
                }
            }
            if (ll < heap_size) {
                if (A[ll] > A[max]) {
                    max = ll;
                }
            }
            if (rr < heap_size) {
                if (A[rr] > A[max]) {
                    max = rr;
                }
            }
            if (rl < heap_size) {
                if (A[rl] > A[max]) {
                    max = rl;
                }
            }
            if (max != i) {
                swape(A, i, max);
                if (heap_size >= 4) {// if the heap more the 4 maining that have grandson to chek with them. 
                    if (grand(max, i)) {//check if exists grand to the place of the max value.
                        if (A[max] < A[((max - 1) / 2)]) {//check if the value of max is smaller then the Father.
                            swape(A, max, ((max - 1) / 2));
                        }
                        HEAPIFY(A, max);
                    }
                }
            }
        }
        // the odd places.
        if ((int) (Math.log(i + 1) / Math.log(2)) % 2 != 0) {//Checks if its height is odd
             //Check form 6 places if there exists and who have the min value.
            int min = i;
            if (l < heap_size) {
                if (A[l] < A[min]) {
                    min = l;
                }
            }
            if (r < heap_size) {
                if (A[r] < A[min]) {
                    min = r;
                }
            }
            if (lr < heap_size) {
                if (A[lr] < A[min]) {
                    min = lr;
                }
            }
            if (ll < heap_size) {
                if (A[ll] < A[min]) {
                    min = ll;
                }
            }
            if (rr < heap_size) {
                if (A[rr] < A[min]) {
                    min = rr;
                }
            }
            if (rl < heap_size) {
                if (A[rl] < A[min]) {
                    min = rl;
                }
            }
            if (min != i) {
                swape(A, i, min);
                if (heap_size >= 4) {// if the heap more the 4 maining that have grandson to chek with them. 
                    if (grand(min, i)) {//check if exists grand to the place of the min value.
                        if (A[min] > A[((min - 1) / 2)]) {//check if the value of min is bigger then the Father.
                            swape(A, min, ((min - 1) / 2));
                        }
                        HEAPIFY(A, min);
                    }
                }
            }
        }
    }

    /**
     * Insert new Key to the Heap
     * @param key the int value.
     */
    public void Heap_Insert(int key) {
        //if the array not full add to the end and do bubleUP. 
        if (array_size != heap_size) {
            arr[heap_size] = key;
            heap_size = heap_size + 1;
            BubleUp(heap_size - 1);
        } else {
            //the array is full therfore create new array double size and coppy all the valuew from old array to the new one.
            array_size = array_size * 2;
            int[] bigArray = new int[array_size];
            CopyArray(bigArray, arr);
            arr = new int[array_size];
            CopyArray(arr, bigArray);
            Heap_Insert(key);
        }
    }

    /**
     * get out the maximum from the heap.
     * if the heap is empty return 0. 
     * @return the maximum vaulue of the heap.
     */
    public int Heap_Extract_Max() {
        if(heap_size <= 0){
            return 0;
        }
        else{
        int max = arr[0];
        swape(arr, 0, heap_size - 1);
        heap_size = heap_size - 1;
            HEAPIFY(arr, 0);
        return max;
        }
    }

    /**
     * get out the minimum from the heap.
     * @return  the minimum vaulue of the heap.
     */
    public int Heap_extract_Min() {
        int min;
        if (heap_size > 2 && arr[1] >= arr[2]) {
            min = arr[2];
            swape(arr, 2, heap_size-1);
            heap_size--;
            HEAPIFY(arr, 2);
            return min;
        } else if (heap_size > 2 && arr[2] > arr[1]) {
            min = arr[1];
            swape(arr, 1, heap_size-1);
            heap_size--;
            HEAPIFY(arr, 1);
            return min;
        }
        else if(heap_size ==2){
            heap_size--;
            return arr[1];
        }
        else{
            return arr[0];
        }
         
    }
    
    /**
     * Delete the value in the place of i.
     * @param i the place in the array to delete.
     */
    public void Heap_Delete(int i) {
        if (heap_size > 0) {
            swape(arr, i, heap_size - 1);
            heap_size--;
            for (int j = (heap_size / 2); j >= 0; j--) {
                HEAPIFY(arr, j);
            }
        }
    }

    @Override
    public String toString() {
        String a = "[";
        for (int i = 0; i < heap_size; i++) {
            if (i == heap_size - 1) {
                a = a + arr[i];
            } else {
                a = a + arr[i] + ',';
            }

        }
        a = a + "]";
        return a;
    }
    
    /**
     * sort the heap from min to max.
     */
    public void Heap_sort(){
        //Removes the minimum and shortens the heap.
        int length = heap_size+1;
        int [] B = new int[length]; 
        for(int i = 0;i< length;i++){
            int temp = Heap_extract_Min();
            B[i] = temp; 
        }
        heap_size = length-1;
        CopyArray(arr,B);
    }


}
