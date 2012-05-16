package au.com.realestate.test.operations;

public class TextInput extends ReaBaseScreen {

    private String elementId;
    private String text;

    public TextInput(String id, String text) {
        this.elementId = id;
        this.text = text;
    }

    @Override
    public void action() {
        helper.findDisplayedElementById(elementId).sendKeys(text);
    }

    public void clear() {
        helper.findDisplayedElementById(elementId).clear();
    }
}
