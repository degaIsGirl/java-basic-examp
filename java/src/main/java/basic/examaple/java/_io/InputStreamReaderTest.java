package basic.examaple.java._io;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class InputStreamReaderTest {

    /**
     * 直接用字符流读取GBK文件会乱码。默认使用utf-8读取文件
     * <p>
     * JDK11会有字符集的参数  jkd8不能指定字符编码只能使用字节流转字符流
     *
     * @throws Exception
     */
    @Test
    public void readByFileReader() throws Exception {
        FileReader fileReader = new FileReader("gbk.txt");
        int ch;
        while ((ch = fileReader.read()) != -1) {
            System.out.println((char) ch);
        }
        fileReader.close();
    }

    /**
     * 字节流=> 字符流，使用子节流读取，但是 会按指定的字符集转为字符流
     *
     * @throws IOException
     */
    @Test
    public void inputStreamReaderTest() throws IOException {
        InputStreamReader isr = new InputStreamReader(Files.newInputStream(Paths.get("gbk.txt")), "GBK");

        int ch;
        while ((ch = isr.read()) != -1) {
            System.out.println((char) ch);
        }
        isr.close();

    }

    /**
     * buffer读取一行
     */
    @Test
    public void inputStreamReaderReadLineTest() throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(Files.newInputStream(Paths.get("gbk11.txt")), "GBK"));
        String line ;
        while ((line =bufferedReader.readLine()) !=null){
            System.out.println(line);
        }

    }


}