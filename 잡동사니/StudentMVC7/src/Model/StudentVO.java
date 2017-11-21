package Model;

public class StudentVO {
//	√—15∞≥¿”
	private int no;
	
	private String name;
	private String year;
	private String ban;
	private String gender;
	
	private int korean;
	private int english;
	private int math;
	private int sic;
	private int soc;
	private int music;
	
	private int total;
	private double avg;
	
	private String register;
	private String filename=null;

	public StudentVO() {

	}

	public StudentVO(String name, String year, String ban, String gender, int korean, int english, int math, int sic,
			int soc, int music, int total, double avg, String filename) {
		super();
		
		this.name = name;
		this.year = year;
		this.ban = ban;
		this.gender = gender;
		
		this.korean = korean;
		this.english = english;
		this.math = math;
		this.sic = sic;
		this.soc = soc;
		this.music = music;
		
		this.total = total;
		this.avg = avg;
		this.filename = filename;
		
		System.out.println(this.name);
		System.out.println(this.year);
		System.out.println(this.ban);
		System.out.println(this.gender);
		System.out.println(this.filename);
	}

	public StudentVO(String name, String year, String ban, String gender, int korean, int english, int math, int sic,
			int soc, int music, int total, double avg) {
		super();
		this.name = name;
		this.year = year;
		this.ban = ban;
		this.gender = gender;
		
		this.korean = korean;
		this.english = english;
		this.math = math;
		this.sic = sic;
		this.soc = soc;
		this.music = music;
		
		this.total = total;
		this.avg = avg;

	}

	public StudentVO(int no, String name, String year, String ban, String gender, int korean, int english, int math,
			int sic, int soc, int music, int total, double avg) {
		super();
		
		this.no = no;
		
		this.name = name;
		this.year = year;
		this.ban = ban;
		this.gender = gender;
		
		this.korean = korean;
		this.english = english;
		this.math = math;
		this.sic = sic;
		this.soc = soc;
		this.music = music;
		
		this.total = total;
		this.avg = avg;
	}

	public StudentVO(int no, String name, String year, String ban, String gender, int korean, int english, int math,
			int sic, int soc, int music, int total, double avg, String register, String filename) {
		super();
		
		this.no = no;
		
		this.name = name;
		this.year = year;
		this.ban = ban;
		this.gender = gender;
		
		this.korean = korean;
		this.english = english;
		this.math = math;
		this.sic = sic;
		this.soc = soc;
		this.music = music;
		this.total = total;
		this.avg = avg;
		
		this.register = register;
		this.filename = filename;
	}

	public StudentVO(int no, String name, String year, String ban, String gender, int korean, int english, int math,
			int sic, int soc, int music, int total, double avg, String register) {
		super();
		
		this.no = no;
		
		this.name = name;
		this.year = year;
		this.ban = ban;
		this.gender = gender;
		
		this.korean = korean;
		this.english = english;
		this.math = math;
		this.sic = sic;
		this.soc = soc;
		this.music = music;		
		this.total = total;
		this.avg = avg;
		
		this.register = register;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getBan() {
		return ban;
	}

	public void setBan(String ban) {
		this.ban = ban;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getKorean() {
		return korean;
	}

	public void setKorean(int korean) {
		this.korean = korean;
	}

	public int getEnglish() {
		return english;
	}

	public void setEnglish(int english) {
		this.english = english;
	}

	public int getMath() {
		return math;
	}

	public void setMath(int math) {
		this.math = math;
	}

	public int getSic() {
		return sic;
	}

	public void setSic(int sic) {
		this.sic = sic;
	}

	public int getSoc() {
		return soc;
	}

	public void setSoc(int soc) {
		this.soc = soc;
	}

	public int getMusic() {
		return music;
	}

	public void setMusic(int music) {
		this.music = music;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public double getAvg() {
		return avg;
	}

	public void setAvg(double avg) {
		this.avg = avg;
	}

	public String getRegister() {
		return register;
	}

	public void setRegister(String register) {
		this.register = register;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

}
