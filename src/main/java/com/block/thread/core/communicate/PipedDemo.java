package com.block.thread.core.communicate;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * PipedInputStream和PipedOutputStream是用于不同的线程间直接通讯的流，一个线程吧数据发送到输出管道，
 * 另一个线程从输入管道读取数据
 * ，两个线程的管道流需要使用PipeInputStream.connect(PipedOutputStream)或PipedOutputStream
 * .connect(PipeInputStream)进行关联
 * 
 * @author ajie
 *
 */
public class PipedDemo {

	public static void main(String[] args) throws IOException, InterruptedException {
		final PipedOutputStream out = new PipedOutputStream();
		final PipedInputStream in = new PipedInputStream();
		out.connect(in);
		new Thread(new Runnable() {
			public void run() {
				System.out.println("write");
				try {
					for (int i = 0; i < 300; i++) {
						String outData = "" + i + 1;
						// 吧数据写到输出流，跟该输出流绑定的输入流就会有数据
						out.write(outData.getBytes());
					}
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					try {
						out.close();
					} catch (IOException e) {
					}
				}
			}
		}).start();
		Thread.sleep(1000);
		new Thread(new Runnable() {
			public void run() {
				System.out.println("read");
				try {
					byte[] buf = new byte[20];
					// 与该输入流绑定的输出流将数据读入输入流后，输入流就有数据了，再读出来
					int len = in.read(buf);
					while (-1 != len) {
						System.out.println(new String(buf, 0, len));
						len = in.read(buf);
					}
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					try {
						in.close();
					} catch (IOException e) {
					}
				}
			}
		}).start();
	}
}
