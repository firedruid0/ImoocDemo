package com.imooc.imoocpiechart;

import java.util.ArrayList;

/**
 *
 */
public class MonthBean {
    public String date;
    public ArrayList<PieBean> obj;

    @Override
    public String toString() {
        return "MonthBean{" +
                "date='" + date + '\'' +
                ", obj=" + obj +
                '}';
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
