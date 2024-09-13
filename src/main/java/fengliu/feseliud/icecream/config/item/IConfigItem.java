package fengliu.feseliud.icecream.config.item;

public interface IConfigItem<T> {
    T get();
    void set(T data);
    String getKey();
    String getDescription();
}
