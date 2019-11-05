package org.shrewsburyrobotics.datalab.datawrite;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.shrewsburyrobotics.datalab.datafetch.requestTypes;

public class fileWriter {
    public fileIndex mfileIndex;
    public int mFileIndexNumber;
    public File file = new File("C://Users//Team467//Documents//testcsvs");
    public File mTestFile;
    private String fileName;
    private String mData;

    public fileWriter(String data, String name) {
        mData = data;
        fileName = name;
    }

    public void writeToFile() throws IOException {
        //check to see if file exists
        mTestFile = new File("C://Users//Team467//Documents//testcsvs" + "//" + fileName + "_" + mfileIndex.fileIndex + ".csv");
             if(mTestFile.isFile()) {
                mfileIndex.fileIndex++;
                System.out.println("file exists writing new file with " + mfileIndex.fileIndex);
            } else {
                System.out.println("file does not exist");
                if(mFileIndexNumber != 0) {
                    mFileIndexNumber = 0;
                }
            }



        FileOutputStream out = new FileOutputStream(file + "//" + fileName + "_" + mfileIndex.fileIndex + ".csv");
        out.write(mData.getBytes());
        out.close();
    }
}