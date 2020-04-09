package repositories;

import entities.ParkingSpace;
import entities.Ticket;
import utils.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class ParkingLotRepository {

  public void saveAll(List<ParkingSpace> parkingSpaceList) {
    String saveSql = "INSERT INTO parking_lot(parking_lot_id, position_id) VALUES (?,?)";
    try (Connection connection = ConnectionUtil.getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(saveSql)) {
      for (ParkingSpace parkingSpace : parkingSpaceList) {
        preparedStatement.setString(1, parkingSpace.getParkingLotId());
        preparedStatement.setInt(2, parkingSpace.getPositionId());
        preparedStatement.execute();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  private void update(String updateSql) {
    try (Connection connection = ConnectionUtil.getConnection();
         Statement statement = connection.createStatement()) {
      statement.executeUpdate(updateSql);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void deleteAll() {
    String deleteSql = "DELETE FROM parking_lot";
    update(deleteSql);
  }

  public void setAutoIncrement() {
    String autoIncrementSql = "ALTER TABLE parking_lot AUTO_INCREMENT = 1";
    update(autoIncrementSql);
  }

  public void updateParkingSpace(ParkingSpace parkingSpace) {
    String updateSql = String.format(
        "UPDATE parking_lot SET status = '%s', car_number = '%s' WHERE id = %s",
        parkingSpace.getStatus(), parkingSpace.getCarNumber(), parkingSpace.getId());
    update(updateSql);
  }

  public List<ParkingSpace> queryList(String whereClause) {
    String querySql = "SELECT id, parking_lot_id, position_id, status, car_number FROM parking_lot "
            + whereClause
            + " ORDER BY parking_lot_id ASC, position_id ASC";
    List<ParkingSpace> parkingSpaceList = new LinkedList<>();
    try (Connection connection = ConnectionUtil.getConnection();
         Statement statement = connection.createStatement();
         ResultSet resultSet = statement.executeQuery(querySql)) {
      while (resultSet.next()) {
        parkingSpaceList.add(new ParkingSpace(
            resultSet.getInt("id"),
            resultSet.getString("parking_lot_id"),
            resultSet.getInt("position_id"),
            resultSet.getString("status"),
            resultSet.getString("car_number")));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return parkingSpaceList;
  }

  public ParkingSpace queryByTicket(Ticket ticket) {
    String whereClause = String.format("WHERE parking_lot_id = '%s' AND position_id = %s AND car_number = '%s'",
        ticket.getParkingLotId(), ticket.getPositionId(), ticket.getCarNumber());
    List<ParkingSpace> parkingSpaceList = queryList(whereClause);
    return parkingSpaceList.isEmpty() ? null : parkingSpaceList.get(0);
  }
}
