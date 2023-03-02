package lab2.Mathematics;

import java.io.FileWriter;

public abstract class Writable {

    private FileWriter writer = null;

    public void setWriter(FileWriter _writer){
        writer = _writer;
    }

    protected void tryWriteToFile(String[] data){
        if(writer != null){
            try {
                writer.write(String.join(";", data) + "\n");
            }catch (Exception e){
                System.out.println("Неудалось записать в файл");
            }
        }
    }
}
