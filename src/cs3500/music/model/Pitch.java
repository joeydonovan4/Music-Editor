package cs3500.music.model;

/**
 * Created by joeydonovan on 6/6/16.
 */

/**
 * Represents the different types of pitches of music.
 */
public enum Pitch {
  C("C"),
  CSHARP("C#"),
  D("D"),
  DSHARP("D#"),
  E("E"),
  F("F"),
  FSHARP("F#"),
  G("G"),
  GSHARP("G#"),
  A("A"),
  ASHARP("A#"),
  B("B");

  private final String value;

  Pitch(String value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return value;
  }

  public static Pitch toPitch(int pitchNo) throws IllegalArgumentException {
    switch (pitchNo) {
      case 0:
        return C;
      case 1:
        return CSHARP;
      case 2:
        return D;
      case 3:
        return DSHARP;
      case 4:
        return E;
      case 5:
        return F;
      case 6:
        return FSHARP;
      case 7:
        return G;
      case 8:
        return GSHARP;
      case 9:
        return A;
      case 10:
        return ASHARP;
      case 11:
        return B;
      default:
        throw new IllegalArgumentException("Invalid pitch");
    }
  }
}
