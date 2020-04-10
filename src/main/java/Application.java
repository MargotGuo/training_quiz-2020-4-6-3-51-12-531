import entities.Receipt;
import entities.Ticket;
import entities.employee.Employee;
import entities.employee.Manager;
import entities.employee.SmartEmployee;
import exception.InvalidTicketException;
import exception.ParkingLotFullException;

import java.util.Scanner;

public class Application {

  private static Manager manager = new Manager();

  public static void main(String[] args) {
    operateParking();
  }

  public static void operateParking() {
    while (true) {
      System.out.println("1. 初始化停车场数据\n2. 停车\n3. 取车\n4. 退出\n请输入你的选择(1~4)：");
      Scanner printItem = new Scanner(System.in);
      String choice = printItem.next();
      if (choice.equals("4")) {
        System.out.println("系统已退出");
        break;
      }
      try {
        handle(choice);
      } catch (ParkingLotFullException | InvalidTicketException e) {
        System.out.println(e.getMessage());
      }
    }
  }

  private static void handle(String choice) {
    Scanner scanner = new Scanner(System.in);
    if (choice.equals("1")) {
      System.out.println("请输入初始化数据\n格式为\"停车场编号1：车位数,停车场编号2：车位数\" 如 \"A:8,B:9\"：");
      String initInfo = scanner.next();
      init(initInfo);
    }
    else if (choice.equals("2")) {
      System.out.println("请输入车牌号\n格式为\"车牌号\" 如: \"A12098\"：");
      String carInfo = scanner.next();
      String ticket = park(carInfo);
      String[] ticketDetails = ticket.split(",");
      System.out.format("已将您的车牌号为%s的车辆停到%s停车场%s号车位，停车券为：\"%s\"，请您妥善保存。\n", ticketDetails[2], ticketDetails[0], ticketDetails[1], ticket);
    }
    else if (choice.equals("3")) {
      System.out.println("请输入停车券信息\n格式为\"停车场编号1,车位编号,车牌号\" 如 \"A,1,8\"：");
      String ticket = scanner.next();
      Receipt receipt = fetch(ticket);
      System.out.format("已为您取到车牌号为%s的车辆，您的停车时间为%.1f小时，停车费用为%d元，很高兴为您服务，祝您生活愉快!\n",
          receipt.getCarNumber(), receipt.getTime(), receipt.getPrice());
    }
  }

  public static void init(String initInfo) {

    manager.deleteData();
    String[] parkingLotArray = initInfo.split(",");
    for (String inputParkingLotMessage : parkingLotArray) {
      String[] parkingLotDetail = inputParkingLotMessage.split(":");
      String parkingLotId = parkingLotDetail[0];
      int capacity = Integer.parseInt(parkingLotDetail[1]);
      manager.initParkingLot(parkingLotId, capacity);
    }
  }

  public static String park(String carNumber) {
    Employee employee = manager.getNextParkEmployee();
    Ticket ticket = employee.parkCar(carNumber);
    return ticket.toString();
  }

  public static Receipt fetch(String userTicket) {
    Employee employee = manager.getNextFetchEmployee();
    Ticket ticket = new Ticket(userTicket);
    return employee.fetchCar(ticket);
  }
}
