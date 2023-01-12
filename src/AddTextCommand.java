public class AddTextCommand implements Command{
    private StringBuilder text;
    private String str;

    public AddTextCommand(StringBuilder text, String str) {
        this.text = text;
        this.str = str;
    }

    @Override
    public void execute() {
        text.append(str);
    }

    @Override
    public void undo() {
        int start = text.length() - str.length();
        int end = text.length();
        text.delete(start, end);
    }
}
