#include<bits/stdc++.h>
using namespace std;

// bubbleSort
void bubbleSort(vector<int>& arr) {
    int N = arr.size();
    for(int i=0 ; i<N-1 ; ++i) {
        for(int j=0 ; j<N-i-1 ; ++j) {
            if(arr[j] > arr[j+1])
                swap(arr[j], arr[j+1]);
        }
    }
}

// insertionSort
void insertionSort(vector<int>& arr) {
    int N = arr.size();
    for(int i=1 ; i<N ; ++i) {
        int key = arr[i];
        int j = i-1;
        while( j>=0 && key < arr[j] ) {
            arr[j+1] =  arr[j];
            j--;
        }
        arr[j+1] = key;
    }
}

// selectionSort
void selectionSort(vector<int>& arr) {
    int N = arr.size();
    for(int i=0 ; i<N-1 ; ++i) {
        int min = i;
        for(int j=i ; j<N ; ++j) {
            if( arr[min] > arr[j] )
                min = j;
        }
        swap(arr[i], arr[min]);
    }
}

// mergeSort
void merge(vector<int>& arr, int start, int mid, int end) {
    vector<int> temp(end-start+1);
    int x=start, y=mid+1, z = 0;

    while(x <= mid && y <= end) 
        temp[z++] = ( arr[x] < arr[y] ) ? arr[x++] : arr[y++];

    while(x <= mid ) 
        temp[z++] = arr[x++];

    while(y <= end) 
        temp[z++] = arr[y++];
    
    for(int i=start ; i<=end ; ++i) 
        arr[i] = temp[i-start];
    
}

void mergeSort(vector<int>& arr, int start, int end) {
    if(start == end) {
        return;
    }
    int mid = start + (end - start) / 2;
    mergeSort(arr, start, mid);
    mergeSort(arr, mid + 1, end);
    merge(arr, start, mid, end);
}

// quickSort
void quickSort(vector<int>& arr, int start, int end) {
    if(start >= end)
        return;
    
    int mid = start + (end-start) / 2;
    int key = arr[mid] , low = start;

    swap(arr[end], arr[mid]);
    for(int i=start; i<end ; ++i) {
        if( arr[i] <= key) 
            swap(arr[i], arr[low++]);
    }
    swap(arr[low], arr[end]);

    quickSort(arr, start, mid-1);
    quickSort(arr, mid+1, end);
}

// heapSort
void maxHeapify(vector<int>& arr, int position, int heapSize) {
    int l = 2*position + 1;
    int r = 2*position + 2;

    int max = position;
    if( l < heapSize  && arr[l] > arr[max]) 
        max = l;
    
    if( r < heapSize && arr[r] > arr[max] ) 
        max = r;
        
    
    if (max != position) {
        swap(arr[position], arr[max]);
        maxHeapify(arr, max, heapSize);
    }
}

pair<int, int> extractMax(vector<int>& arr, int heapSize) {
    int maxi = arr[0];
    heapSize--;
    arr[0] = arr[heapSize];
    maxHeapify(arr, 0, heapSize);
    return {maxi, heapSize};
}

void buildMaxHeap(vector<int>& arr, int heapSize) {
    if(heapSize == 1)
        return;
    for(int i = heapSize/2 - 1 ; i >= 0 ; --i ) 
        maxHeapify(arr, i, heapSize);
}

void heapSort(vector<int>& arr) {
    int N = arr.size();
    int peek;
    int heapSize = arr.size();

    buildMaxHeap(arr, heapSize); 
    for(int i=arr.size()-1 ; i > 0 ; --i) {
        tie(peek, heapSize) = extractMax(arr, heapSize);
        arr[i] = peek;
    }
}

// countingSort
void countingSort(vector<int>& arr) {
    int mini = *min_element(arr.begin(), arr.end());
    int maxi = *max_element(arr.begin(), arr.end());

    vector<int> dict( (maxi-mini+1) , 0);

    for(int i: arr)
        dict[i-mini] += 1;

    int index = 0;
    for(int i=mini ; i<=maxi ; ++i) {
        int cnt = dict[i-mini];
        while( cnt-- ) 
            arr[ index++] = i;
    }
}

// radixSort
void radixCounter(vector<int> & arr, int exp) {
    vector<int> count(10, 0);
    vector<int> temp(arr.size(), 0);
    for(int elem: arr)
        count[ (elem/exp) % 10] += 1;
    
    for(int i=1 ; i<10 ; ++i) 
        count[i] += count[i-1];

    for(int i=arr.size()-1 ; i >= 0 ; --i) {
        int elem = arr[i];
        temp[ --count[(elem/exp)%10] ] = elem;
    }
    arr = temp;
}

void radixSort(vector<int>& arr) {
    int maxi =*max_element(arr.begin(), arr.end() ) ; 
    for(int exp = 1 ; maxi / exp > 0; exp *= 10) {
        radixCounter(arr, exp);
    }
}

