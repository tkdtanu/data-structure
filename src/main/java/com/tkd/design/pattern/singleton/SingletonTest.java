package com.tkd.design.pattern.singleton;

// No Eager initialization
// Need to handle MultiThreading scenario
class SingletonType1 {
	private static SingletonType1 instance;

	private SingletonType1() {
		System.out.println("SingleTon Object is Created of Type 1");
	}

	public static SingletonType1 getInstance() {
		if (instance == null) {
			synchronized (SingletonType1.class) {
				if (instance == null) {
					instance = new SingletonType1();
				}
			}
		}
		return instance;
	}
}

// Eager Initialization
// Memory wasted if the object is not used
// No need to handle separated for multithreading
class SingletonType2 {
	private static SingletonType2 instance = new SingletonType2();

	private SingletonType2() {
		System.out.println("SingleTon Object is Created of Type 2");
	}

	public static SingletonType2 getInstance() {
		return instance;
	}
}

// No Eager initialization
// No Need to handle for Multithreading
// This is the best Approach
// Inner static class will only be loaded at the time of calling
class SingletonType3 {
	private static class InstanceHolder {
		static {
			System.out.println("Inner Static class is loaded");
		}
		public static SingletonType3 instance = new SingletonType3();
	}

	private SingletonType3() {
		System.out.println("SingleTon Object is Created of Type 3");
	}

	public static SingletonType3 getInstance() {
		return InstanceHolder.instance;
	}
}

public class SingletonTest {

	public static void main(String[] args) {
		SingletonType1.getInstance();
		SingletonType1.getInstance();
		SingletonType2.getInstance();
		SingletonType2.getInstance();
		SingletonType3.getInstance();
		SingletonType3.getInstance();
	}

}
