public class DeleteTextCommand implements Command{
    private StringBuilder text;
    private int start;
    private int end;
    private String deletedText;

    public DeleteTextCommand(StringBuilder text, int start, int end) {
        this.text = text;
        this.start = start;
        this.end = end;
    }

    @Override
    public void execute() {
        deletedText = text.substring(start, end);
        text.delete(start, end);
    }

    @Override
    public void undo() {
        text.insert(start, deletedText);
    }
}
