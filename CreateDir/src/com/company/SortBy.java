package com.company;

import java.io.File;
import java.io.FileDescriptor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by alex on 30.10.15.
 */


public class SortBy {
    public List fileList;

    public File[] sortByName() {

        ArrayList res = new ArrayList(fileList.size());
        //копируем список
        res.addAll(fileList);
        //выполняем сортировку
        Collections.sort(res);
        //возвращаем результат
        File[] f  = new File[res.size()];
        res.toArray(f);
        return f;

    }
    public List sortBySize(List fileList) {

        ArrayList res = new ArrayList(fileList.size());
        //копируем список
        res.addAll(fileList);
        //выполняем сортировку
        Collections.sort(res);
        //возвращаем результат
        return res;

    }

}
