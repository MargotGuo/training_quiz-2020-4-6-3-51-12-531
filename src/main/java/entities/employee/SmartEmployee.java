package entities.employee;

import entities.ParkingSpace;
import entities.Ticket;
import exception.ParkingLotFullException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class SmartEmployee extends Employee {
  @Override
  public Ticket parkCar(String carNumber) {
    List<ParkingSpace> emptySpaces = findEmptySpaces();
    if (emptySpaces.isEmpty()) {
      throw new ParkingLotFullException();
    }
    Map<String, List<ParkingSpace>> parkingLotGroupById = emptySpaces
        .stream()
        .collect(Collectors.groupingBy(ParkingSpace::getParkingLotId));
    ParkingSpace targetParkingSpace = findTarget(parkingLotGroupById);
    Ticket ticket = new Ticket(
        targetParkingSpace.getParkingLotId(),
        targetParkingSpace.getPositionId(),
        carNumber);
    updateStatus(targetParkingSpace, "IN_USE", carNumber);
    return ticket;
  }

  private ParkingSpace findTarget(Map<String, List<ParkingSpace>> parkingLotGroupById) {
    Set<String> parkingLotId = parkingLotGroupById.keySet();
    int maxSize = 0;
    List<ParkingSpace> targetParkingSpaceList = new ArrayList<>();
    for (String id : parkingLotId) {
      int currentSize = parkingLotGroupById.get(id).size();
      if (currentSize > maxSize) {
        maxSize = currentSize;
        targetParkingSpaceList = parkingLotGroupById.get(id);
      }
    }
    return targetParkingSpaceList.get(0);
  }
}
