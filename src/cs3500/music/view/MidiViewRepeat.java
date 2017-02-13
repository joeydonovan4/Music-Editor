package cs3500.music.view;

import cs3500.music.model.IRange;

/**
 * Created by joeydonovan on 6/26/16.
 */
public class MidiViewRepeat extends MidiViewImpl {

  public MidiViewRepeat() {
    super();
  }

  @Override
  public void update() {
    int tick = (int) getSequencer().getTickPosition();

    if (!mtv.getEndings().isEmpty()) {
      if (mtv.getCurrentEnding() > 0) {
        if (tick == mtv.getEndings().get(0).getStartBeat()) {
          sequencer.setTickPosition(mtv.getEndings().get(mtv.getCurrentEnding()).getStartBeat());
          sequencer.setTempoInMPQ(mtv.getTempo());
        }
      }

      if (tick == mtv.getEndings().get(mtv.getCurrentEnding()).getEndBeat()) {
        if (mtv.hasRepeatAt(tick)) {
          mtv.setCurrentEnding(mtv.getCurrentEnding() + 1);
          IRange repeat = mtv.getRepeatAt(tick);
          sequencer.setTickPosition(mtv.getRespondingBeat(repeat));
          sequencer.setTempoInMPQ(mtv.getTempo());
        }
      }
    }

    if (mtv.hasRepeatAt(tick)) {
      IRange repeat = mtv.getRepeatAt(tick);
      if (mtv.hasRepeatsLeft(repeat) && tick == repeat.getEndBeat()) { // If it has repeats left...
        mtv.decrement(repeat); // Decrement the amount of repeats it has left
        sequencer.setTickPosition(mtv.getRespondingBeat(repeat)); // Set tick position to its beginning bound
        sequencer.setTempoInMPQ(mtv.getTempo());
      }
    }
  }
}