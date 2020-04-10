package entities.employee;

import entities.ParkingSpace;
import entities.Receipt;
import entities.Ticket;
import exception.InvalidTicketException;
import repositories.ParkingLotRepository;

import java.util.List;
import java.util.Optional;

public abstract class Employee {

  private static ParkingLotRepository parkingLotRepository = new ParkingLotRepository();

  public abstract Ticket parkCar(String carNumber);

  protected List<ParkingSpace> findEmptySpaces() {
    String condition = "WHERE status = 'EMPTY'";
    return parkingLotRepository.queryList(condition);
  }

  public Receipt fetchCar(Ticket ticket) {
    Optional<ParkingSpace> parkingSpace = Optional.ofNullable(parkingLotRepository.queryByTicket(ticket));
    Optional<Receipt> receipt = parkingSpace.map(space -> new Receipt(space.getCarNumber()));
    parkingSpace.ifPresent(space -> updateStatus(space, "EMPTY", "------"));
    return receipt.orElseThrow(InvalidTicketException::new);
  }

  protected void updateStatus(ParkingSpace parkingSpace, String newStatus, String newCarNumber) {
    parkingSpace.setStatus(newStatus);
    parkingSpace.setCarNumber(newCarNumber);
    parkingLotRepository.updateParkingSpace(parkingSpace);
  }
}
