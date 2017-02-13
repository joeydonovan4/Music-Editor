package cs3500.music.controller;

import static org.junit.Assert.*;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.awt.event.KeyEvent;
import java.awt.Container;
import java.io.PrintStream;


/**
 * Created by joeydonovan on 6/25/16.
 */
public class KeyboardHandlerTest {

  private final ByteArrayOutputStream outStream = new ByteArrayOutputStream();
  private KeyboardHandler kh = new KeyboardHandler();

  @Test
  public void testKeyPressed() {
    System.setOut(new PrintStream(outStream));

    Runnable keyPress0 = () -> System.out.print("key 0 pressed ");
    Runnable keyPress1 = () -> System.out.print("key 1 pressed ");
    Runnable keyPress2 = () -> System.out.print("key 2 pressed");

    kh.setKeyPressed(0, keyPress0);
    kh.setKeyPressed(1, keyPress1);
    kh.setKeyPressed(2, keyPress2);

    kh.keyPressed(new KeyEvent(new Container(), 0, 0, 0, 0, ' '));
    assertEquals("key 0 pressed ", outStream.toString());

    kh.keyPressed(new KeyEvent(new Container(), 0, 0, 0, 1, ' '));
    assertEquals("key 0 pressed key 1 pressed ", outStream.toString());

    kh.keyPressed(new KeyEvent(new Container(), 0, 0, 0, 2, ' '));
    assertEquals("key 0 pressed key 1 pressed key 2 pressed", outStream.toString());
  }

  @Test
  public void testKeyReleased() {
    System.setOut(new PrintStream(outStream));

    Runnable keyRelease0 = () -> System.out.print("key 0 released ");
    Runnable keyRelease1 = () -> System.out.print("key 1 released ");
    Runnable keyRelease2 = () -> System.out.print("key 2 released");

    kh.setKeyReleased(0, keyRelease0);
    kh.setKeyReleased(1, keyRelease1);
    kh.setKeyReleased(2, keyRelease2);

    kh.keyReleased(new KeyEvent(new Container(), 0, 0, 0, 0, ' '));
    assertEquals("key 0 released ", outStream.toString());

    kh.keyReleased(new KeyEvent(new Container(), 0, 0, 0, 1, ' '));
    assertEquals("key 0 released key 1 released ", outStream.toString());

    kh.keyReleased(new KeyEvent(new Container(), 0, 0, 0, 2, ' '));
    assertEquals("key 0 released key 1 released key 2 released", outStream.toString());
  }

  @Test
  public void testKeyTyped() {
    System.setOut(new PrintStream(outStream));

    Runnable keyType0 = () -> System.out.print("key 0 typed ");
    Runnable keyType1 = () -> System.out.print("key 1 typed ");
    Runnable keyType2 = () -> System.out.print("key 2 typed");

    kh.setKeyTyped(0, keyType0);
    kh.setKeyTyped(1, keyType1);
    kh.setKeyTyped(2, keyType2);

    kh.keyTyped(new KeyEvent(new Container(), 0, 0, 0, 0, ' '));
    assertEquals("key 0 typed ", outStream.toString());

    kh.keyTyped(new KeyEvent(new Container(), 0, 0, 0, 1, ' '));
    assertEquals("key 0 typed key 1 typed ", outStream.toString());

    kh.keyTyped(new KeyEvent(new Container(), 0, 0, 0, 2, ' '));
    assertEquals("key 0 typed key 1 typed key 2 typed", outStream.toString());
  }
}