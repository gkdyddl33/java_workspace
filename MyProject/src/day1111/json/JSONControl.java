package day1111.json;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.mysql.cj.xdevapi.JsonParser;

/*
 	�ܺ��� �����͸� �������� ��� ��κ� XML,JSON ������ �������̴�.
 	���� �ڹٰ����ڴ� �ڹ� ���� XML,JSON ���� �����͸� �ؼ�, �м�
 	(�Ľ�:parsing)�� �� �ִ� �ɷ��� �ʿ��ϴ�.
 	
 	�ڹپ�� ���������δ� JSON ǥ����� ������ �� ����.
	���������� �ʾ� �߸��� �������� ���صȴ�.
	�ذ�å��? ���ڿ��� ó���ؾ� �Ѵ�.
*/
public class JSONControl {
	
	public static void main(String[] args) {
		// StringBuffr �� �� ������? String�� �Һ��� Ư¡�� �����Ƿ�,
		// �ʹ� ���� ���ڿ� ����� ������ �ʱ� ����..
		StringBuffer sb = new StringBuffer();
		
		sb.append("{");
		sb.append("\"name\":\"zino\"");
		sb.append("}");
		
		// sb �� ����� ǥ���, ���� JSON��ü�� �ƴϹǷ�, �Ľ̴ܰ踦 ����
		// JSON��ü�� ��ȯ�ؾ� �Ѵ�.
		// �ڹ� ���ߺоߴ� �ַ� ������(���¼ҽ� ����)�� �ܺ� ���̺귯���� ����ġ��ܿ��� ��Ǵ�
		// maven ����Ʈ�� �̿��Ѵ�.
		
		// ������ �м��ϴ� �ļ���ü "����"
		JSONParser jsonParser = new JSONParser();
		try {
			JSONObject obj = (JSONObject) jsonParser.parse(sb.toString());		// "�Ľ̽���"
			// �Ľ��� �Ϸ�� ���ĺ��ʹ� �� �̻� ���ڿ��� �ƴ� JSON ��ü�� ����ϸ� �ȴ�.
			System.out.println(obj.get("name"));
		
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
