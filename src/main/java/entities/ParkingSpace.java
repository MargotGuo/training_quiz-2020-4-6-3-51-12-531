package entities;

public class ParkingSpace {

  private int id;
  private String parkingLotId;
  private int positionId;
  private String status;
  private String carNumber;

  public ParkingSpace(String parkingLotId, int positionId) {
    this.parkingLotId = parkingLotId;
    this.positionId = positionId;
  }

  public ParkingSpace(int id, String parkingLotId, int positionId, String status, String carNumber) {
    this.id = id;
    this.parkingLotId = parkingLotId;
    this.positionId = positionId;
    this.status = status;
    this.carNumber = carNumber;
  }

  public int getId() {
    return id;
  }

  public String getParkingLotId() {
    return parkingLotId;
  }

  public int getPositionId() {
    return positionId;
  }

  public String getStatus() {
    return status;
  }

  public String getCarNumber() {
    return carNumber;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public void setCarNumber(String carNumber) {
    this.carNumber = carNumber;
  }
}
