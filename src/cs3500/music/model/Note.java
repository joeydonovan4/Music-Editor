package cs3500.music.model;

import java.util.Objects;

/**
 * Created by joeydonovan on 6/6/16.
 */

/**
 * Represents a Note of music.
 */
public final class Note implements INote {

  /**
   * Class invariants:
   * pitch integer representation >= 0 and < 13
   * octave is zero indexed, >= 0
   * startBeat >= 0
   * endBeat >= 0
   * beats >= 0
   * instrument >= 0
   * value >= 0
   */

  private Pitch pitch;
  private int octave, startBeat, endBeat, beats, volume;
  private final int instrument, value;

  /**
   * Constructor for a Note.
   * @param startBeat the starting {@code beat} of the {@code note}.
   * @param beats the duration of the {@code note} in total beats.
   * @param pitch the {@link Pitch} of the {@code note}.
   * @param octave the {@code octave} of the {@code note}.
   * @param instrument the {@code instrument} of the {@code note}.
   * @throws IllegalArgumentException
   */
  public Note(int startBeat, int beats, int pitch, int octave, int instrument, int volume)
          throws IllegalArgumentException {
    if (startBeat < 0 || beats < 0)
      throw new IllegalArgumentException("Invalid note.");

    if (octave < 0)
      throw new IllegalArgumentException("Invalid octave.");

    if (pitch < 0 || pitch > 12)
      throw new IllegalArgumentException("Invalid pitch.");

    if (instrument < 0)
      throw new IllegalArgumentException("Invalid instrument.");

    if (volume < 0)
      throw new IllegalArgumentException("Invalid volume.");

    this.startBeat = startBeat;
    this.beats = beats;
    this.pitch = Pitch.toPitch(pitch);
    this.octave = octave;
    this.volume = volume;
    this.endBeat = startBeat + (beats - 1);
    this.instrument = instrument;
    this.value = (12 * ++octave) + pitch;
  }

  public Note(int startBeat, int beats, int pitch, int octave) {
    this(startBeat, beats, pitch, octave, 1, 64);
  }

  public int getValue() {
    return value;
  }

  public int getStartBeat() {
    return startBeat;
  }

  public void setStartBeat(int beat) {
    startBeat = beat;
  }

  public int getBeats() {
    return beats;
  }

  public void setBeats(int beats) {
    this.beats = beats;
  }

  public int getEndBeat() {
    return endBeat;
  }

  public void setEndBeat(int beat) {
    endBeat = beat;
  }

  public int getPitch() {
    return pitch.ordinal();
  }

  public void setPitch(int pitch) {
    this.pitch = Pitch.toPitch(pitch);
  }

  public int getOctave() {
    return octave;
  }

  public void setOctave(int octave) {
    this.octave = octave;
  }

  public int getInstrument() {
    return instrument;
  }

  public int getVolume() {
    return volume;
  }

  /**
   * Returns a String representation of a {@code Note}.
   * @return a String of the {@code Note}.
   */
  @Override
  public String toString() {
    return pitch.toString() + octave;
  }

  /**
   * Determines if two {@code Note}s are deemed to be equivalent.
   * @param that the {@code Note} being compared to this.
   * @return true if they are equivalent, false otherwise.
   */
  @Override
  public boolean equals(Object that) {
    if (this == that)
      return true;
    if (!(that instanceof Note))
      return false;
    Note note = (Note) that;
    return this.pitch == note.pitch && this.octave == note.octave &&
            this.getBeats() == note.getBeats() &&
            this.getStartBeat() == note.getStartBeat();
  }

  /**
   * Hashes a value for the {@code Note} based on its {@code pitch}, {@code octave},
   * {@code beat} length, and starting {@code beat}.
   * @return an integer representation of the {@code Note}.
   */
  @Override
  public int hashCode() {
    return Objects.hash(pitch, getBeats(), octave, getStartBeat());
  }

}
