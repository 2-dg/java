package wait_notify;

public class WorkObject {
	public synchronized void methodA() throws InterruptedException {
		System.out.println("ThreadA가 methodA()를 실행했습니다.");
		notify();
		wait();			
		//싱크로나이즈선언(동기화처리)하면 괄호까지 임계영역이 설정되는 거임
		//임계영역 : 함수가 완료되는 동안 간섭 불가
	}
	public synchronized void methodB() throws InterruptedException {
		System.out.println("ThreadB가 methodB()를 실행했습니다.");
		notify();
		wait();
	}
}