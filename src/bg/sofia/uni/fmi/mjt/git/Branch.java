package bg.sofia.uni.fmi.mjt.git;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Branch {
    private String name;
    private List<Commit> commits;
    private Set<String> files;

    public Branch(String name, List<Commit> commits, Set<String> files) {
        this.name = name;
        this.commits = commits;
        this.files = files;
    }

    public String getName() {
        return name;
    }

    public void setCommits(List<Commit> newCommits) {
        this.commits = newCommits;
    }

    public void commit(Commit newCommit) {
        commits.add(newCommit);
    }

    public Commit getLastCommit() {
        return commits.get(commits.size() - 1);
    }

    public boolean isEmpty() {
        return commits.isEmpty();
    }

    public List<Commit> getAllCommits() {
        return commits;
    }

    public Result log() {
        Result result;
        if (isEmpty()) {
            result = new Result("branch " + name + " does not have any commits yet", false);
        } else {
            result = new Result(getBranchLogMessage(), true);
        }
        return result;
    }

    public String getBranchLogMessage() {
        StringBuilder s = new StringBuilder();
        String commitMessage;
        for (int i = commits.size() - 1; i > 0; --i) {
            commitMessage = commits.get(i).getLogMessage();
            s.append(commitMessage + "\n\n");
        }
        final int INDEX_OF_FIRST_COMMIT = 0;
        commitMessage = commits.get(INDEX_OF_FIRST_COMMIT).getLogMessage();
        s.append(commitMessage);
        return s.toString();
    }

    public Set<String> getAllFiles() {
        return new HashSet<>(files);
    }
}
