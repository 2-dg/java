import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Scanner;


public class AppMain {
	//���� 1000ms = 1��
	private static final int KEY_DELAY = 20;
	//20ms = 0.02��
	private static final int MOVE_DELAY = 2000;
	//1000ms = 1��
	private static final int PAGE_DELAY = 1000;
	//1000ms = 1��
	private static Scanner scanner = new Scanner(System.in);
	
	private static Robot robot=null;
	
	public static void main(String[] args) throws AWTException, InterruptedException {
		robot = new Robot();
		String account = "110359573890";
		String password = "abracadabra11";
		
		System.out.print("�ݺ��� �ܾ� �Է�(����.�빮�ڸ� �Է��ص� �ҹ��ڷ� ��ȯ�˴ϴ�.) : ");
		String input = scanner.nextLine();
		
		System.out.print("�ݺ����� �Է�(���ۼ�) : ");
		int startNumber = Integer.parseInt(scanner.nextLine());
		
		System.out.print("�ݺ����� �Է�(����) : ");
		int finishNumber = Integer.parseInt(scanner.nextLine());
		
		System.out.print("�󸶸��� �ѹ��� �����ұ��?(�ʴ���) : ");
		int repeatTime = Integer.parseInt(scanner.nextLine());
		
		System.out.print("���� �����ٰ� �����ұ��? : ");
		int waitTime = Integer.parseInt(scanner.nextLine());

		System.out.println("=================================================");
		
		String word = input.toLowerCase();
		System.out.println("�ݺ��� �ܾ� : " + word);
		System.out.println("�ݺ��� Ƚ�� : " +(finishNumber-startNumber+1));
		System.out.println("���� ��� �ð� : "+waitTime+"��");		
		
		for(int i=0;i<waitTime;i++) {
			System.out.println(waitTime-i+"�� �Ŀ� ���۵˴ϴ�.");
			Thread.sleep(1000);
		}
		System.out.println("��ũ�ΰ� ����˴ϴ�.");
		
		for(;startNumber<=finishNumber;startNumber++) {
			String outWord=word+startNumber;
			robot.delay(PAGE_DELAY);
			botMoveAndClickMethod(2200, 200);
			//�α���/ȸ������ Ŭ��
			robot.delay(PAGE_DELAY);
			botMoveAndClickMethod(1400, 1400);
			//����������� Ŭ��
			botMoveAndClickMethod(1400, 830);
			//�̸��Ϸ� �����ϱ� Ŭ��
			botMoveAndClickMethod(1400, 662);
			//���̵� �Է¶� Ŭ��
			botWriteMethod(outWord);
			//���̵� �Է�
			
			botTabMethod();
			botWriteMethod(outWord);
			emailWriteMethos();
			botTabMethod();
			botWriteMethod(outWord);
			emailWriteMethos();
			//������� �̸��� �Է�
			
			botTabMethod();
			botWriteMethod(password);
			botTabMethod();
			botWriteMethod(password);
			//������� ��й�ȣ �Է�
			
			botMoveAndClickMethod(1114, 1180);
			//üũ�ڽ� ����
			botMoveAndClickMethod(1370, 1324);
			//ȸ������ ��ư Ŭ��
			
			robot.delay(PAGE_DELAY);			
			botMoveAndClickMethod(2400, 100);
			robot.delay(PAGE_DELAY);
			//���ã�� Ŭ��
			botMoveAndClickMethod(2400, 600);
			robot.delay(PAGE_DELAY);
			//�ִϸ����Ǿ� Ŭ��
			botMoveAndClickMethod(1910, 1720);
			//�о��ֱ� Ŭ��
			botMoveAndClickMethod(600, 400);
			robot.delay(PAGE_DELAY);
			//�Ŀ��ϱ� Ŭ��
			botMoveAndClickMethod(532, 346);
			robot.delay(PAGE_DELAY);
			//ȭ��ǥ Ŭ��
			botMoveAndClickMethod(500, 750);
			//������� Ŭ��
			botMoveAndClickMethod(600, 600);
			//���ΰ��� Ŭ��
			botMoveAndClickMethod(300, 530);
			//���� Ŭ��
			botMoveAndClickMethod(260, 860);
			//�������� Ŭ��
			botMoveAndClickMethod(350, 764);
			
			botWriteMethod(account);
			botTabMethod();
			robot.delay(300);
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.delay(300);
			robot.keyPress(KeyEvent.VK_V);
			robot.delay(300);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyRelease(KeyEvent.VK_V);			
			robot.delay(300);			
			botTabMethod();
			botWriteMethod("911112");
			botTabMethod();
			botWriteMethod("01047848691");
			botMoveAndClickMethod(200, 1350);
			robot.delay(PAGE_DELAY);
			robot.delay(PAGE_DELAY);
			botMoveAndClickMethod(1200, 1500);
			robot.delay(PAGE_DELAY);
			robot.delay(PAGE_DELAY);
			robot.delay(PAGE_DELAY);
			robot.delay(PAGE_DELAY);
			botMoveAndClickMethod(1426, 1470);
			robot.delay(PAGE_DELAY);
			robot.delay(PAGE_DELAY);
			botMoveAndClickMethod(2232, 202);
			robot.delay(PAGE_DELAY);
			robot.delay(PAGE_DELAY);
			botMoveAndClickMethod(400, 1150);
			robot.delay(PAGE_DELAY);
			robot.delay(PAGE_DELAY);
			Thread.sleep(repeatTime*1000);
		}//end of Main for
	}

