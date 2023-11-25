package basic.examaple.java._io;

import org.junit.jupiter.api.Test;

import java.io.*;

/**
 * 字节流: 读写的单位是字节
 *
 * 字符流: 读写的单位是字符
 */
public class FileOutputStreamTest {

    /**
     *1）字节输出流细节：
     *     1.创建字节输出流对象
     *           细节1：参数是字符串表示的路径或者是File对象都可以
     *           细节2：如果文件不存在会创建新的一个文件，但是要保证父级别路径是存在的
     *           细节3：如果 文件已经存在 ，则会清空文件 不想清空 需要加：boolean append参数
     *
     *     2.写数据
     *         细节：write 方法参数是整数，但是实际上写到本地文件中的是整数在ASCII上对应的字符
     *          97--->a
     *          怎么写出97?
     *           ASCII表https://www.renrendoc.com/paper/107647011.html
     *     3.释放资源
     *        每次使用完流后都要释放资源：不释放的后果：文件一直会占用
     * 2） 写数据的三种方式
     *         1.void write(int b)  一次写一个子节数据
     *         2.void write(byte[] b) 一次写一个子节数组
     *         3.void write(byte[] b,int off,int len) 一次写一个子节数组的部分数据  off 起始  len长度
     *
     * 3）换行写
     *          win:      \r\n
     *          linux:   \n
     *          mac:      \r
     *          System.lineSeparator()
     *
     * @throws Exception
     */
    @Test
    public void fileOutputStreamTest() throws Exception {

        //让程序和文件建立连接
        FileOutputStream fileOutputStream =new FileOutputStream("src/1.txt");

        //写入字节
        fileOutputStream.write(79);

        //写入字节数组
        String data = "fuck";
        fileOutputStream.write(data.getBytes());

        //关闭通道
        fileOutputStream.close();

    }

    /**
     * 换行
     * @throws Exception
     */
    @Test
    public void fileOutputStreamChangeLineTest()throws Exception{
        FileOutputStream fileOutputStream =new FileOutputStream("src/3.txt");
        fileOutputStream.write("bbbb".getBytes());
        fileOutputStream.write(System.lineSeparator().getBytes());
        fileOutputStream.write("nnn".getBytes());
        fileOutputStream.write("ccc".getBytes());
        fileOutputStream.close();
    }


    /**
     * read()
     * 1.默认也是一个子节一个子节的读取，如果遇到中文就会读取多个
     * 2.在读取之后，方法的底层会解码并且转为十进制
     * 3.这个十进制也是字符集的数字
     * 4.如果是英文：文件里面的二进制数据 0110 0001 read读取后解码为97
     *   如果是中文：文件里面的二进制数据 11100110 10110001 10001001 read读取后解码为27771
     *
     * @throws Exception
     */
    @Test
    public void fileReader() throws Exception{
        FileReader fileReader =new FileReader("1.txt");
        int ch;
        while ((ch =fileReader.read()) !=-1){
            System.out.println(ch);
            System.out.println((char) ch);
        }
        fileReader.close();

        System.out.println((char) 25108);
        System.out.println((int)'喔');
    }

    /**
     * 读取数组 ：读取，解码 ，强转 之后的数据放在数组中 ，可以debug看下
     * void read(char[] buf)
     * @throws Exception
     */
    @Test
    public void fileReaderCharArr() throws Exception{
        FileReader fileReader =new FileReader("1.txt");
        char [] chars =new char[2];
        int len;
        while ((len =fileReader.read(chars)) !=-1){
            System.out.print(new String(chars,0,len));
        }
        fileReader.close();
    }

    /**
     * 书写细节：
     *     1.创建字符输出流对象
     *          细节1：参数是字符串的路径或者file对象
     *          细节2：如果文件不存在会创建新的文件，但是要保证父路径存在
     *          细节3：如果文件存在会清空文件，如果不想清空就要打开续写开关
     *
     *      2.写数据
     *         细节1:如果write方法是整数，但是实际是整数在字符集对应的字符
     *
     *      3.释放资源
     *
     *       '我' 25105
     */
    @Test
    public void fileWriterTest() throws IOException {

        FileWriter fw =new FileWriter("1.txt");
        fw.write(25105);
        fw.close();
        //文件长度
        File file =new File("1.txt");
        System.out.println(file.length());
    }

    @Test
    public void fileWriterArrTest() throws IOException {

        FileWriter fw =new FileWriter("1.txt");
        char [] chars ={'我','你'};
        fw.write(chars);

        fw.write("中国");
        fw.close();
    }
}