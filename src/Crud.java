public interface Crud<T> {
    public void create(T t);
    public void read();
    public void update(T t);
    public void delete(int t);
}
