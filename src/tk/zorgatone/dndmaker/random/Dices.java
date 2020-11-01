package tk.zorgatone.dndmaker.random;

import java.util.Arrays;

public class Dices {

  private static Dices dices;

  public static final byte D4 = 4;
  public static final byte D6 = 6;
  public static final byte D10 = 10;
  public static final byte D12 = 12;
  public static final byte D20 = 20;

  private final Dice d4;
  private final Dice d6;
  private final Dice d10;
  private final Dice d12;
  private final Dice d20;

  private Dices() {
    if (dices != null) {
      throw new IllegalStateException("Cannot create Dices class more than once!");
    }

    this.d4 = new Dice(D4);
    this.d6 = new Dice(D6);
    this.d10 = new Dice(D10);
    this.d12 = new Dice(D12);
    this.d20 = new Dice(D20);
  }

  private static Dices get() {
    Dices d = dices;

    if (d == null) {
      synchronized (Dices.class) {
        d = dices;

        if (d == null) {
          d = new Dices();
          dices = d;
        }
      }
    }

    return d;
  }

  public static byte rollCharacteristic() {
    final byte numRolls = 4;
    byte[] rolls = get().d6.roll(numRolls);

    Arrays.sort(rolls);

    byte sum = 0;
    for (byte j = 1; j < numRolls; j++) {
      sum += rolls[j];
    }

    return sum;
  }

  public static byte[] rollCharacteristics() {
    final byte numRolls = 4;
    final Dices dices = get();
    final byte[] characteristicValues = new byte[6];
    byte[] rolls;

    for (byte i = 0; i < characteristicValues.length; i++) {
      rolls = dices.d6.roll(numRolls);

      Arrays.sort(rolls);

      for (byte j = 1; j < numRolls; j++) {
        characteristicValues[i] += rolls[j];
      }
    }

    sortDesc(characteristicValues);

    return characteristicValues;
  }

  private static void sortDesc(byte[] arr) {
    Arrays.sort(arr);
    reverse(arr);
  }

  private static void reverse(byte[] arr) {
    if (arr == null || arr.length < 2) {
      return;
    }

    final byte halfLen = (byte) (arr.length / 2);
    byte tmp;

    for (byte i = 0; i < halfLen; i++) {
      tmp = arr[arr.length - i - 1];
      arr[arr.length - i - 1] = arr[i];
      arr[i] = tmp;
    }
  }

}
