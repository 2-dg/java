package join;

public class JoinExample {
	public static void main(String[] args) {
		SumThread sumThread = new SumThread();
		sumThread.start();
		try {
			sumThread.join();						//이렇게 하면 sumThread가 끝나야지 남은 mainThread가 실행
													//이걸 이용하면 스레드를 1~10순서로 올바르게 찍을 수 있을듯
		} catch (InterruptedException e) {
		}
		//트라이 캐치부분을 주석처리하면 가끔씩 0이 출력되는데 그 이유는 조인관계를 설정하지 않으면
		//CPU 스케쥴러가 랜덤하게 메인스레드와 섬스레드의 우선순위를 설정하기 때문임.
		System.out.println("1~100 합: " + sumThread.getSum());
		System.out.println("메인스레드 종료");
	}
}

