package entities;

import exception.InvalidTicketException;
import exception.ParkingLotFullException;
import repositories.ParkingLotRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ParkingLot {

  private static ParkingLotRepository parkingLotRepository = new ParkingLotRepository();

  public void deleteData() {
    parkingLotRepository.deleteAll();
    parkingLotRepository.setAutoIncrement();
  }

  public void initParkingLot(String parkingLotId, int capacity) {
    List<ParkingSpace> parkingSpaceList = new ArrayList<>();
    for (int positionId = 1; positionId <= capacity; positionId++) {
      parkingSpaceList.add(new ParkingSpace(parkingLotId, positionId));
    }
    parkingLotRepository.saveAll(parkingSpaceList);
  }

  public Ticket parkCar(String carNumber) {
    List<ParkingSpace> emptySpaces = findEmptySpaces();
    if (emptySpaces.isEmpty()) {
      throw new ParkingLotFullException();
    }
    ParkingSpace availableSpace = findEmptySpaces().get(0);
    Ticket ticket = new Ticket(availableSpace.getParkingLotId(), availableSpace.getPositionId(), carNumber);
    updateStatus(availableSpace, "IN_USE", carNumber);
    return ticket;
  }

  private List<ParkingSpace> findEmptySpaces() {
    String condition = "WHERE status = 'EMPTY'";
    return parkingLotRepository.queryList(condition);
  }

  public String fetchCar(Ticket ticket) {
    Optional<ParkingSpace> parkingSpace = Optional.ofNullable(parkingLotRepository.queryByTicket(ticket));
    Optional<String> fetchedCarNumber = parkingSpace.map(ParkingSpace::getCarNumber);
    parkingSpace.ifPresent(space -> updateStatus(space, "EMPTY", "------"));
    return fetchedCarNumber.orElseThrow(InvalidTicketException::new);
  }

  private void updateStatus(ParkingSpace parkingSpace, String newStatus, String newCarNumber) {
    parkingSpace.setStatus(newStatus);
    parkingSpace.setCarNumber(newCarNumber);
    parkingLotRepository.updateParkingSpace(parkingSpace);
  }
}
