package com.trifonov.konstantin.trainingjournal;

import android.os.Environment;
import android.util.Log;

import com.opencsv.CSVReader;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVFile {
    final String LOG_TAG = "trainingJournal";

    private File open(String rootName, String fileName) throws IOException {
        if (!isExternalStorageWritable()) {
            Log.e(LOG_TAG, "Storage is not writable");
            return null;
        }
        File root = getDownloadsStorageDir(rootName);
        File file = new File(root, fileName);
        if (!file.exists()) {
            file.createNewFile();
        }
        return file;
    }

    public List<String[]> read(String root, String name) throws IOException {
        List<String[]> strings;
        try {
            File file = this.open(root, name);
            FileReader fileReader = new FileReader(file);
            CSVReader reader = new CSVReader(fileReader, ',', '"');
            strings = reader.readAll();
            reader.close();
        } catch (Exception e) {
            strings = new ArrayList<>();
        }
        return strings;
    }

    public void append(String root, String name, String string) throws IOException {
        if (!isExternalStorageWritable()) {
            Log.e(LOG_TAG, "Storage is not writable");
            return;
        }
        File file = this.open(root, name);
        FileWriter writer = new FileWriter(file, true);
        writer.append(string);
        writer.append("\n");
        writer.flush();
        writer.close();
    }

    private boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state);
    }

    private File getDownloadsStorageDir(String rootName) {
        File file = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOWNLOADS), rootName);
        if (!file.mkdirs()) {
            Log.e(LOG_TAG, "Directory not created");
        }
        return file;
    }
}







