package bg.sofia.uni.fmi.mjt.git;

import java.util.*;

public class Repository {

    private Set<String> filesInRepo;
    private Branch currentBranch;
    private Map<String, Branch> branches;

    private Set<String> filesForAdd;
    private Set<String> filesForRemove;

    public Repository() {
        initializeRepository();
    }

    private void initializeRepository() {
        filesInRepo = new HashSet<>();
        branches = new HashMap<>();
        Branch branch = new Branch("master", new ArrayList<>(), new HashSet<>());
        branches.put("master", branch);
        currentBranch = branches.get("master");
        filesForAdd = new LinkedHashSet<>();
        filesForRemove = new LinkedHashSet<>();
    }

    public Result add(String... files) {
        StringBuilder filesMessage = new StringBuilder();
        Result result;
        String message;
        for (String file : files) {
            if (filesInRepo.contains(file) || filesForAdd.contains(file)) {
                message = "'" + file + "'" + " already exists";
                result = new Result(message, false);
                return result;
            }
        }
        for (String file : files) {
            filesForAdd.add(file);
            filesMessage.append(", " + file);
        }
        message = "added " + getCleanMessage(filesMessage) + " to stage";
        result = new Result(message, true);
        return result;
    }

    private String getCleanMessage(StringBuilder filesMessage) {
        return filesMessage.toString().substring(2, filesMessage.length());
    }

    public Result commit(String message) {
        int changedFiles = filesForAdd.size() + filesForRemove.size();
        if (changedFiles == 0) {
            final String RESULT_MESSAGE = "nothing to commit, working tree clean";
            Result result = new Result(RESULT_MESSAGE, false);
            return result;
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
        Result result;
        String message;
        if (branches.containsKey(name)) {
            message = "branch " + name + " already exists";
            result = new Result(message, false);
        } else {
            Branch branch = new Branch(name, new ArrayList<>(currentBranch.getAllCommits()), new HashSet<>(filesInRepo));
            branches.put(name, branch);
            message = "created branch " + name;
            result = new Result(message, true);
        }
        return result;
    }

    public Result checkoutBranch(String name) {
        Result result;
        String message;
        if (isBranchNotExist(name)) {
            message = "branch " + name + " does not exist";
            result = new Result(message, false);
            return result;
        } else {
            currentBranch = branches.get(name);
            filesInRepo = new HashSet<>(branches.get(name).getAllFiles());
            message = "switched to branch " + name;
            result = new Result(message, true);
        }
        return result;
    }

    private boolean isBranchNotExist(String name) {
        return !(branches.containsKey(name));
    }

    public Result checkoutCommit(String hash) {
        List<Commit> newCommits = new LinkedList<>();
        Result result;
        String message;
        for (Commit commit : currentBranch.getAllCommits()) {
            newCommits.add(commit);
            if (isHashEqual(commit, hash)) {
                currentBranch.setCommits(newCommits);
                filesInRepo = currentBranch.getLastCommit().getFiles();
                message = "HEAD is now at " + hash;
                result = new Result(message, true);
                return result;
            }
        }
        message = "commit " + hash + " does not exist";
        result = new Result(message, false);
        return result;
    }

    private boolean isHashEqual(Commit commit, String hash) {
        return commit.getHash().equals(hash);
    }
}
