public class Temp {
	public static void main(String[]args) {
		int num = new Integer(400);					//������Ÿ���� Ÿ�� �ȿ� ���� �� �����Ƿ� �׿� �ش��ϴ� Ŭ��������
													//���� ���� �� �ְ� �ص� ��.
		BoxInteger boxi = new BoxInteger(new Integer(10));
		boxi.setNumber(new Integer(20));
		Integer numberi = boxi.getNumber();			//������ ���߱� ���� ���� Integer�� ����
		System.out.println(numberi);
		BoxDouble boxd = new BoxDouble(new Double(56.7));
		boxd.setNumber(new Double(32.4));
		Double numberd = boxd.getNumber();
		System.out.println(numberd);
		
		BoxObject boxo = new BoxObject(new Integer(30));
		Integer numbero = (Integer)boxo.getNumber();			//boxo�� ������Ʈ�� �޾ұ� ������ Integer����
		System.out.println(numberi);							//����Ŭ�����̹Ƿ� ��������ȯ�ؾ���
		BoxObject boxs = new BoxObject(new String("�ˤ������"));
		boxs.setNumber("�ƶ󸮿�");
		String boxsstr = (String)boxs.getNumber();
		System.out.println(boxsstr);
		
		BoxGeneric<Integer> boxg = new BoxGeneric/*<Integer>*/(new Integer(10));	//����Ŭ������ ��ü, Ŭ������ ��� ��
		System.out.println(boxg.getNumber());
		BoxGeneric<Double> boxgd = new BoxGeneric/*<Double>*/(new Double(30.2));
		//������ �߻����� ������ ���׸��̶�� �� ǥ���ϱ� ���� �ּ��� ���������� ���ܵδ� �� �����̴�.
		Double te = boxgd.getNumber();							//���׸��� �̻� ����
		System.out.println(te);
	}
}