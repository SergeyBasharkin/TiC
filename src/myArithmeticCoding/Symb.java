package myArithmeticCoding;

public class Symb {
    private String symbol;
    private Segment segment;

    public Symb(String symbol, Segment segment) {
        this.symbol = symbol;
        this.segment = segment;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Segment getSegment() {
        return segment;
    }

    public void setSegment(Segment segment) {
        this.segment = segment;
    }
}
