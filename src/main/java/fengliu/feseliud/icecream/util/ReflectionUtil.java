package fengliu.feseliud.icecream.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ReflectionUtil {

    /**
     * 获取类中所有指定类的实例
     * @param objClass 指定类
     * @param findClass 要搜索的类
     * @return 所有指定类实例列表
     */
    public static <O, F> List<? extends O> getObjects(Class<? super O> objClass, Class<? super F> findClass){
        List<O> objects = new ArrayList<>();
        for (Field field: findClass.getDeclaredFields()) {
            Object object;
            try {
                object = field.get(null);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }

            if (objClass.isInstance(object)){
                objects.add((O) object);
            }
        }
        return objects;
    }
}
