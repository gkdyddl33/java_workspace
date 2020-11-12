package day1111.json;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.mysql.cj.xdevapi.JsonParser;

/*
 	외부의 데이터를 제공받을 경우 대부분 XML,JSON 형태의 데이터이다.
 	따라서 자바개발자는 자바 언어에서 XML,JSON 등의 데이터를 해석, 분석
 	(파싱:parsing)할 수 있는 능력이 필요하다.
 	
 	자바언어 내부적으로는 JSON 표기법을 이해할 수 없다.
	지원하지를 않아 잘못된 문장으로 이해된다.
	해결책은? 문자열로 처리해야 한다.
*/
public class JSONControl {
	
	public static void main(String[] args) {
		// StringBuffr 를 쓴 이유는? String은 불변의 특징이 있으므로,
		// 너무 많은 문자열 상수를 만들어내지 않기 위해..
		StringBuffer sb = new StringBuffer();
		
		sb.append("{");
		sb.append("\"name\":\"zino\"");
		sb.append("}");
		
		// sb 에 담겨진 표기는, 실제 JSON객체는 아니므로, 파싱단계를 거쳐
		// JSON객체로 전환해야 한다.
		// 자바 개발분야는 주로 무료기반(오픈소스 진영)의 외부 라이브러리는 아파치재단에서 운영되는
		// maven 사이트를 이용한다.
		
		// 구문을 분석하는 파서객체 "생성"
		JSONParser jsonParser = new JSONParser();
		try {
			JSONObject obj = (JSONObject) jsonParser.parse(sb.toString());		// "파싱시작"
			// 파싱이 완료된 이후부터는 더 이상 문자열이 아닌 JSON 객체로 사용하면 된다.
			System.out.println(obj.get("name"));
		
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
