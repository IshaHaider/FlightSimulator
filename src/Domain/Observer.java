package src.Domain;

public interface Observer<T> {
    void update(T data);
}
