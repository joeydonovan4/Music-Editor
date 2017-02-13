package cs3500.music.controller;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

import java.util.Map;
import java.util.HashMap;

/**
 * Created by joeydonovan on 6/22/16.
 */

/**
 * Represents a keyboard listener. It is configurable by the controller
 * that instantiates it.
 */
public class KeyboardHandler implements KeyListener {
  private Map<Integer, Runnable> keyTypedMap = new HashMap<>();
  private Map<Integer, Runnable> keyPressedMap = new HashMap<>();
  private Map<Integer, Runnable> keyReleasedMap = new HashMap<>();

  public void keyTyped(KeyEvent e) {
    if (keyTypedMap.containsKey(e.getKeyCode()))
      keyTypedMap.get(e.getKeyCode()).run();
  }

  public void setKeyTyped(int key, Runnable r) {
    if (!keyTypedMap.containsKey(key))
      keyTypedMap.put(key, r);
  }

  public void keyPressed(KeyEvent e) {
    if (keyPressedMap.containsKey(e.getKeyCode()))
      keyPressedMap.get(e.getKeyCode()).run();
  }

  public void setKeyPressed(int key, Runnable r) {
    if (!keyPressedMap.containsKey(key))
      keyPressedMap.put(key, r);
  }

  public void keyReleased(KeyEvent e) {
    if (keyReleasedMap.containsKey(e.getKeyCode()))
      keyReleasedMap.get(e.getKeyCode()).run();
  }

  public void setKeyReleased(int key, Runnable r) {
    if (!keyReleasedMap.containsKey(key))
      keyReleasedMap.put(key, r);
  }

}
