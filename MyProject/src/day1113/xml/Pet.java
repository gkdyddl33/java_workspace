package day1113.xml;
/*
 	�Ѹ����� �ݷ������� ��� �� VO Ŭ����
 	VO��? Value Object �� ���ڷμ�, ���� �ۼ��� ������ �ƴ� ���� �����͸��� ������ �뵵�� ���Ǵ�
 			��ü�� ����Ű�� ���ø����̼� ���� ��� �� �ϳ�
*/
public class Pet {
	private String type;
	private String name;
	private int age;
	private String gender;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
		
}
