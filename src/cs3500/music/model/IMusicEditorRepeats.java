package cs3500.music.model;

import java.util.List;

/**
 * Created by joeydonovan on 6/26/16.
 */
public interface IMusicEditorRepeats {

  void decrement(IRange r);

  boolean hasRepeatAt(int beat);

  IRange getRepeatAt(int beat);

  void setCurrentEnding(int ending);

  int getCurrentEnding();

  int getRespondingBeat(IRange r);

  boolean hasRepeatsLeft(IRange r);

  List<IRange> getRepeats();

  List<IRange> getEndings();
}
