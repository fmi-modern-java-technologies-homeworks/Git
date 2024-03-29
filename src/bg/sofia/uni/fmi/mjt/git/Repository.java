package bg.sofia.uni.fmi.mjt.git;

import java.util.*;

public class Repository {

    private Set<String> filesInRepo;
    private Branch currentBranch;
    private Map<String, Branch> branches;

    private StageAreaAdd stageAdd;
    private StageAreaRemove stageRemove;

    public Repository() {
        initializeRepository();
    }

    private void initializeRepository() {
        filesInRepo = new HashSet<>();
        branches = new HashMap<>();
        Branch branch = new Branch("master", new ArrayList<>(), new HashSet<>());
        branches.put("master", branch);
        currentBranch = branches.get("master");
        stageAdd = new StageAreaAdd();
        stageRemove = new StageAreaRemove();
    }

    public Result add(String... files) {
        Result result;
        String message;
        for (String file : files) {
            if (isFileContained(file)) {
                message = "'" + file + "'" + " already exists";
                result = new Result(message, false);
                return result;
            }
        }
        stageAdd.add(files);
        StringBuilder filesMessage = createFileMessage(files);
        message = "added " + getCleanMessage(filesMessage) + " to stage";
        result = new Result(message, true);
        return result;
    }

    private boolean isFileContained(String file) {
        return filesInRepo.contains(file) || stageAdd.isFileContained(file);
    }

    private StringBuilder createFileMessage(String... files) {
        StringBuilder message = new StringBuilder();
        for (String file : files) {
            message.append(", " + file);
        }
        return message;
    }

    private String getCleanMessage(StringBuilder filesMessage) {
        return filesMessage.toString().substring(2, filesMessage.length());
    }

    public Result commit(String commitMessage) {
        int changedFiles = stageAdd.getNumberOfFiles() + stageRemove.getNumberOfFiles();
        Result result;
        String message;

        if (changedFiles == 0) {
            message = "nothing to commit, working tree clean";
            result = new Result(message, false);
            return result;
        }

        commitFilesFromStageArea();
        Commit commit = new Commit(commitMessage, new HashSet<>(filesInRepo));
        currentBranch.commit(commit);
        clearStage();

        message = changedFiles + " files changed";
        result = new Result(message, true);
        return result;
    }

    private void commitFilesFromStageArea(){
        filesInRepo.addAll(stageAdd.getAll());
        filesInRepo.removeAll(stageRemove.getAll());
    }

    private void clearStage() {
        stageAdd.clearStage();
        stageRemove.clearStage();
    }

    public Result remove(String... files) {
        String message;
        Result result;
        for (String file : files) {
            if (isFileNotContained(file)) {
                message = "'" + file + "'" + " did not match any files";
                result = new Result(message, false);
                return result;
            }
        }
        removeFiles(files);
        StringBuilder fileMessage = createFileMessage(files);
        message = "added " + getCleanMessage(fileMessage) + " for removal";
        result = new Result(message, true);
        return result;
    }

    private void removeFiles(String... files) {
        List<String> filesForRemove = new ArrayList<>();
        for (String file : files) {
            if (stageAdd.isFileContained(file)) {
                stageAdd.remove(file);
            } else {
                filesForRemove.add(file);
            }
        }
        stageRemove.add(filesForRemove);
    }

    private boolean isFileNotContained(String file) {
        return !filesInRepo.contains(file) && !stageAdd.isFileContained(file);
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
