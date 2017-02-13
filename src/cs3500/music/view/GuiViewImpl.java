package cs3500.music.view;

import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import cs3500.music.model.INote;
import cs3500.music.model.Note;

import javax.swing.*;

/**
 * A skeleton Frame (i.e., a window) in Swing
 */
public class GuiViewImpl extends javax.swing.JFrame implements GuiView {

  private IModelToView mtv;
  private JPanel displayPanel;
  private final JFrame frame;
  private final JScrollPane scroll;
  static final int BLOCKSIZE = 20;

  /**
   * Constructor for a {@link cs3500.music.model.IMusicEditorModel} GUI.
   */
  public GuiViewImpl() {
    displayPanel = new JPanel(new BorderLayout());
    frame = new JFrame("Music Editor");
    scroll = new JScrollPane(displayPanel);
  }

  @Override
  public Dimension getPreferredSize(){
    return displayPanel.getPreferredSize();
  }

  /**
   * Renders a GUI Frame.
   * @param mtv the model to be shown in the view.
   */
  @Override
  public void render(IModelToView mtv) {
    this.mtv = mtv;
    MusicSheetPanel panel = new MusicSheetPanel(mtv);
    displayPanel.add(beatsPanel(mtv), BorderLayout.NORTH);
    displayPanel.add(panel);
    addScrollPane();
    frame.setBackground(Color.WHITE);
    frame.add(scroll, BorderLayout.CENTER);
    frame.pack();
    scroll.getHorizontalScrollBar().setUnitIncrement(frame.getWidth() / 16);
    scroll.getVerticalScrollBar().setUnitIncrement(frame.getHeight() / 16);
    frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    frame.setVisible(true);
  }

  @Override
  public IModelToView getMTV() {
    return mtv;
  }

  /**
   * Pauses the {@link cs3500.music.model.IMusicEditorModel} if it was playing,
   * and vice versa.
   */
  @Override
  public void handleSpaceBar() {

  }

