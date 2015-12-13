/*
 * We made this interface, so that the communication
 * occurs within the Controller only which is the MainFrame.java
 * In order to prevent any communications between
 * the components (Toolbar and TextPanel)
 * here, the Toolbar defines an object of type
 * StringListener and pass the text parameter to it
 * based on which button has been pressed
 * (Hello or Goodbye)
 * and then the MainFrame implements this interface,
 * so it must implement the unimplemented function
 * (public void textEmitted(String text);)
 * so it implements it with appending the text panel
 * with the text string parameter that was recently
 * provided by the Toolbar
 */


public interface StringListener {
	public void textEmitted(String text);
}
