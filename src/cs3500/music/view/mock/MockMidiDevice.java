package cs3500.music.view.mock;

import javax.sound.midi.*;
import java.util.List;

/**
 * Created by joeydonovan on 6/18/16.
 */

/**
 * A Mock Synthesizer used for testing the MIDI Synthesizer.
 * This mock overrides all of {@link Synthesizer}s methods with stubs,
 * and uses a {@link StringBuilder} to store the {@link cs3500.music.model.Note}s that
 * are being sent in messages.
 */
public class MockMidiDevice implements Synthesizer {

  private StringBuilder log;

  /**
   * Constructor for a MockMidiDevice.
   * @param log the log of the current notes passed by the synthesizer.
   */
  public MockMidiDevice(StringBuilder log) {
    this.log = log;
  }

  /**
   * Calls {@link MockReceiver} as this synthesizer's receiver.
   * @return {@link MockReceiver}.
   */
  @Override
  public Receiver getReceiver() {
    return new MockReceiver(log);
  }

  @Override
  public Info getDeviceInfo() {
    return null;
  }

  @Override
  public void open() {

  }

  @Override
  public void close() {

  }

  @Override
  public boolean isOpen() {
    return false;
  }

  @Override
  public long getMicrosecondPosition() {
    return 0;
  }

  @Override
  public int getMaxReceivers() {
    return 0;
  }

  @Override
  public int getMaxTransmitters() {
    return 0;
  }

  @Override
  public List<Receiver> getReceivers() {
    return null;
  }

  @Override
  public Transmitter getTransmitter() {
    return null;
  }

  @Override
  public List<Transmitter> getTransmitters() {
    return null;
  }

  @Override
  public int getMaxPolyphony() {
    return 0;
  }

  @Override
  public long getLatency() {
    return 0;
  }

  @Override
  public MidiChannel[] getChannels() {
    try {
      return MidiSystem.getSynthesizer().getChannels();
    }
    catch (MidiUnavailableException e) {
      e.printStackTrace();
      return null;
    }
  }

  @Override
  public VoiceStatus[] getVoiceStatus() {
    return new VoiceStatus[0];
  }

  @Override
  public boolean isSoundbankSupported(Soundbank soundbank) {
    return false;
  }

  @Override
  public boolean loadInstrument(Instrument instrument) {
    return false;
  }

  @Override
  public void unloadInstrument(Instrument instrument) {

  }

  @Override
  public boolean remapInstrument(Instrument from, Instrument to) {
    return false;
  }

  @Override
  public Soundbank getDefaultSoundbank() {
    try {
      return MidiSystem.getSynthesizer().getDefaultSoundbank();
    }
    catch (MidiUnavailableException e) {
      e.printStackTrace();
      return null;
    }
  }

  @Override
  public Instrument[] getAvailableInstruments() {
    return new Instrument[0];
  }

  @Override
  public Instrument[] getLoadedInstruments() {
    return new Instrument[0];
  }

  @Override
  public boolean loadAllInstruments(Soundbank soundbank) {
    return false;
  }

  @Override
  public void unloadAllInstruments(Soundbank soundbank) {

  }

  @Override
  public boolean loadInstruments(Soundbank soundbank, Patch[] patchList) {
    return false;
  }

  @Override
  public void unloadInstruments(Soundbank soundbank, Patch[] patchList) {

  }

}
