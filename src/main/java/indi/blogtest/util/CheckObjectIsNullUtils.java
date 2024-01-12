package indi.blogtest.util;
import java.lang.reflect.Field;
import java.lang.reflect.Type;

public class CheckObjectIsNullUtils {
    public static boolean checkObjectIsNull(Object object) {
        Field[] fields = object.getClass().getDeclaredFields();
        boolean flag = true;
        for (Field field : fields) {
            field.setAccessible(true);
            String fieldName = field.getName();
            Object fieldValue = null;
            try {
                fieldValue = field.get(object);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            String fieldTypeName = field.getType().getTypeName();
            if(fieldTypeName.equals("java.lang.String")){
                if ((fieldValue == null || fieldValue.equals("") && !fieldName.equals("blogIntro"))) {
                    flag = false;
                }
            }
        }
        return flag;
    }
}