	private static void botMoveAndClickMethod(int x, int y) {
		robot.mouseMove(x, y);
		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		robot.delay(MOVE_DELAY);
	}

	private static void botTabMethod() {
		robot.keyPress(KeyEvent.VK_TAB);
		robot.keyRelease(KeyEvent.VK_TAB);
		robot.delay(KEY_DELAY);
	}

	private static void emailWriteMethos() {
		robot.keyPress(KeyEvent.VK_SHIFT);
		robot.delay(KEY_DELAY);
		robot.keyPress(KeyEvent.VK_2);
		robot.delay(KEY_DELAY);
		robot.keyRelease(KeyEvent.VK_2);
		robot.keyRelease(KeyEvent.VK_SHIFT);		
		robot.delay(KEY_DELAY);
		robot.keyPress(KeyEvent.VK_N);
		robot.keyRelease(KeyEvent.VK_N);
		robot.delay(KEY_DELAY);
		robot.keyPress(KeyEvent.VK_A);
		robot.keyRelease(KeyEvent.VK_A);
		robot.delay(KEY_DELAY);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.delay(KEY_DELAY);
		robot.keyPress(KeyEvent.VK_E);
		robot.keyRelease(KeyEvent.VK_E);
		robot.delay(KEY_DELAY);
		robot.keyPress(KeyEvent.VK_R);
		robot.keyRelease(KeyEvent.VK_R);
		robot.delay(KEY_DELAY);
		robot.keyPress(KeyEvent.VK_PERIOD);
		robot.keyRelease(KeyEvent.VK_PERIOD);
		robot.delay(KEY_DELAY);
		robot.keyPress(KeyEvent.VK_C);
		robot.keyRelease(KeyEvent.VK_C);
		robot.delay(KEY_DELAY);
		robot.keyPress(KeyEvent.VK_O);
		robot.keyRelease(KeyEvent.VK_O);
		robot.delay(KEY_DELAY);
		robot.keyPress(KeyEvent.VK_M);
		robot.keyRelease(KeyEvent.VK_M);
		robot.delay(KEY_DELAY);
	}

