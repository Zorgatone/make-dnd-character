package tk.zorgatone.dndmaker.random;

import java.util.Random;

final class Dice {

  static final byte MIN_FACES = 4;
  static final byte DEFAULT_FACES = 6;
  static final byte MAX_FACES = 20;

  private static final Random random;
  private final byte faces;

  static {
    synchronized (Dice.class) {
      random = new Random();
    }
  }

  @SuppressWarnings("unused")
  Dice() {
    this(DEFAULT_FACES);
  }

  Dice(final byte faces) {
    if (faces < MIN_FACES || faces > MAX_FACES) {
      throw new IllegalArgumentException(
        "Invalid faces argument must be between " + MIN_FACES
          + " and " + MAX_FACES
      );
    }

    this.faces = faces;
  }

  @SuppressWarnings("unused")
  public byte roll() {
    return roll((byte) 1)[0];
  }

  public byte[] roll(final byte numRolls) {
    if (numRolls < 1) {
      throw new IllegalArgumentException(
        "Invalid numRolls argument must be at least 1"
      );
    }

    final byte[] b = new byte[numRolls];

    synchronized (random) {
      random.nextBytes(b);
    }

    for (int i = 0; i < numRolls; i++) {
      b[i] = (byte) ((b[i] - Byte.MIN_VALUE) % faces + 1);
    }

    return b;
  }

}
