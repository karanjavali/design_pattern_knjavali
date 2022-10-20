public interface Iterator {
    boolean hasNext();
    Offering next();
    void moveToHead();

    void reset();
}
