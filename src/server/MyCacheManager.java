package server;

import classes.Solution;

import java.io.FileNotFoundException;
import java.io.IOException;

public class MyCacheManager<T> implements CacheManager<Solution<T>>{
    @Override
    public boolean isFileExist(String fileName) {
        return false;
    }

    @Override
    public Solution<T> loadFile(String fileName) throws FileNotFoundException, IOException, ClassNotFoundException {
        return null;
    }

    @Override
    public void saveFile(String fileName, Solution<T> file) throws IOException {

    }
}
