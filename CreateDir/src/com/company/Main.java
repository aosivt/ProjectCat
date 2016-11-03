package com.company;

import java.io.File;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.*;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.FileUtil;
import org.apache.hadoop.fs.Path;

public class Main {

    private static String DirTiff; ///точка доступа к папке с tiff

    private static File TiffPath; //точка доступа к списку файлов tiff
    private static File []TiffList; //собираем список файлов в определенном каталоге

    public static void main(String[] args) {


        if (args.length <= 0) {

            Configuration conf = new Configuration();
            try {
                FileSystem hdfs = FileSystem.get(conf);
                hdfs.mkdirs(new Path("/user/alex/testiest"));
//                hdfs.createNewFile(new Path("/user/alex/testiest/part"));
                Path[] paths = new Path[] {
                        (new Path("/user/alex/CustKey/temp/part-r-00000"))
                        ,(new Path("/user/alex/CustKey/temp/part-r-00001"))
                        ,(new Path("/user/alex/CustKey/temp/part-r-00002"))
                        ,(new Path("/user/alex/CustKey/temp/part-r-00003"))
                        ,(new Path("/user/alex/CustKey/temp/part-r-00004"))
                        ,(new Path("/user/alex/CustKey/temp/part-r-00005"))
                        ,(new Path("/user/alex/CustKey/temp/part-r-00006"))
                        ,(new Path("/user/alex/CustKey/temp/part-r-00007"))};

                hdfs.concat(new Path("/user/alex/testiest/part"),paths);


            } catch (IOException e) {
                e.printStackTrace();
            }


        }
        else
        {

            String srcPath = args[0];///"/user/hadoop/output";
            String dstPath = args[1];//"/user/hadoop/merged_file";
            Configuration conf = new Configuration();
            try { FileSystem hdfs = FileSystem.get(conf);
                ///hdfs.createNewFile(new Path(args[1]));
                FileUtil.copyMerge(hdfs, new Path(srcPath), hdfs, new Path(dstPath), false, conf, null); }
            catch (IOException e) { }

        }
        /*
        else {

            String tempString = "";
            DirTiff = "output";
            TiffPath = new File(DirTiff);

            SortBy sb = new SortBy();
            sb.fileList = Arrays.asList(TiffPath.listFiles());

            TiffList = sb.sortByName();

            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
            timeFormat = new SimpleDateFormat("HH:mm:ss");

            System.out.println(timeFormat.format(new Date(System.currentTimeMillis())));
            String temp;
            byte[] bytes;
            if (args.length < 1) {
                (new File("tempCL")).mkdir();
            } else {
                RandomAccessFile mixfile = null;
                try {
                    mixfile = new RandomAccessFile(new File("mixfile"), "rw");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                for (int i = 0; i < TiffList.length; i++) {

                    //Нужны только папки в место isFile() пишим isDirectory()
                    //так указали не брать файлы с расширением "crc" и файлы содержащие "_SUCCESS"

                    if (TiffList[i].isFile() && !TiffList[i].getName().endsWith(".CRC")
                            && TiffList[i].getName().indexOf("_SUCCESS") < 0) {
                        try {
                            RandomAccessFile file = new RandomAccessFile(new File(TiffPath.getPath()
                                    + File.separator + TiffList[i].getName()), "r");

                            mixfile.seek(mixfile.length());
                            file.seek(0);// /сдвигает указатель на указанное количество байтов вправо относительно начала.
                            bytes = new byte[(int) file.length()];
                            file.readFully(bytes, 0, (int) file.length());
                            mixfile.write(bytes);
                            bytes = null;
                            file.close();

                        } catch (FileNotFoundException e) {
                            System.out.println(e.getMessage());
                        } catch (IOException e) {
                            System.out.println(e.getMessage());
                        }
                    }

                }
                try {
                    mixfile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
      */
    }

    }

