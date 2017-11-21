public class Temp {
	public static void main(String[]args) {
		int num = new Integer(400);					//프라임타입은 타입 안에 넣을 수 없으므로 그에 해당하는 클래스들을
													//만들어서 넣을 수 있게 해둔 것.
		BoxInteger boxi = new BoxInteger(new Integer(10));
		boxi.setNumber(new Integer(20));
		Integer numberi = boxi.getNumber();			//형들을 맞추기 위해 전부 Integer를 넣음
		System.out.println(numberi);
		BoxDouble boxd = new BoxDouble(new Double(56.7));
		boxd.setNumber(new Double(32.4));
		Double numberd = boxd.getNumber();
		System.out.println(numberd);
		
		BoxObject boxo = new BoxObject(new Integer(30));
		Integer numbero = (Integer)boxo.getNumber();			//boxo는 오브젝트로 받았기 때문에 Integer보다
		System.out.println(numberi);							//슈퍼클래스이므로 강제형변환해야함
		BoxObject boxs = new BoxObject(new String("알ㄹ라라라라"));
		boxs.setNumber("아라리요");
		String boxsstr = (String)boxs.getNumber();
		System.out.println(boxsstr);
		
		BoxGeneric<Integer> boxg = new BoxGeneric/*<Integer>*/(new Integer(10));	//랩퍼클래스나 객체, 클래스를 써야 함
		System.out.println(boxg.getNumber());
		BoxGeneric<Double> boxgd = new BoxGeneric/*<Double>*/(new Double(30.2));
		//오류가 발생하진 않지만 제네릭이라는 걸 표시하기 위해 최소한 꺽쇠정도는 남겨두는 게 관행이다.
		Double te = boxgd.getNumber();							//제네릭은 이상 없음
		System.out.println(te);
	}
}