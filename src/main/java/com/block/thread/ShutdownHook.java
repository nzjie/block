package com.block.thread;

public class ShutdownHook {

	public static void main(String[] args) {
		HookIns hook = new HookIns();
		Runtime.getRuntime().addShutdownHook(hook);
		int i = 0;
		while (true) {
			try {
				i++;
				Thread.sleep(1000);
				if (i == 5) {
					//Runtime.getRuntime().halt(0); 不会执行hook线程
					System.exit(-1); //会执行
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}

	static class HookIns extends Thread {
		@Override
		public void run() {
			System.out.println("执行shutdownHook方法");
		}
	}

}
