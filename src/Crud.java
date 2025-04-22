public interface Crud<T> {
    public void create();
    public void read();
    public void update(T t);
    public void delete(int t);
}
