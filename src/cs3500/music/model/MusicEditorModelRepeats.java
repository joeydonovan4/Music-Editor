package cs3500.music.model;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by joeydonovan on 6/26/16.
 */
public class MusicEditorModelRepeats extends MusicEditorModel implements IMusicEditorRepeats {

  private List<IRange> repeatList;
  private List<IRange> endings;
  private int currentEnding;

  public MusicEditorModelRepeats(List<INote> notes, List<IRange> repeats, List<IRange> endings,
                                 int measureLength, int tempo) {
    super(notes, measureLength, tempo);
    for (IRange r : repeats) {
      if (r.getEndBeat() > getLengthInBeats())
        throw new IllegalArgumentException("Invalid repeat. Upper bound of repeat cannot" +
                " be greater than the length of the musical piece.");
    }

    this.repeatList = repeats;
    this.endings = endings;
    this.currentEnding = 0;
    Collections.sort(endings);
  }

  @Override
  public List<IRange> getRepeats() {
    List<IRange> tempArray = new ArrayList<>();
    tempArray.addAll(repeatList);
    return tempArray;
  }

  @Override
  public List<IRange> getEndings() {
    List<IRange> tempArray = new ArrayList<>();
    tempArray.addAll(endings);
    return tempArray;
  }

  @Override
  public IRange getRepeatAt(int beat) {
    for (IRange r : repeatList) {
      if (r.hasBeat(beat))
        return r;
    }
    return null;
  }

  @Override
  public void setCurrentEnding(int ending) {
    currentEnding = ending;
  }

  @Override
  public int getCurrentEnding() {
    return currentEnding;
  }

  @Override
  public boolean hasRepeatAt(int beat) {
    for (IRange r : repeatList) {
      if (r.hasBeat(beat))
        return true;
    }
    return false;
  }

  @Override
  public boolean hasRepeatsLeft(IRange r) {
    return r.getRemainingRepeats() > 0;
  }

  @Override
  public void decrement(IRange r) {
    r.decrement();
  }

  @Override
  public int getRespondingBeat(IRange r) {
    return r.getStartBeat();
  }
}