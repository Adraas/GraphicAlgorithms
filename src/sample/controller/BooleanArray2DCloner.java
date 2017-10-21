package sample.controller;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class BooleanArray2DCloner implements Serializable {
    private static final long serialVersionUID = 2919017661809385476L;
    private boolean[][] arr;

    public BooleanArray2DCloner() {
        super();
    }

    public BooleanArray2DCloner(boolean[][] arr) {
        super();
        this.arr = arr;
    }

    public boolean[][] getArray() {
        return arr;
    }

    public void setArray(boolean[][] arr) {
        this.arr = arr;
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