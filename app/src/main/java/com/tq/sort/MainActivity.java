package com.tq.sort;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private TextView tvShow;
    private TextView tvResult;
    private Button btnMaoPao;
    private Button btnXuanZe;
    private Button btnQuick;
    private static final String TAG = "MainActivity ";

    private int[] a = {3, 105, 29, 15, 36, 64, 222, 125, 318, 48};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("MainActivity","我是分支1");
        Log.d("MainActivity","修改分支1");
        setContentView(R.layout.activity_main);
        tvShow = findViewById(R.id.tv_show);
        tvResult = findViewById(R.id.tv_result);
        btnMaoPao = findViewById(R.id.btn_maopao);
        btnXuanZe = findViewById(R.id.btn_xuanze);
        btnQuick = findViewById(R.id.btn_quick);
        btnMaoPao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sortByMaoPao();
//                sortByMaoPao2();
                StringBuffer buffer = new StringBuffer();
                for (int i = 0; i < a.length; i++) {
                    buffer.append(a[i] + ",");
                }
                tvResult.setText(buffer.toString());
            }
        });
        btnXuanZe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sortByXuanZe();
                StringBuffer buffer = new StringBuffer();
                for (int i = 0; i < a.length; i++) {
                    buffer.append(a[i] + ",");
                }
                tvResult.setText(buffer.toString());
            }
        });
        btnQuick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sortByQuick(a, 0, a.length - 1);
                StringBuffer buffer = new StringBuffer();
                for (int i = 0; i < a.length; i++) {
                    buffer.append(a[i] + ",");
                }
                tvResult.setText(buffer.toString());
                int position = binSearch(a, 0, a.length - 1, 318);
                Log.d(TAG, "position:" + position);
            }
        });
    }

    private void sortByMaoPao() {
//        int count = 0;
        for (int i = 0; i < a.length - 1; i++) {
            for (int j = 0; j < a.length - 1 - i; j++) {
//                count = count+1;
                if (a[j] > a[j + 1]) {
                    int temp = a[j + 1];
                    a[j + 1] = a[j];
                    a[j] = temp;
                }
            }
        }
//        Log.d(TAG,"count:"+count);
    }

    private void sortByMaoPao2() {
        int i = a.length - 1;
//        int count = 0;
        while (i > 0) {
            int pos = 0;
            for (int j = 0; j < i; j++) {
//                count = count+1;
                if (a[j] > a[j + 1]) {
                    pos = j;
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
            }
            i = pos;
        }
//        Log.d(TAG,"count:"+count);
    }

    private void sortByXuanZe() {
        for (int i = 0; i < a.length - 1; i++) {
            for (int j = i + 1; j < a.length; j++) {
                if (a[j] < a[i]) {
                    int temp = a[j];
                    a[j] = a[i];
                    a[i] = temp;
                }
            }
        }
    }

    public void sortByQuick(int[] a, int low, int high) {
        int start = low;
        int end = high;
        int key = a[low];


        while (end > start) {
            //从后往前比较
            while (end > start && a[end] >= key)  //如果没有比关键值小的，比较下一个，直到有比关键值小的交换位置，然后又从前往后比较
                end--;
            if (a[end] <= key) {
                int temp = a[end];
                a[end] = a[start];
                a[start] = temp;
            }
            //从前往后比较
            while (end > start && a[start] <= key)//如果没有比关键值大的，比较下一个，直到有比关键值大的交换位置
                start++;
            if (a[start] >= key) {
                int temp = a[start];
                a[start] = a[end];
                a[end] = temp;
            }
            //此时第一次循环比较结束，关键值的位置已经确定了。左边的值都比关键值小，右边的值都比关键值大，但是两边的顺序还有可能是不一样的，进行下面的递归调用
        }
        //递归
        if (start > low) sortByQuick(a, low, start - 1);//左边序列。第一个索引位置到关键值索引-1
        if (end < high) sortByQuick(a, end + 1, high);//右边序列。从关键值索引+1到最后一个
    }

    //二分查找
    public int binSearch(int a[], int start, int end, int key) {
        int mid = (end - start) / 2 + start;
        if (a[mid] == key) {
            return mid;
        }
        if (start >= end) {
            return -1;
        }
        if (key > a[mid]) {
            return binSearch(a, mid + 1, end, key);
        }
        if (key < a[mid]) {
            return binSearch(a, start, mid - 1, key);
        }
        return -1;
    }
}
