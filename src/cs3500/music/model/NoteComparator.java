package cs3500.music.model;

import java.util.Comparator;

/**
 * Created by joeydonovan on 6/9/16.
 */

/**
 * Comparator for {@link INote}s.
 */
class NoteComparator implements Comparator<INote> {

  public int compare(INote n1, INote n2) {
    if (n1.getValue() > n2.getValue())
      return -1;
    else if (n1.getValue() == n2.getValue()) {
      if (n1.getStartBeat() > n2.getStartBeat())
        return -1;
      else if (n1.getStartBeat() == n2.getStartBeat())
        return 0;
      else
        return 1;
    }
    else
      return 1;
  }
}