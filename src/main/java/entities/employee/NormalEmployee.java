package entities.employee;

import entities.ParkingSpace;
import entities.Ticket;
import exception.ParkingLotFullException;

import java.util.List;

public class NormalEmployee extends Employee {
  @Override
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
}