  /**
   * Generates a {@link JOptionPane} for the client to enter data regarding
   * the new {@link Note} that they would like to add to the
   * {@link cs3500.music.model.IMusicEditorModel}.
   */
  @Override
  public void handleAKey() {
    if (mtv.isPaused()) {
      JTextField field1 = new JTextField();
      JTextField field2 = new JTextField();
      JTextField field3 = new JTextField();
      JTextField field4 = new JTextField();
      Object[] message = {
              "Start Beat:", field1,
              "Length of Beat:", field2,
              "Pitch:", field3,
              "Octave:", field4,
      };
      int option = JOptionPane.showConfirmDialog(frame, message, "Enter all your values",
              JOptionPane.OK_CANCEL_OPTION);
      try {
        if (option == JOptionPane.OK_OPTION) {
          String value1 = field1.getText();
          String value2 = field2.getText();
          String value3 = field3.getText();
          String value4 = field4.getText();

          mtv.addNote(new Note(Integer.parseInt(value1),
                  Integer.parseInt(value2),
                  Integer.parseInt(value3),
                  Integer.parseInt(value4)));
          JOptionPane.showMessageDialog(frame, "Successfully added note");

        }
      } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(frame, "Incorrect Input! Could not add note!");
      }
    }
  }

  /**
   * Moves the horizontal {@link JScrollBar} to the left by its
   * unit increment.
   */
  @Override
  public void handleLeftArrow() {
    JScrollBar sb = scroll.getHorizontalScrollBar();
    sb.setValue(sb.getValue() - sb.getUnitIncrement());
  }

  /**
   * Moves the horizontal {@link JScrollBar} to the right by its
   * unit increment.
   */
  @Override
  public void handleRightArrow() {
    JScrollBar sb = scroll.getHorizontalScrollBar();
    sb.setValue(sb.getValue() + sb.getUnitIncrement());
  }

  /**
   * Moves the vertical {@link JScrollBar} up by its
   * unit increment.
   */
  @Override
  public void handleUpArrow() {
    JScrollBar sb = scroll.getVerticalScrollBar();
    sb.setValue(sb.getValue() - sb.getUnitIncrement());
  }

  /**
   * Moves the vertical {@link JScrollBar} down by its
   * unit increment.
   */
  @Override
  public void handleDownArrow() {
    JScrollBar sb = scroll.getVerticalScrollBar();
    sb.setValue(sb.getValue() + sb.getUnitIncrement());
  }

  /**
   * Moves the horizontal {@link JScrollBar} to the end
   * of the {@link cs3500.music.model.IMusicEditorModel}.
   */
  @Override
  public void handleEnd() {
    JScrollBar sb = scroll.getHorizontalScrollBar();
    sb.setValue(mtv.getLengthInBeats() * BLOCKSIZE);
  }

  /**
   * Moves the horizontal {@link JScrollBar} to the beginning
   * of the {@link cs3500.music.model.IMusicEditorModel}.
   */
  @Override
  public void handleHome() {
    JScrollBar sb = scroll.getHorizontalScrollBar();
    sb.setValue(0);
  }

  /**
   * Exits the program.
   */
  @Override
  public void handleEscape() {
    System.exit(0);
  }

  /**
   * Removes {@link Note}s that have been selected by the client.
   */
  @Override
  public void handleRemove() {
    if (!mtv.getSelected().isEmpty()) {
      mtv.removeMultipleNotes(mtv.getSelected().toArray(new INote[mtv.getSelected().size()]));
      mtv.getSelected().clear();
    }
  }

  /**
   * Selects the {@link Note} that has been clicked on. If no {@link Note} has
   * been clicked on, clear selected notes.
   */
  @Override
  public void handleLeftClick() {
    int beat = (int) Math.floor(displayPanel.getMousePosition().getX() / BLOCKSIZE) - 2;
    int value = (int) (mtv.getHighestNote() - Math.floor(displayPanel.getMousePosition().getY()
            / BLOCKSIZE) + 1);
    if (clickedNote(beat, value) != null) {
      if (!mtv.getSelected().isEmpty())
        mtv.getSelected().clear();

      mtv.addSelected(clickedNote(beat, value));
    }
    else
      mtv.getSelected().clear();
  }

  /**
   * Selects the {@link Note} that has been clicked on, while keeping all other
   * selected {@link Note}s selected.
   */
  @Override
  public void handleShiftLeftClick() {
    int beat = (int) Math.floor(displayPanel.getMousePosition().getX() / BLOCKSIZE) - 2;
    int value = (int) (mtv.getHighestNote() - Math.floor(displayPanel.getMousePosition().getY()
            / BLOCKSIZE) + 1);
    if (clickedNote(beat, value) != null)
      mtv.addSelected(clickedNote(beat, value));
    else
      mtv.getSelected().clear();
  }

  /**
   * Adds a {@link KeyListener} to the {@link JFrame}.
   * @param keyListener the {@code KeyListener} to be added.
   */
  @Override
  public void addKeyListener(KeyListener keyListener) {
    frame.addKeyListener(keyListener);
  }

  /**
   * Adds a {@link MouseListener} to the {@link JPanel}.
   * @param mouseListener the {@code MouseListener} to be added.
   */
  @Override
  public void addMouseListener(MouseListener mouseListener) {
    displayPanel.addMouseListener(mouseListener);
  }

  /**
   * Removes a {@link MouseListener} from the {@link JPanel}.
   * @param mouseListener the {@code MouseListener} to be removed.
   */
  @Override
  public void removeMouseListener(MouseListener mouseListener) {
    displayPanel.removeMouseListener(mouseListener);
  }

  /**
   * Scrolls the {@link JPanel} so that the ticker moves across the screen.
   * Once the ticker reaches the end, the {@link JPanel} is updated.
   */
  @Override
  public void update() {
    if (CompositeViewImpl.currentBeat * GuiViewImpl.BLOCKSIZE %
            scroll.getVisibleRect().getSize().width == 0)
      scroll.getHorizontalScrollBar().
              setValue(CompositeViewImpl.currentBeat * GuiViewImpl.BLOCKSIZE);
  }

  /**
   * Gets the clicked {@link INote} by the client.
   * @param beat the beat that has been clicked.
   * @param value the value that has been clicked.
   * @return the {@code note} that has been clicked.
   */
  private INote clickedNote(int beat, int value) {
    if (beat < 0)
      return null;
    for (INote n : mtv.getNotesAtBeat(beat)) {
      if (n.getValue() == value)
        return n;
    }
    return null;
  }

  /**
   * Adds a scroll pane to the frame.
   */
  private void addScrollPane() {
    scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
    scroll.setWheelScrollingEnabled(true);
  }

  /**
   * Creates a Panel of the {@code beats} to be displayed on the frame.
   * @param mtv the model to be shown in the view.
   * @return a horizontal panel of {@code beats}.
   */
  private JPanel beatsPanel(IModelToView mtv) {
    JPanel beats = new JPanel();
    beats.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
    beats.setBorder(BorderFactory.createEmptyBorder(0, 25 + BLOCKSIZE, 0, 0));
    for (int i = 0; i <= mtv.getLengthInBeats(); i += mtv.getMeasureLength()) {
      Box beatBox = Box.createHorizontalBox();
      beatBox.setMinimumSize(new Dimension(BLOCKSIZE * 4, BLOCKSIZE));
      beatBox.setMaximumSize(new Dimension(BLOCKSIZE * 4, BLOCKSIZE));
      beatBox.setPreferredSize(new Dimension(BLOCKSIZE * 4, BLOCKSIZE));
      if (i % 16 == 0) {
        JLabel beat = new JLabel(Integer.toString(i), SwingConstants.LEFT);
        beatBox.add(beat);
      }
      beats.add(beatBox);
    }
    return beats;
  }
}
