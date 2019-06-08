package bg.sofia.uni.fmi.mjt.git;

import java.util.LinkedHashSet;
import java.util.Set;

public class StageAreaAdd {
    private Set<String> files;

    public StageAreaAdd() {
        this.files = new LinkedHashSet<>();
    }

    public void add(String... filesForAdding) {
        for (String file : filesForAdding) {
            files.add(file);
        }
    }

    public int getNumberOfFiles() {
        return files.size();
    }

    public void clearStage() {
        files.clear();
    }

    public boolean isFileContained(String file) {
        return files.contains(file);
    }

    public Set<String> getAll() {
        return files;
    }

    public void remove(String file) {
        files.remove(file);
    }
}
