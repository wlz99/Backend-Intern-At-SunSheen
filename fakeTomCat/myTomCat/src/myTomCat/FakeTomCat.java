package myTomCat;

import java.io.*;
import java.net.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

public class FakeTomCat {
	private static int port = 8080;
	
	public static void main(String args[]) {
		port = args.length > 0 ? Integer.parseInt(args[0]) : port;
		new FakeTomCat().start(port);
	}
	
	public void start(int port) {
		try {
			ServerSocket ss = new ServerSocket(port);
			System.out.println("Server start... listen [" + port + "]port");
			while(true) {
				Socket socket = ss.accept();
				System.out.println("New request...");
				ExecutorService pool = Executors.newFixedThreadPool(100);
				pool.submit(new HandlerRequestThread(socket));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

