package cs3500.music.model;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by joeydonovan on 6/10/16.
 */
public class PitchTest {

  @Test
  public void testToString() {
    Pitch pitch = Pitch.toPitch(3);
    assertEquals("D#", pitch.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testToPitchInvalidPitchNo() {
    Pitch.toPitch(15);
  }

  @Test
  public void testToPitch() {
    assertEquals(Pitch.G, Pitch.toPitch(7));
  }
}