package myArithmeticCoding;

import java.math.BigDecimal;

public class Segment {
    private BigDecimal left;
    private BigDecimal right;

    public Segment(BigDecimal left, BigDecimal right) {
        this.left = left;
        this.right = right;
    }

    public BigDecimal getLeft() {
        return left;
    }

    public void setLeft(BigDecimal left) {
        this.left = left;
    }

    public BigDecimal getRight() {
        return right;
    }

    public void setRight(BigDecimal right) {
        this.right = right;
    }
}
