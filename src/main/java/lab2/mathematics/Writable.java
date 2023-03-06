package lab2.mathematics;

import java.io.FileWriter;

public abstract class Writable {

    private FileWriter writer = null;

    public void setWriter(FileWriter writer){
        this.writer = writer;
    }

    protected void tryWriteToFile(String[] data){
        if(writer != null){
            try {
                writer.write(String.join(",", data) + "\n");
            }catch (Exception e){
                System.out.println("Не удалось записать в файл");
            }
        }
    }
}
