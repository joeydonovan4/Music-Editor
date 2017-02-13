package cs3500.music.view;

import javax.sound.midi.*;
import javax.sound.midi.MidiSystem;
import cs3500.music.model.INote;
import cs3500.music.view.mock.MockMidiDevice;

/**
 * A skeleton for MIDI playback
 */
public class MidiViewImpl implements EditorView {
  private final Synthesizer synth;
  private final Transmitter transmitter;
  private final Receiver receiver;
  final Sequencer sequencer;
  protected IModelToView mtv;

  /**
   * Constructor for a MIDI.
   * Stores {@link Synthesizer} and {@link Receiver} as temp variables initially,
   * then sets them to class variables.
   */
  public MidiViewImpl() {
    Sequencer tempSequencer = null;
    Transmitter tempTrans = null;
    Synthesizer tempSynth = null;
    Receiver tempReceiver = null;
    try {
      tempSequencer = MidiSystem.getSequencer();
      tempSequencer.open();
      tempTrans = tempSequencer.getTransmitter();
      tempSynth = MidiSystem.getSynthesizer();
      tempSynth.open();
      tempReceiver = tempSynth.getReceiver();
      tempTrans.setReceiver(tempReceiver);
    } catch (MidiUnavailableException e) {
      e.printStackTrace();
    }
    finally {
      this.sequencer = tempSequencer;
      this.transmitter = tempTrans;
      this.synth = tempSynth;
      this.receiver = tempReceiver;
    }
  }

  /**
   * Convenience constructor used for testing a MIDI.
   * @param log log of the notes that the MIDI has played.
   */
  MidiViewImpl(StringBuilder log) {
    Sequencer tempSeq = null;
    Transmitter tempTrans = null;
    Synthesizer tempSynth = null;
    Receiver tempReceiver = null;
    try {
      tempSeq = MidiSystem.getSequencer();
      tempSeq.open();
      tempTrans = tempSeq.getTransmitter();
      tempSynth = new MockMidiDevice(log);
      tempReceiver = tempSynth.getReceiver();
      tempTrans.setReceiver(tempReceiver);
      tempSynth.open();
    } catch (MidiUnavailableException e) {
      e.printStackTrace();
    }
    finally {
      this.sequencer = tempSeq;
      this.transmitter = tempTrans;
      this.synth = tempSynth;
      this.receiver = tempReceiver;
    }
  }

  /**
   * Adds all the {@link cs3500.music.model.INote}s to the {@link Sequencer}.
   * @param mtv the model-to-view data.
   */
  private void addNotesToMIDI(IModelToView mtv) {
    try {
      sequencer.setTempoInMPQ(mtv.getTempo());
      Sequence sequence = new Sequence(Sequence.PPQ, 1);
      Track t = sequence.createTrack();
      for (int i = 0; i < 16; i++) {
        MidiMessage newInstrument = new ShortMessage(ShortMessage.PROGRAM_CHANGE, i, 0);
        t.add(new MidiEvent(newInstrument, 0));
      }

      for (INote n : mtv.getNotes()) {
        MidiMessage start = new ShortMessage(ShortMessage.NOTE_ON, n.getInstrument(),
                n.getValue(), n.getVolume());
        t.add(new MidiEvent(start, n.getStartBeat()));
        MidiMessage stop = new ShortMessage(ShortMessage.NOTE_OFF, n.getInstrument(),
                n.getValue(), n.getVolume());
        t.add(new MidiEvent(stop, n.getEndBeat()));
      }

      sequencer.setSequence(sequence);

    } catch (InvalidMidiDataException e) {
        e.printStackTrace();
    }
  }

  /**
   * Adds {@link cs3500.music.model.Note}s to the MIDI player if they have been added by a client.
   * @param mtv the model-to-view data.
   */
  void addToMIDI(IModelToView mtv) {
    Sequence sequence = sequencer.getSequence();
    Track t = sequence.getTracks()[0];
    try {
      for (INote n : mtv.getAdded()) {
        MidiMessage start = new ShortMessage(ShortMessage.NOTE_ON, n.getInstrument(),
                n.getValue(), n.getVolume());
        t.add(new MidiEvent(start, n.getStartBeat()));
        MidiMessage stop = new ShortMessage(ShortMessage.NOTE_OFF, n.getInstrument(),
                n.getValue(), n.getVolume());
        t.add(new MidiEvent(stop, n.getEndBeat()));
      }
      sequencer.setSequence(sequence);
    } catch (InvalidMidiDataException e) {
        e.printStackTrace();
    }
  }

  /**
   * Refreshes the MIDI player if {@link INote}s have been removed from the MIDI.
   * @param mtv the model-to-view data.
   */
  void refreshMIDI(IModelToView mtv) {
    Sequence sequence = sequencer.getSequence();
    sequence.deleteTrack(sequence.getTracks()[0]);
    try {
      sequencer.setTempoInMPQ(mtv.getTempo());
      Track t = sequence.createTrack();
      for (int i = 0; i < 16; i++) {
        MidiMessage newInstrument = new ShortMessage(ShortMessage.PROGRAM_CHANGE, i, 0);
        t.add(new MidiEvent(newInstrument, 0));
      }

      for (INote n : mtv.getNotes()) {
        MidiMessage start = new ShortMessage(ShortMessage.NOTE_ON, n.getInstrument(),
                n.getValue(), n.getVolume());
        t.add(new MidiEvent(start, n.getStartBeat()));
        MidiMessage stop = new ShortMessage(ShortMessage.NOTE_OFF, n.getInstrument(),
                n.getValue(), n.getVolume());
        t.add(new MidiEvent(stop, n.getEndBeat()));
      }
      sequencer.setSequence(sequence);
    } catch (InvalidMidiDataException e) {
        e.printStackTrace();
    }
  }

  /**
   * Adds all {@link INote}s in the musical piece to the MIDI player,
   * then begins playing the {@link Sequencer}.
   * @param mtv the model to be shown in the view.
   */
  @Override
  public void render(IModelToView mtv) {
    this.mtv = mtv;
    addNotesToMIDI(mtv);
    sequencer.start();
  }

  @Override
  public void update() {
    sequencer.setTempoInMPQ(mtv.getTempo());
  }

  /**
   * Gets the {@link Sequencer} in the MIDI player.
   * @return the {@code sequencer}.
   */
  Sequencer getSequencer() {
    return sequencer;
  }
}
