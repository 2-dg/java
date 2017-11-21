package yield;

public class YieldExample {
	public static void main(String[] args) throws InterruptedException {
		ThreadA threadA = new ThreadA();
		ThreadB threadB = new ThreadB();	
		threadA.start();		
		threadB.start();				//A와 B가 같이 찍힘
		
		Thread.sleep(3000);				//메인스레드를 3초동안 정지시킴. AB는 독립스레드이므로 게속 실행.
		threadB.work=false;				//A만 찍힘
		
		Thread.sleep(3000);				//3초동안 찍힘
		threadB.work=true;				
		threadA.work=false;				//B만 찍힘
		
		Thread.sleep(3000);				//3초동안 찍힘
		threadA.stop=true;
		threadB.stop=true;
		
		System.out.println("Main is over");

	}
}
