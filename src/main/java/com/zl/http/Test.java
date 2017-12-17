package com.zl.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test {
	public static void main(String[] args) {
		String[] cmds = { "curl", "-k", "-X", "POST", "--data", "action=login&username=azkaban&password=@WSX4rfv",
				"https://101.71.61.221:8443" };
		ProcessBuilder pb = new ProcessBuilder(cmds);
		pb.redirectErrorStream(true);
		Process p;
		try {
			p = pb.start();
			BufferedReader br = null;
			String line = null;

			br = new BufferedReader(new InputStreamReader(p.getInputStream()));
			while ((line = br.readLine()) != null) {
				String[] lines = line.split(" ");
				for(String s : lines){
					if(s.contains(",")){
						System.out.println(s);
					}
				}
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
