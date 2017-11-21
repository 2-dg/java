package temps;

import java.util.ArrayList;
import java.util.Scanner;


public class StudentArrayList { 	
    public static void main(String [] args){
    	ArrayList<Student> as = new ArrayList<>();
    	as.add(new Student("A학생","A주소","A연락처"));
    	as.add(new Student("B학생","B주소","B연락처"));
    	as.add(new Student("C학생","C주소","C연락처"));
    	as.add(new Student("D학생","D주소","D연락처"));
    	Scanner scan = new Scanner(System.in);
    	for(;;) {
    	System.out.print("(1)학생 정보 추가. (2)학생 정보 검색. (3)학생 정보 삭제. (4)학생 정보 출력. (0)나가기 : ");
    	int operator = Integer.parseInt(scan.nextLine());
    	switch(operator) {
    	case 0:{
    		System.out.print("프로그램을 종료합니다.");
    		System.exit(0); break;
    	}
    	case 1:{
    		System.out.print("학생의 이름을 입력하세요 : ");
    		String name = scan.nextLine();
    		System.out.print("학생의 주소를 입력하세요 : ");
    		String address = scan.nextLine();
    		System.out.print("학생의 연락처를 입력하세요 : ");
    		String phonenum = scan.nextLine();
    		as.add(new Student(name,address,phonenum));
    		break;
    	}
    	case 2:{
    		System.out.print("검색할 학생의 이름을 입력하세요 : ");
    		String search = scan.nextLine();
    		for(Student st : as) {
    			if (st.getName().equals(search)) {
    				System.out.println("찾는 학생의 정보는 아래와 같습니다.");
    				System.out.println(st);
    			}
    		}
    		break;
    	}
    	case 3:{
    		System.out.println("저장된 학생들의 정보를 출력합니다.");
    		int count=0;
    		for(Student st : as) {
    			System.out.println(st+"\t(인덱스 : "+(count++)+")");
    		}
    		System.out.print("삭제할 학생의 인덱스를 입력하세요 : ");
    		int index = Integer.parseInt(scan.nextLine());
    		as.remove(index);
    		System.out.println("삭제가 완료되었습니다. 학생 목록을 다시 출력합니다.");
    		count=0;
    		for(Student st : as) {
    			System.out.println(st+"\t(인덱스 : "+(count++)+")");
    		}
    		break;
    	}
    	case 4:{
    		System.out.println("저장된 학생들의 정보를 출력합니다.");
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