package com.block.socket;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) {
		ServerSocket ss = null;
		try {
			ss = new ServerSocket(9999);
			Socket s = ss.accept();
			InputStream inputStream = s.getInputStream();
			int len = 8;
			byte[] buf = new byte[len];
			StringBuilder sb = new StringBuilder();
			int n = 0;
			do {
				n = inputStream.available();
				inputStream.read(buf, 0, len);
				sb.append(new String(buf, 0, Math.min(n, len), "utf-8"));
			} while (n > len);
			System.out.println(sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				ss.close();
			} catch (IOException e) {
			}
		}
	}
}
