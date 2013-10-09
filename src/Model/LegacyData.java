package Model;

public class LegacyData {
	Integer suiteNumber;
	String suiteName;
	Integer susNum, failNum, skipNum;
	public LegacyData(Integer suiteNumber, String suiteName, Integer susNum, Integer failNum, Integer skipNum) {
		this.suiteNumber = suiteNumber;
		this.suiteName = suiteName;
		this.susNum = susNum;
		this.failNum = failNum;
		this.skipNum = skipNum;
	}

	public String toString(){
		return suiteNumber.toString() + suiteName + susNum.toString() + failNum.toString() + skipNum.toString();
	}

	public Integer getSuiteNumber() {
		return suiteNumber;
	}

	public void setSuiteNumber(Integer suiteNumber) {
		this.suiteNumber = suiteNumber;
	}

	public String getSuiteName() {
		return suiteName;
	}

	public void setSuiteName(String suiteName) {
		this.suiteName = suiteName;
	}

	public Integer getSusNum() {
		return susNum;
	}

	public void setSusNum(Integer susNum) {
		this.susNum = susNum;
	}

	public Integer getFailNum() {
		return failNum;
	}

	public void setFailNum(Integer failNum) {
		this.failNum = failNum;
	}

	public Integer getSkipNum() {
		return skipNum;
	}

	public void setSkipNum(Integer skipNum) {
		this.skipNum = skipNum;
	}
}
