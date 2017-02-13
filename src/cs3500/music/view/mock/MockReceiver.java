package cs3500.music.view.mock;

import javax.sound.midi.MidiMessage;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;

/**
 * Created by joeydonovan on 6/18/16.
 */

/**
 * A Mock Receiver used for testing the MIDI Receiver.
 * This mock overrides all of {@link Receiver}s methods with stubs,
 * and uses a {@link StringBuilder} to store the {@link cs3500.music.model.Note}s that
 * are being sent in messages.
 */
public class MockReceiver implements Receiver {

  private StringBuilder log;

  /**
   * Constructor for a MockReceiver.
   * @param log the log of the current notes passed to the receiver.
   */
  public MockReceiver(StringBuilder log) {
    this.log = log;
  }

  @Override
  public void send(MidiMessage message, long timeStamp) {
    ShortMessage sm = (ShortMessage) message;
    String status;
    if (sm.getCommand() == 144)
      status = "ON";
    else
      status = "OFF";

    log.append("note ");
//    log.append(timeStamp);
//    log.append(" ");
    log.append(status);
    log.append(" ");
    log.append(sm.getData1());
    log.append("\n");
  }

  @Override
  public void close() {

  }

}
