public class SudokuCell {
    private int value;
    private boolean fixed;

    public SudokuCell(int value, boolean fixed){
        this.value = value;
        this.fixed = fixed;
    }

    public int getValue(){
        return this.value;
    }

    public void setValue(int value){
        this.value = value;
    }

    public boolean getFixed(){
        return this.fixed;
    }

    public void setFixed(boolean fixed){
        this.fixed = fixed;
    }
}
