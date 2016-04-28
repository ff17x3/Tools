package util;

public class PointF {
    public final float x;
    public final float y;
    public boolean deleted;

    public PointF(float x, float y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "(" + x + "|" + y + ")";
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof PointF && ((PointF) obj).x == x && ((PointF) obj).y == y;
    }

    public void delete() {
        deleted = true;
    }

    public boolean isDeleted() {
        return deleted;
    }

}