	private static void botWriteMethod(String outWord) {
		for(int j=0;j<outWord.length();j++) {
			if(outWord.charAt(j)=='a') {
				robot.keyPress(KeyEvent.VK_A);
				robot.keyRelease(KeyEvent.VK_A);
				robot.delay(KEY_DELAY);
			}
			if(outWord.charAt(j)=='b') {
				robot.keyPress(KeyEvent.VK_B);
				robot.keyRelease(KeyEvent.VK_B);
				robot.delay(KEY_DELAY);
			}
			if(outWord.charAt(j)=='c') {
				robot.keyPress(KeyEvent.VK_C);
				robot.keyRelease(KeyEvent.VK_C);
				robot.delay(KEY_DELAY);
			}
			if(outWord.charAt(j)=='d') {
				robot.keyPress(KeyEvent.VK_D);
				robot.keyRelease(KeyEvent.VK_D);
				robot.delay(KEY_DELAY);
			}
			if(outWord.charAt(j)=='e') {
				robot.keyPress(KeyEvent.VK_E);
				robot.keyRelease(KeyEvent.VK_E);
				robot.delay(KEY_DELAY);
			}
			if(outWord.charAt(j)=='f') {
				robot.keyPress(KeyEvent.VK_F);
				robot.keyRelease(KeyEvent.VK_F);
				robot.delay(KEY_DELAY);
			}
			if(outWord.charAt(j)=='g') {
				robot.keyPress(KeyEvent.VK_G);
				robot.keyRelease(KeyEvent.VK_G);
				robot.delay(KEY_DELAY);
			}
			if(outWord.charAt(j)=='h') {
				robot.keyPress(KeyEvent.VK_H);
				robot.keyRelease(KeyEvent.VK_H);
				robot.delay(KEY_DELAY);
			}
			if(outWord.charAt(j)=='i') {
				robot.keyPress(KeyEvent.VK_I);
				robot.keyRelease(KeyEvent.VK_I);
				robot.delay(KEY_DELAY);
			}
			if(outWord.charAt(j)=='j') {
				robot.keyPress(KeyEvent.VK_J);
				robot.keyRelease(KeyEvent.VK_J);
				robot.delay(KEY_DELAY);
			}
			if(outWord.charAt(j)=='k') {
				robot.keyPress(KeyEvent.VK_K);
				robot.keyRelease(KeyEvent.VK_K);
				robot.delay(KEY_DELAY);
			}
			if(outWord.charAt(j)=='l') {
				robot.keyPress(KeyEvent.VK_L);
				robot.keyRelease(KeyEvent.VK_L);
				robot.delay(KEY_DELAY);
			}
			if(outWord.charAt(j)=='m') {
				robot.keyPress(KeyEvent.VK_M);
				robot.keyRelease(KeyEvent.VK_M);
				robot.delay(KEY_DELAY);
			}
			if(outWord.charAt(j)=='n') {
				robot.keyPress(KeyEvent.VK_N);
				robot.keyRelease(KeyEvent.VK_N);
				robot.delay(KEY_DELAY);
			}
			if(outWord.charAt(j)=='o') {
				robot.keyPress(KeyEvent.VK_O);
				robot.keyRelease(KeyEvent.VK_O);
				robot.delay(KEY_DELAY);
			}
			if(outWord.charAt(j)=='p') {
				robot.keyPress(KeyEvent.VK_P);
				robot.keyRelease(KeyEvent.VK_P);
				robot.delay(KEY_DELAY);
			}
			if(outWord.charAt(j)=='q') {
				robot.keyPress(KeyEvent.VK_Q);
				robot.keyRelease(KeyEvent.VK_Q);
				robot.delay(KEY_DELAY);
			}
			if(outWord.charAt(j)=='r') {
				robot.keyPress(KeyEvent.VK_R);
				robot.keyRelease(KeyEvent.VK_R);
				robot.delay(KEY_DELAY);
			}
			if(outWord.charAt(j)=='s') {
				robot.keyPress(KeyEvent.VK_S);
				robot.keyRelease(KeyEvent.VK_S);
				robot.delay(KEY_DELAY);
			}
			if(outWord.charAt(j)=='t') {
				robot.keyPress(KeyEvent.VK_T);
				robot.keyRelease(KeyEvent.VK_T);
				robot.delay(KEY_DELAY);
			}
			if(outWord.charAt(j)=='u') {
				robot.keyPress(KeyEvent.VK_U);
				robot.keyRelease(KeyEvent.VK_U);
				robot.delay(KEY_DELAY);
			}
			if(outWord.charAt(j)=='v') {
				robot.keyPress(KeyEvent.VK_V);
				robot.keyRelease(KeyEvent.VK_V);
				robot.delay(KEY_DELAY);
			}
			if(outWord.charAt(j)=='w') {
				robot.keyPress(KeyEvent.VK_W);
				robot.keyRelease(KeyEvent.VK_W);
				robot.delay(KEY_DELAY);
			}
			if(outWord.charAt(j)=='x') {
				robot.keyPress(KeyEvent.VK_X);
				robot.keyRelease(KeyEvent.VK_X);
				robot.delay(KEY_DELAY);
			}
			if(outWord.charAt(j)=='y') {
				robot.keyPress(KeyEvent.VK_Y);
				robot.keyRelease(KeyEvent.VK_Y);
				robot.delay(KEY_DELAY);
			}
			if(outWord.charAt(j)=='z') {
				robot.keyPress(KeyEvent.VK_Z);
				robot.keyRelease(KeyEvent.VK_Z);
				robot.delay(KEY_DELAY);
			}
			if(outWord.charAt(j)=='0') {
				robot.keyPress(KeyEvent.VK_0);
				robot.keyRelease(KeyEvent.VK_0);
				robot.delay(KEY_DELAY);
			}
			if(outWord.charAt(j)=='1') {
				robot.keyPress(KeyEvent.VK_1);
				robot.keyRelease(KeyEvent.VK_1);
				robot.delay(KEY_DELAY);
			}
			if(outWord.charAt(j)=='2') {
				robot.keyPress(KeyEvent.VK_2);
				robot.keyRelease(KeyEvent.VK_2);
				robot.delay(KEY_DELAY);
			}
			if(outWord.charAt(j)=='3') {
				robot.keyPress(KeyEvent.VK_3);
				robot.keyRelease(KeyEvent.VK_3);
				robot.delay(KEY_DELAY);
			}
			if(outWord.charAt(j)=='4') {
				robot.keyPress(KeyEvent.VK_4);
				robot.keyRelease(KeyEvent.VK_4);
				robot.delay(KEY_DELAY);
			}
			if(outWord.charAt(j)=='5') {
				robot.keyPress(KeyEvent.VK_5);
				robot.keyRelease(KeyEvent.VK_5);
				robot.delay(KEY_DELAY);
			}
			if(outWord.charAt(j)=='6') {
				robot.keyPress(KeyEvent.VK_6);
				robot.keyRelease(KeyEvent.VK_6);
				robot.delay(KEY_DELAY);
			}
			if(outWord.charAt(j)=='7') {
				robot.keyPress(KeyEvent.VK_7);
				robot.keyRelease(KeyEvent.VK_7);
				robot.delay(KEY_DELAY);
			}
			if(outWord.charAt(j)=='8') {
				robot.keyPress(KeyEvent.VK_8);
				robot.keyRelease(KeyEvent.VK_8);
				robot.delay(KEY_DELAY);
			}
			if(outWord.charAt(j)=='9') {
				robot.keyPress(KeyEvent.VK_9);
				robot.keyRelease(KeyEvent.VK_9);
				robot.delay(KEY_DELAY);
			}				
		}			
	}
}