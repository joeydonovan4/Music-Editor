package cs3500.music.model;

/**
 * Created by joeydonovan on 6/26/16.
 */

public interface IRange extends Comparable<IRange> {

  int getStartBeat();

  int getEndBeat();

  int getRemainingRepeats();

  boolean hasBeat(int beat);

  void decrement();
}
