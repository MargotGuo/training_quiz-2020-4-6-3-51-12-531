package entities;

import java.util.Random;

public class Receipt {
  private String carNumber;
  private double time = getRandomTime();
  private int price = getPrice(time);

  public Receipt(String carNumber) {
    this.carNumber = carNumber;
  }

  private double getRandomTime() {
    Random random = new Random();
    return 24 * random.nextDouble();
  }

  private int getPrice(double time) {
    int finalTimeToCalcPrice = (int) Math.ceil(time);
    if (finalTimeToCalcPrice <= 2) {
      return 0;
    }
    if (finalTimeToCalcPrice <= 5) {
      return (finalTimeToCalcPrice - 2) * 5;
    }
    return 15 + (finalTimeToCalcPrice - 5) * 10;
  }

  public String getCarNumber() {
    return carNumber;
  }

  public double getTime() {
    return time;
  }

  public int getPrice() {
    return price;
  }
}
