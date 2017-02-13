package cs3500.music.model;

/**
 * Created by joeydonovan on 6/26/16.
 */
class Range implements IRange {

  private final int startBeat, endBeat;
  private int remainingRepeats;

  Range(int startBeat, int endBeat, int repeats) {
    if (startBeat < 0 || endBeat < 0) {
      throw new IllegalArgumentException("Invalid repeat.");
    }
    this.startBeat = startBeat;
    this.endBeat = endBeat;
    this.remainingRepeats = repeats;
  }

  @Override
  public int getStartBeat() {
    return startBeat;
  }

  @Override
  public int getEndBeat() {
    return endBeat;
  }

  @Override
  public int getRemainingRepeats() {
    return remainingRepeats;
  }

  @Override
  public boolean hasBeat(int beat) {
    return startBeat <= beat && endBeat >= beat;
  }

  @Override
  public void decrement() {
    remainingRepeats--;
  }

  @Override
  public int compareTo(IRange r) {
    if (this.endBeat < r.getEndBeat())
      return -1;
    else
      return 1;
  }
}
