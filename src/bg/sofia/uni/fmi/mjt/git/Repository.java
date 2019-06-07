package bg.sofia.uni.fmi.mjt.git;

import java.util.*;

public class Repository {

    private Set<String> filesInRepo;
    private Branch currentBranch;
    private Map<String, Branch> branches;

    private Set<String> filesForAdd;
    private Set<String> filesForRemove;

    public Repository() {
        filesInRepo = new HashSet<>();
        branches = new HashMap<>();
        branches.put("master", new Branch("master", new ArrayList<>(), new HashSet<>()));
        currentBranch = branches.get("master");
        filesForAdd = new LinkedHashSet<>();
        filesForRemove = new LinkedHashSet<>();
    }

    public Result add(String... files) {
        StringBuilder str = new StringBuilder();
        for (String file : files) {
            if (filesInRepo.contains(file) || filesForAdd.contains(file)) {
                return new Result("'" + file + "'" + " already exists", false);
            }
            str.append(", " + file);
        }
        for (String file : files) {
            filesForAdd.add(file);
        }

        return new Result("added " + str.toString().substring(2, str.length()) + " to stage", true);
    }

    public Result commit(String message) {
        int changedFiles = filesForAdd.size() + filesForRemove.size();
        if (changedFiles == 0) {
            return new Result("nothing to commit, working tree clean", false);
        }

        filesInRepo.addAll(filesForAdd);
        filesInRepo.removeAll(filesForRemove);

        currentBranch.commit(new Commit(message, new HashSet<>(filesInRepo)));

        filesForAdd.clear();
        filesForRemove.clear();

        return new Result(changedFiles + " files changed", true);
    }

    public Result remove(String... files) {
        StringBuilder str = new StringBuilder();
        for (String file : files) {
            if (!filesInRepo.contains(file) && !filesForAdd.contains(file)) {
                return new Result("'" + file + "'" + " did not match any files", false);
            }
            str.append(", " + file);
        }
        for (String file : files) {
            if (filesForAdd.contains(file)) {
                filesForAdd.remove(file);
            } else {
                filesForRemove.add(file);
            }
        }
        return new Result("added " + str.toString().substring(2, str.length()) + " for removal", true);

    }

    public Commit getHead() {
        if (currentBranch.isEmpty()) {
            return null;
        }
        return currentBranch.getLastCommit();
    }

    public Result log() {
        return currentBranch.log();
    }

    public String getBranch() {
        return currentBranch.getName();
    }

    public Result createBranch(String name) {
        if (branches.containsKey(name)) {
            return new Result("branch " + name + " already exists", false);
        }
        branches.put(name,
                new Branch(name, new ArrayList<>(currentBranch.getAllCommits()), new HashSet<>(filesInRepo)));
        return new Result("created branch " + name, true);
    }

    public Result checkoutBranch(String name) {
        if (!branches.containsKey(name)) {
            return new Result("branch " + name + " does not exist", false);
        }
        currentBranch = branches.get(name);
        filesInRepo = new HashSet<>(branches.get(name).getAllFiles());
        return new Result("switched to branch " + name, true);
    }

    public Result checkoutCommit(String hash) {

        List<Commit> newCommits = new LinkedList<>();
        for (Commit commit : currentBranch.getAllCommits()) {
            newCommits.add(commit);
            if (commit.getHash().equals(hash)) {
                currentBranch.setCommits(newCommits);
                filesInRepo = currentBranch.getLastCommit().getFiles();
                return new Result("HEAD is now at " + hash, true);
            }
        }
        return new Result("commit " + hash + " does not exist", false);
    }
}
