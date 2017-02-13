Joe Donovan
Victor Liang
Assignment #7

For this week’s homework, we added:
1) A controller interface and a subsequent class. The controller was used to take in user interaction with the GUI and process/handle any inputs that the user may have had.
2) A GuiView sub-interface, which extended EditorView. This interface was only implemented by classes that were dealing directly with the GUI view. Therefore, the MIDI and console output views had nothing to do with GuiView.
3) A composite view class which implemented GuiView. The composite view allowed for both the MIDI view and the GUI view to simultaneously run.
4) A keyboard handler and a mouse handler class. These classes implemented KeyboardListener and MouseListener, which responded to user inputs via the keyboard and mouse. The handler classes created mappings of Runnable methods which occurred after certain user inputs occurred.
5) An IModelToView interface, which ModelToView implemented. We figured this would be helpful. The interface extended IMusicEditorModel.
6) The ability for a user to remove/add notes via the GUI. I will explain those features below.
7) A Sequencer that replaced our old Timer implementation. We realized that a Sequencer would be much more optimal for this homework, so we added it.
8) A ticker on the GUI which represented the current beat. This was synchronized with the MIDI player, so that the current beat represented by the ticker was corresponding with the current beat of the MIDI player.
9) The ability to escape the application.

Details:

1) The controller class stored instances of the Model and the GuiView that were to be rendered. When the controller was instantiated, a default keyboard and mouse handler were both added to the JFrame and JPanel. In order to have the MIDI and the GUI sync with one another, the controller used a Timer that went off frequently. Every time the timer went off, the program would check if the GUI needed updating. If so, the GuiView and MIDI would respond accordingly. The controller stored the following key inputs and mouse inputs:

	1) Spacebar: Used to pause/resume the music.
	2) Left/Right Arrows: Used to scroll the horizontal scroll bar.
	3) Up/Down Arrows: Used to scroll the vertical scroll bar.
	4) Home Key: Used to jump to the beginning of the composition.
	5) End Key: Used to jump to the end of the composition.
	6) Escape Key: Used to prematurely quit the program.
	7) Backspace: Used to delete any selected Notes in the composition.
	8) A Key: Used to add new Notes to the composition.
	9) Shift Key: Used to add a new mouse handler that could detect clicks when the shift key 	was pressed down, and also removed that handler when the shift key was released.
	10) Left Click: Used to select Notes in the composition. In the standard mouse handler, 	only one note could be selected at a time. With the space bar held down, a user could 	select multiple Notes.

2) The GuiView interface stored methods that only the composition view and the GuiViewImpl could use, as they were specific to the user interacting with the GUI. These methods were the handling methods that were called once any of the aforementioned keys above were pressed.

3) The composite view class is where the program knew how to sync the MIDI and the GUI view together. The composite view kept a variable, currentBeat, that was constantly updated to the current beat of the MIDI player. This is how the GUI could access the current beat of the MIDI constantly, without actually interacting with the MIDI directly itself.

4) The keyboard handler implemented the KeyListener interface and therefore overrode its keyTyped, keyPressed, and keyReleased methods. We created Hashmaps which stored the keys that were pressed, and the corresponding Runnable methods that were to be run once the key was pressed. The mouse handler essentially did the same thing. The mouse handler could respond to the mouse entering and exiting components, clicking within components, and pressing and releasing as well.

5) The IModelToView interface was created in order to have better design for our ModelToView class, which was being used by all the views in order to gain access to the model’s data. The interface extended the IMusicEditorModel interface, so it was in direct contact with the model itself. However, the views were still kept blind to the model’s actual implementation.

6) Removing notes:

The user could only remove notes when the composition had been paused. When the composition was paused, the user could click on any Note in the composition and it would be selected. When selected, a red box would appear around the Note and the program would store whichever Note had been selected in a data structure. If the user held down the Shift key, they could select multiple notes. Without the shift key, only one Note could be selected at a time. To delete notes, the user would have to press the backspace key. When this happened, the program would check if any notes were selected at the time the key was pressed. If so, they would be instantly removed from the composition, and the GUI would repaint itself. We also made it so the JPanel would resize itself according to which Notes were still remaining on the sheet of music.

Adding notes:

The user could only add notes when the composition had been paused. When the composition was paused, the user could press the ‘A’ key to add a Note. When pressed, a JOptionPane would appear that would give the user the option to add a new Note to the composition. It would ask for details of the Note, such as: starting beat, duration, pitch, and octave. This allowed for Notes that were currently not rendered on the sheet of music to be added. If that did happen, the sheet of music would repaint itself appropriately.

When notes were added/removed, the MIDI was refreshed in order to be able to play the Notes that had been added, or not play the Notes that had been removed.

7) We originally used a Timer in previous week’s assignments in order to play the MIDI. However, we saw that using a Sequencer would certainly make everything much simpler, and it seemed like a much more elegant solution. Therefore we implemented it. A sequence was created with a single track that contained all the Notes. If notes were added by the user however, a new track was created that contained all the new Notes in the composition.

8) The ticker moved across the GUI screen as the song was playing. When paused, the ticker would also pause as well. Once the ticker reached the end of the visible part of the music editor, the JFrame would reposition and scroll so that the ticker would be on the leftmost side. So the ticker would move across the screen, and the notes wouldn’t be moving underneath the ticker.

9) Whenever the program was opened, the user had the ability to press the Escape key to immediately quit the program.

Some changes:

We originally had the JPanel of the pitches in the composition built without using painting. However, this assignment called for the adding and removing of notes. When the highest/lowest notes were removed from the music editor, the sheet of music would repaint itself to be as big as it should be. When this happened, the pitches were not refreshing since they had not been painted. Therefore, we discarded our JLabel approach of last week and painted all the notes. This gave us the ability to refresh the pitches accordingly.