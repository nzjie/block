package com.block.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author niezhenjie
 * 
 */
public class SimpleRobot {
	public static void main(String[] args) {
		URL url = null;
		URLConnection urlConnetcion = null;
		BufferedReader br = null;
		PrintWriter pw = null;
		String regex = "https://[\\w+\\.?/?]+\\.[A-Za-z]+";
		Pattern pattern =  Pattern.compile(regex);
		
		try {
			url = new URL("http://www.qq.com/");
			urlConnetcion = url.openConnection();
			pw = new PrintWriter(new File("/home/mitnick/pachongdata.txt"));
			br = new BufferedReader(new InputStreamReader(urlConnetcion.getInputStream()));
			String buf  = null;
			while((buf=br.readLine())!=null){
				Matcher res = pattern.matcher(buf);
				if(res.find()){
					pw.write(res.group()+"\r\n");
				}
			}
			System.out.println("爬取成功！");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			try {
				pw.close();
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
