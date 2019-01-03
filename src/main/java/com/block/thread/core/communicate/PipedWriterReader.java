package com.block.thread.core.communicate;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

public class PipedWriterReader {

	public static void main(String[] args) throws IOException, InterruptedException {
		final PipedWriter writer = new PipedWriter();
		final PipedReader reader = new PipedReader();
		writer.connect(reader);
		new Thread(new Runnable() {
			public void run() {
				try {
					// for (int i = 0; i < 300; i++) { 
					// String data = "" + (i + 1);
					String data = "我爱中国，中国爱我，爱我中国";
					writer.write(data);
					// }
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					try {
						writer.close();
					} catch (IOException e) {
					}
				}
			}
		}).start();

		new Thread(new Runnable() {
			public void run() {
				char[] cbuf = new char[20];
				try {
					int len = reader.read(cbuf);
					while (-1 != len) {
						System.out.println(new String(cbuf, 0, len));
						len = reader.read(cbuf);
					}
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					try {
						reader.close();
					} catch (IOException e) {
					}
				}

			}
		}).start();
	}
}
