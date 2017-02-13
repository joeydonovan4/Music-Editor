package cs3500.music.model;

/**
 * Created by joeydonovan on 6/15/16.
 */

/**
 * Represents a Note that can be added to a {@link IMusicEditorModel}.
 */
public interface INote {

  /**
   * Gets the value of the INote, which is based on its {@link Pitch} and {@code octave}.
   * @return the value.
   */
  int getValue();

  /**
   * Gets the starting {@code beat} of the INote.
   * @return the starting beat.
   */
  int getStartBeat();

  /**
   * Sets the starting {@code beat} of the INote.
   * @param beat the beat being set as the starting beat.
   */
  void setStartBeat(int beat);

  /**
   * Gets the end {@code beat} of the INote.
   * @return the end beat.
   */
  int getEndBeat();

  /**
   * Sets the ending {@code beat} of the INote.
   * @param beat the beat being set as the ending beat.
   */
  void setEndBeat(int beat);

  /**
   * Gets the duration of the INote in total beats.
   * @return the total beats of the INote.
   */
  int getBeats();

  /**
   * Sets the duration of the INote in total beats.
   * @param beats the desired duration of the INote.
   */
  void setBeats(int beats);

  /**
   * Gets the {@link Pitch} of the INote, represented as an integer.
   * @return the integer value of the INote's {@code pitch}.
   */
  int getPitch();

  /**
   * Sets the {@link Pitch} of the INote.
   * @param pitch the integer value of the desired {@code pitch}.
   */
  void setPitch(int pitch);

  /**
   * Gets the {@code octave} of the INote.
   * @return the octave.
   */
  int getOctave();

  /**
   * Sets the {@code octave} of the INote.
   * @param octave the desired octave.
   */
  void setOctave(int octave);

  /**
   * Gets the {@code instrument} of the INote.
   * @return the instrument.
   */
  int getInstrument();

  /**
   * Gets the {@code volume} of the INote.
   * @return the volume.
   */
  int getVolume();
}
