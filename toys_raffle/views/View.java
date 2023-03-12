package toys_raffle.views;

public interface View {
    /**
     * Shows main menu
     */
    public void MainMenu();

    /**
     * Gets an Integer value from user's input
     * @param message   User's prompt
     * @return          Parsed integer value
     */
    public int getInt(String message);

    /**
     * Gets a percentage value from user's input
     * @param message   User's prompt
     * @return          Parsed percentage value
     */
    public int getPercent(String message);

    /**
     * Gets a string value from user's input
     * @param message   User's prompt
     * @return          Parsed String value
     */
    public String getString(String message);

    /**
     * Gets a boolean value from user's input
     * @param message   User's prompt
     * @return          Parsed boolean value
     */
    public boolean getBoolean(String message);

    /**
     * Shows message to user
     * @param msg   Message to show
     */
    public void ShowMsg(String msg);
    
    /**
    * Shows message to user (adds newlines at the end of message)
     * @param msg       Message to show
     * @param newLines  How much new lines should I add after the message
     */
    public void ShowMsg(String msg, int newLines);
}
