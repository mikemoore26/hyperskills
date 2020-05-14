package flashcards;

public class FlashCard {

    private String term;
    private String def;
    private int mistake;

    public FlashCard(String term, String def) {
        this.term = term;
        this.def = def;
        this.mistake = 0;
    }

    public FlashCard(String term, String def, int mistake) {
        this.term = term;
        this.def = def;
        this.mistake = mistake;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getDef() {
        return def;
    }

    public void setDef(String def) {
        this.def = def;
    }

    public int getMistake() {
        return mistake;
    }

    public void setMistake(int mistake) {
        this.mistake = mistake;
    }

    public void wrongAnswer(){
        mistake++;
    }

    @Override
    public String toString() {
        return  term;
    }
}
