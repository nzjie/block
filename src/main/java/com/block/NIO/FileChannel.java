package com.block.NIO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;

public class FileChannel {

	public static void main(String[] args) {
		try {
			FileInputStream is = new FileInputStream(new File(
					"D:\\wifipw\\wifizidian.txt"));
/*			FileInputStream is = new FileInputStream(new File(
					"D:\\wifipw\\整理wifi密码字典\\一千四百万密码字典.txt"));
*/			java.nio.channels.FileChannel channel = is.getChannel();
			ByteBuffer buf = ByteBuffer.allocate(1024);
			buf.clear();
			channel.read(buf);
			buf.flip();
			byte[] bytes = new byte[128];
			buf.get(bytes);
			System.out.println(new String(bytes, "utf-8"));
			System.out.println("=======================");
			buf.clear();
			channel.read(buf);
			buf.flip();
			buf.get(bytes);
			System.out.println(new String(bytes, "utf-8"));
			is.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
