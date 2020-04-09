package entities;

public class Ticket {
  private String parkingLotId;
  private int positionId;
  private String carNumber;

  public Ticket(String parkingLotId, int positionId, String carNumber) {
    this.parkingLotId = parkingLotId;
    this.positionId = positionId;
    this.carNumber = carNumber;
  }

  public Ticket(String ticket) {
    String[] ticketDetail = ticket.split(",");
    this.parkingLotId = ticketDetail[0];
    this.positionId = Integer.parseInt(ticketDetail[1]);
    this.carNumber = ticketDetail[2];
  }

  public String getParkingLotId() {
    return parkingLotId;
  }

  public int getPositionId() {
    return positionId;
  }

  public String getCarNumber() {
    return carNumber;
  }

  @Override
  public String toString() {
    return String.format("%s,%s,%s", parkingLotId, positionId, carNumber);
  }
}
