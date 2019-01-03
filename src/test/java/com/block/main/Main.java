package com.block.main;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * 这是一个main函数入口，作简单测试使用
 * 
 * @author ajie
 *
 */
public class Main {

	public static void main(String[] args) throws IOException {
		/*		System.out.println("hello world");
				int j = 10;
				for (; j >= 0; j--) { // 如果满足 j>0;就会执行j--但是此次循环j依然为(j--)+1
					// 当j=0时，条件满足，执行j--,但是在此块j依然为0,在for外面j为-1
				}
				System.out.println(j); // -1
		*/
		/*		HttpClient client = new DefaultHttpClient();
				InputStream input = null;
				try {
					File f = new File("C:/Users/ajie/Desktop/view.jpg");
					input = new FileInputStream(f);
					byte[] byteArray = toByteArray(input);
					HttpPost post = new HttpPost(
							"http://api.weixin.qq.com/cv/ocr/idcard?type=photo&access_token=16_sMOKezkcQAPMM8Vw12ZieJQX8uvMWlcSagIeAFoIPO4v0wZI_qIxfUsdUDx7x-GaZnq2U7cCSRdFg6ntNyDh927Co17CPWk8fLl-XMRFzO4ZgjQKBKV4kCalPzoXL92XDi85nZJYiKNqD-9VNBThAHDUIA");
					// MultipartEntity multipartEntity = new MultipartEntity();
					// post.addHeader(new BasicHeader("Content-Type", ""));
					// multipartEntity.addPart("image", new InputStreamBody(input,
					// "aaa"));
					byte[] bytes = "helloworld".getBytes();
					ByteArrayInputStream bais = new ByteArrayInputStream(byteArray);
					InputStreamEntity entity = new InputStreamEntity(bais, byteArray.length);
					post.setEntity(entity);
					entity.setContentType("binary/octet-stream");
					HttpResponse res = client.execute(post);
					String str = EntityUtils.toString(res.getEntity(), "utf-8");
					System.out.println(str);

				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					try {
						input.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}*/

		String str = "我在中国，中国爱我,我在中国，中国爱我我在中国，中国爱我我在中国\r\n，中国爱我我在中国，中国爱我我在中国，中国爱我我在中国，中国爱我";
		byte[] strs = str.getBytes();
		System.out.println(strs.length);
		ByteArrayInputStream in = new ByteArrayInputStream(strs);
		/*
		 * 会乱码
		 * int n = 0;
		byte buf[] = new byte[128];
		StringBuilder sb = new StringBuilder();
		try {
			while (-1 != (n = in.read(buf))) {
				sb.append(new String(buf, 0, n, "utf-8"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(sb.toString());*/

		Reader reader = new InputStreamReader(in);
		BufferedReader br = new BufferedReader(reader);
		StringBuilder sb = new StringBuilder();
		String line = "";
		while (null != (line = br.readLine())) {
			sb.append(line);
			sb.append("\r\n");
		}
		System.out.println(sb.toString());
	}

	public static byte[] toByteArray(InputStream input) throws IOException {
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		byte[] buffer = new byte[4096];
		int n = 0;
		while (-1 != (n = input.read(buffer))) {
			output.write(buffer, 0, n);
		}
		return output.toByteArray();
	}
}
