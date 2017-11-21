package temps;

import java.util.ArrayList;
import java.util.Scanner;


public class StudentArrayList { 	
    public static void main(String [] args){
    	ArrayList<Student> as = new ArrayList<>();
    	as.add(new Student("A�л�","A�ּ�","A����ó"));
    	as.add(new Student("B�л�","B�ּ�","B����ó"));
    	as.add(new Student("C�л�","C�ּ�","C����ó"));
    	as.add(new Student("D�л�","D�ּ�","D����ó"));
    	Scanner scan = new Scanner(System.in);
    	for(;;) {
    	System.out.print("(1)�л� ���� �߰�. (2)�л� ���� �˻�. (3)�л� ���� ����. (4)�л� ���� ���. (0)������ : ");
    	int operator = Integer.parseInt(scan.nextLine());
    	switch(operator) {
    	case 0:{
    		System.out.print("���α׷��� �����մϴ�.");
    		System.exit(0); break;
    	}
    	case 1:{
    		System.out.print("�л��� �̸��� �Է��ϼ��� : ");
    		String name = scan.nextLine();
    		System.out.print("�л��� �ּҸ� �Է��ϼ��� : ");
    		String address = scan.nextLine();
    		System.out.print("�л��� ����ó�� �Է��ϼ��� : ");
    		String phonenum = scan.nextLine();
    		as.add(new Student(name,address,phonenum));
    		break;
    	}
    	case 2:{
    		System.out.print("�˻��� �л��� �̸��� �Է��ϼ��� : ");
    		String search = scan.nextLine();
    		for(Student st : as) {
    			if (st.getName().equals(search)) {
    				System.out.println("ã�� �л��� ������ �Ʒ��� �����ϴ�.");
    				System.out.println(st);
    			}
    		}
    		break;
    	}
    	case 3:{
    		System.out.println("����� �л����� ������ ����մϴ�.");
    		int count=0;
    		for(Student st : as) {
    			System.out.println(st+"\t(�ε��� : "+(count++)+")");
    		}
    		System.out.print("������ �л��� �ε����� �Է��ϼ��� : ");
    		int index = Integer.parseInt(scan.nextLine());
    		as.remove(index);
    		System.out.println("������ �Ϸ�Ǿ����ϴ�. �л� ����� �ٽ� ����մϴ�.");
    		count=0;
    		for(Student st : as) {
    			System.out.println(st+"\t(�ε��� : "+(count++)+")");
    		}
    		break;
    	}
    	case 4:{
    		System.out.println("����� �л����� ������ ����մϴ�.");
    		for(Student st : as) {
    			System.out.println(st);
    		}
    		break;
    	}
    	default:{
    		
    	}
    	
    	}
    	
    	
    	
    	
    	}
    	
    	
    }
}