Joe Donovan
Victor Liang
Music Editor Model Assignment

My design contains:
1) A model for a MusicEditor and an interface that the model implements.
2) A Pitch enumeration that represents all possible pitches in a single octave.
3) A Note class that represents a possible note that could be played in a music editor. There is also an interface INote that Note implements.
4) A Note comparator that is used to compare notes based on their given value, which is based off of pitch and octave.

The following have been added on in this week’s assignment:
5) A given composition builder that creates a composition that can be rendered.
6) A given music reader that parses a given text file and generates a model and view based on the text information.
7) An editor view interface that can be implemented by any view type that wants to be rendered.
8) A console view that implements the editor view interface. This view takes a song and renders the song’s output represented as a String on the console.
9) A MIDI view that implements the editor view interface. This view takes a song and renders the song’s output represented as actual audio that is played through a MIDI.
10) A GUI view that implements the editor view interface. This view takes a song and renders the song’s output represented as a GUI. The GUI shows a sheet of music with the song’s notes rendered as colored blocks on the sheet of music.
11) A class ModelToView that the given views use to access proper data from the model that wants to be rendered. This is used to conceal certain data from the view, and gives the view only what it needs from the model to complete a rendering.
12) A factory class ViewFactory that generates an EditorView based on a String input from the user. The user can render a view by entering the String that corresponds with the rendering of that view.
13) A MusicEditor class that contains the main method. The main method takes two program arguments from the user: the type of view that they want to have rendered and the text file that they want to have rendered.
14) A Mock synthesizer and a mock receiver, used for testing the MIDI output. A convenience constructor is used in the MIDI view that renders the mock synthesizer and receiver, which are programmed to output the notes that are being “played” by the MIDI as Strings, rather than actual audio. This makes it much simpler to test the correctness of the MIDI implementation.

Details of the view implementations:

1) Console View:
Implements the render method from the EditorView interface.
The render method takes a ModelToView instance, and prints the String representation of the model to the console.

2) MIDI View:
Implements the render method from the EditorView interface.
The MIDI implementation uses the Java Timer class as a way to play the notes at the correct time. We store the Synthesizer and Receiver in the class, as well as a Timer field and an integer representing the current beat of the song.
When the render method is called, a new TimerTask is created, that being a BeatTask. A BeatTask extends the TimerTask class and takes a ModelToView instance in its constructor. The Timer is scheduled to run the BeatTask at a fixed rate that is dependent on the tempo of the given song. The default method that is called in a TimerTask is run(). In the BeatTask class, the run method is overriden. The method gets all the Notes that are playing at the current beat of the song, and for each note, checks if the note’s starting beat or ending beat is on the current beat. If so, the corresponding method is called (which is either playNote or stopNote). Play note sends a message to the receiver that tells the MIDI to play the given Note and gets the correct instrument, pitch, octave, etc. by accessing the Note’s public methods. Stop note does the same thing except it tells the receiver to stop the note that has been playing. Once the current beat of the song reaches the final beat of the song, the receiver is closed for good. The System then waits 3 seconds then exits.

3) GUI View:
Implements the render method from the EditorView interface.
The GUI implementation extends the JFrame class as well. The fields that the GUI has are a JFrame, a JPanel, a JScrollPane, and a static variable representing the size of a block to be rendered on the GUI. The constructor instantiates the JFrame to have a title, the JPanel to contain a Border Layout, and the JScrollPane to contain the JPanel internally.
The implementation’s render method first generates the sheet of music background that the notes will be placed on. Doing this involves the implementation knowing the range of the Notes in the model. (e.g. how many pitches should be shown on the y-axis of the music sheet). The sheet is rendered properly for the range of pitches in the song and as long as the amount of beats there are in the song, with all the Notes being added at their correct positions. A JPanel is then rendered to represent the pitches to be shown on the left side of the music sheet, and another JPanel is rendered to represent the beats at every 16th beat. The JPanels are formatted correctly on the larger JPanel container. The JScrollPane is then rendered, by setting unit increments for scrolling and setting properties that maintain that the scroll bars will only be there once the size of the window is less than the preferred size. Then the JScrollPane is added to the JFrame, and the contents of the JFrame are packed.