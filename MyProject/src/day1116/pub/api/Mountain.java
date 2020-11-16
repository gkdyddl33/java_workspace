package day1116.pub.api;

public class Mountain {
/*
	멤버변수로는 오픈 데이터 포털의 산정보에 있는 모든 데이터를 다 넣기엔 너무 많으니깐
	원하는 것을 골라 넣어보자.(VO)
	그리고 은닉화 시키기.
*/
	private int mntnid;	// 산의 고유코드
	private String mntnnm;	// 산이 담게 될 변수
	private String mntninfopoflc;	// 산정보소재지(소재지)
	private String mntninfohght;	// 산높이
	
	public int getMntnid() {
		return mntnid;
	}
	public void setMntnid(int mntnid) {
		this.mntnid = mntnid;
	}
	public String getMntnnm() {
		return mntnnm;
	}
	public void setMntnnm(String mntnnm) {
		this.mntnnm = mntnnm;
	}
	public String getMntninfopoflc() {
		return mntninfopoflc;
	}
	public void setMntninfopoflc(String mntninfopoflc) {
		this.mntninfopoflc = mntninfopoflc;
	}
	public String getMntninfohght() {
		return mntninfohght;
	}
	public void setMntninfohght(String mntninfohght) {
		this.mntninfohght = mntninfohght;
	}	
	
}
