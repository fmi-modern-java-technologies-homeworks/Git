package bg.sofia.uni.fmi.mjt.git;

import java.util.Set;

public class StageAreaAdd {
    private Set<String> files;
//    private Set<String> filesForRemove;

    public void add(String... filesForAdding) {
        for (String file : files) {
            files.add(file);
        }

    }

    public int getNumberOfFiles() {
        return files.size();
    }

    public void clearStage() {
        files.clear();
    }

    public boolean isFileContined(String file) {
        return files.contains(file);
    }

    //add
    //remove
    //count/number
    //clear

    //files for add contains
}
