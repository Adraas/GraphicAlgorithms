package sample.controller.clone;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class BooleanArray2DCloner<T> implements Serializable {
    private static final long serialVersionUID = 2919017661809385476L;
    private T arr;

    public BooleanArray2DCloner(T arr) {
        super();
        this.arr = arr;
    }

    public T getArray() {
        return arr;
    }

    public BooleanArray2DCloner clone() {
        BooleanArray2DCloner duplicate = null;
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(this);
            objectOutputStream.close();

            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            duplicate = (BooleanArray2DCloner) objectInputStream.readObject();
            objectInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return duplicate;

    }
}