package springboot.test;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class JobStatus {

	private String jobName;

	@JsonFormat(pattern="yyyyMMddhhmmss",timezone="GMT+8")
	private Date startTime;

	@JsonFormat(pattern="yyyyMMddhhmmss",timezone="GMT+8")
	private Date endTime;

	private String result;

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}


}