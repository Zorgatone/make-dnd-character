package tk.zorgatone.dndmaker.random;

public class DiceTester {
  public static void test100(byte faces) {
    final Dice dice = new Dice(faces);

    final byte[] rolls = dice.roll((byte) 100);
    final byte[] occurrences = new byte[faces];

    for (byte roll : rolls) {
      occurrences[roll-1] += 1;
    }

    for (int i = 0; i < occurrences.length; i++) {
      System.out.println((i + 1) + " appeared " + occurrences[i] + " times.");
    }
  }

}
