package dao;

import database.DBCon;
import entity.DataChart;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataChartDAO {
	
//	public boolean insertDataChartRow(double occupancyRatio) {
//        try (
//            Connection connection = DBCon.getConn();
//            CallableStatement callableStatement = connection.prepareCall("{CALL InsertDataChartRow(?, ?)}");
//        ) {
//
//            callableStatement.setDate(1, new java.sql.Date(new Date().getTime()));
//            callableStatement.setDouble(2, occupancyRatio);
//
//            int rowsAffected = callableStatement.executeUpdate();
//
//            return rowsAffected > 0;
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
	
    public List<DataChart> getOccupancyRatioByYearMonth(int year, int month) {
        List<DataChart> occupancyData = new ArrayList<>();

        try (
            Connection connection = DBCon.getConn();
            CallableStatement callableStatement = connection.prepareCall("{CALL GetOccupancyRatioByYearMonth(?, ?)}");
        ) {
            callableStatement.setInt(1, year);
            callableStatement.setInt(2, month);

            try (ResultSet resultSet = callableStatement.executeQuery()) {
                while (resultSet.next()) {
                    Date recordDate = resultSet.getDate("record_date");
                    double occupancyRatio = resultSet.getDouble("occupancy_ratio");

                    DataChart data = new DataChart(recordDate, occupancyRatio);
                    occupancyData.add(data);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return occupancyData;
    }

}
