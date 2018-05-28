package br.unisinos.redes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleReader {
	
	public String read() throws IOException{
		return new BufferedReader(
				new InputStreamReader(System.in))
					.readLine();
	}
	
}
