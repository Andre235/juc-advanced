package com.geek.juc;

import lombok.SneakyThrows;

import java.io.PipedReader;
import java.io.PipedWriter;

/**
 * @Author: geek
 * @Date: 2020/10/7 12:39
 * @Description: 线程之间传输数据用管道流实现
 */
public class PipedStreamDemo {

    @SneakyThrows
    public static void main(String[] args) {
        PipedWriter writer = new PipedWriter();
        PipedReader reader = new PipedReader();
        // 将输出流和输入流连接，否则会抛出IO异常
        writer.connect(reader);
        new Thread(new PrintThread(reader)).start();

        int receive = 0;
        try {
            while ((receive = System.in.read()) != -1){
                writer.write(receive);
            }
        } finally {
            writer.close();
        }
    }

    static class PrintThread implements Runnable{
        PipedReader  reader;
        public PrintThread(PipedReader reader) {
            this.reader = reader;
        }

        @Override
        @SneakyThrows
        public void run() {
            int receive = 0;
            while ((receive = reader.read()) != -1){
                System.out.print( (char) receive);
            }
        }
    }
}
