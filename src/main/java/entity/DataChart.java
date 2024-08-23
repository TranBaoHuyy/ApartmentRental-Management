package entity;

import java.sql.Date;

public class DataChart {
    private Date recordDate;
    private double occupancyRatio;
    
	public DataChart(Date recordDate, double occupancyRatio) {
		this.recordDate = recordDate;
		this.occupancyRatio = occupancyRatio;
	}
	public Date getRecordDate() {
		return recordDate;
	}
	public void setRecordDate(Date recordDate) {
		this.recordDate = recordDate;
	}
	public double getOccupancyRatio() {
		return occupancyRatio;
	}
	public void setOccupancyRatio(double occupancyRatio) {
		this.occupancyRatio = occupancyRatio;
	}
}
