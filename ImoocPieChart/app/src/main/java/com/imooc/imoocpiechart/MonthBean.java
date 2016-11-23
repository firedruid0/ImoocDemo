package com.imooc.imoocpiechart;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 *
 */
public class MonthBean implements Parcelable {
    public String date;
    public ArrayList<PieBean> obj;

    protected MonthBean(Parcel in) {
        date = in.readString();
    }

    public static final Creator<MonthBean> CREATOR = new Creator<MonthBean>() {
        @Override
        public MonthBean createFromParcel(Parcel in) {
            return new MonthBean(in);
        }

        @Override
        public MonthBean[] newArray(int size) {
            return new MonthBean[size];
        }
    };

    @Override
    public String toString() {
        return "MonthBean{" +
                "date='" + date + '\'' +
                ", obj=" + obj +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(date);
    }

    public float getSum() {
        float sum = 0;
        for (PieBean pieBean : obj) {
            sum += pieBean.value;
        }
        return sum;
    }

    public float getSum(int index) {
        float sum = 0;
        for (int i = 0; i < index; i++) {
            sum += obj.get(i).value;
        }
        for (PieBean pieBean : obj) {
            sum += pieBean.value;
        }
        return sum;
    }

    public class PieBean {
        public String title;
        public int value;

        @Override
        public String toString() {
            return "PieBean{" +
                    "title='" + title + '\'' +
                    ", value=" + value +
                    '}';
        }
    }
}
