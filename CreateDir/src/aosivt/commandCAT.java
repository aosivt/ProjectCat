package aosivt;

import com.company.SortBy;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.List;

/**
 * Created by alex on 04.11.15.
 */
public class commandCAT {
    private static String DirTiff; ///точка доступа к папке с tiff

    private static File TiffPath; //точка доступа к списку файлов tiff
    private static File []TiffList; //собираем список файлов в определенном каталоге
    public static void main(String[] args) throws IOException {

        String tempString = "";
        String DirTiff = "/home/alex/IdeaProjects/MapRedCreateTiff/db";
        //String DirTiff = "/home/alex/IdeaProjects/CreateDir/CDH5/";

        File TiffPath = new File(DirTiff);

        SortBy sb = new SortBy();
        sb.fileList = Arrays.asList(TiffPath.listFiles());

        TiffList = sb.sortByName();

        String temp;
        byte[] bytes;

        RandomAccessFile mixfile =  new RandomAccessFile(new File("mixfile"), "rw");

        int indFile = 0;
        for (int i = 0; i < TiffList.length; i++) {

            //Нужны только папки в место isFile() пишим isDirectory()
            //так указали не брать файлы с расширением "crc" и файлы содержащие "_SUCCESS"

            if (TiffList[i].isFile() && !TiffList[i].getName().endsWith(".crc")) {
                try {
                        /*RandomAccessFile file = new RandomAccessFile(new File(TiffPath.getPath()
                                + File.separator + TiffList[i].getName()), "r");
*/

                    RandomAccessFile file = new RandomAccessFile(new File(DirTiff + "/" + indFile + "partstr" + ".bt"), "r");
                    //RandomAccessFile file = new RandomAccessFile(new File(DirTiff + TiffList[i].getName()), "r");
                    mixfile.seek(mixfile.length());
                    file.seek(0);// /сдвигает указатель на указанное количество байтов вправо относительно начала.
                    bytes = new byte[(int) file.length()];
                    file.readFully(bytes, 0, (int) file.length());
                    mixfile.write(bytes);
                    //funcgetfloat(bytes);
                    bytes = null;
                    indFile++;
                    file.close();


                } catch (FileNotFoundException e) {
                    System.out.println(e.getMessage());
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }

            }

        }
        mixfile.close();
    }
    public static void funcgetfloat(byte[] _Bytes)
    {
        int i =0;

        while (i < _Bytes.length) {
            if (ByteBuffer.wrap(new byte[]{_Bytes[i], _Bytes[i + 1], _Bytes[i + 2], _Bytes[i + 3]})
                    .order(ByteOrder.BIG_ENDIAN).getFloat() > 0.0) {
                System.out.println("номер строки i=" + i +
                        " !Наименование байтов =  " + _Bytes[i] + ":" + _Bytes[i + 1] + ":" + _Bytes[i + 2] + ":" + _Bytes[i + 3] +
                        " !Полученное число = " +
                        ByteBuffer.wrap(
                                new byte[]{_Bytes[i], _Bytes[i + 1], _Bytes[i + 2], _Bytes[i + 3]})
                                .order(ByteOrder.BIG_ENDIAN).getFloat());
            }
            i = i+4;
        }

    }
    }

