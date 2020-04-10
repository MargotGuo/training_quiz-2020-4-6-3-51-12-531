package entities.employee;

import entities.ParkingSpace;
import repositories.ParkingLotRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Manager {

  private static ParkingLotRepository parkingLotRepository = new ParkingLotRepository();

  private Employee normalEmployee = new NormalEmployee();
  private Employee smartEmployee = new SmartEmployee();
  private Employee currentParkCarEmployee = normalEmployee;

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

  public Employee getNextParkEmployee() {
    this.currentParkCarEmployee = currentParkCarEmployee.getClass().equals(NormalEmployee.class) ?
        smartEmployee : normalEmployee;
    return currentParkCarEmployee;
  }

  public Employee getNextFetchEmployee() {
    Random random = new Random();
    int id = random.nextInt(2);
    return id == 0 ? normalEmployee : smartEmployee;
  }
}
