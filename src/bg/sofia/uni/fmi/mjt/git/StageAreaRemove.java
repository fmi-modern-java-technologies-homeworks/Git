package bg.sofia.uni.fmi.mjt.git;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class StageAreaRemove {
    private Set<String> files;

    public StageAreaRemove() {
        this.files = new LinkedHashSet<>();
    }

    public Set<String> getAll() {
        return files;
    }

    public void add(List<String> filesForRemoving) {
        files.addAll(filesForRemoving);
    }

    public void clearStage() {
        files.clear();
    }

    public int getNumberOfFiles() {
        return files.size();
    }
    //add

    //remove
    //count/number
    //clear

    //files for add contains

}
