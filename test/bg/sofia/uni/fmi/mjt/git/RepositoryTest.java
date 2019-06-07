package bg.sofia.uni.fmi.mjt.git;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RepositoryTest {
    private Repository repo;

    @Before
    public void setUp() {
        repo = new Repository();
    }

    @Test
    public void testAddMultipleFiles() {
        Result actual = repo.add("foo.txt", "bar.txt", "baz.txt");
        assertSuccess("added foo.txt, bar.txt, baz.txt to stage", actual);
    }

    @Test
    public void testAddDoesNothingWhenAnyFileIAlreadyExists() {
        repo.add("foo.txt", "bar.txt", "baz.txt");

        Result actual = repo.add("ter.txt", "baz.txt");
        assertFail("'baz.txt' already exists", actual);

        actual = repo.commit("After add");
        assertSuccess("3 files changed", actual);
    }


    @Test
    public void testRemoveMultipleFiles() {
        repo.add("foo.txt", "bar.txt", "baz.txt");
        Result actual = repo.remove("foo.txt", "bar.txt", "baz.txt");
        assertSuccess("added foo.txt, bar.txt, baz.txt for removal", actual);
    }

    @Test
    public void testRemoveDoesNothingWhenAnyFileIsMissing() {
        repo.add("foo.txt", "bar.txt");

        Result actual = repo.remove("foo.txt", "baz.txt");
        assertFail("'baz.txt' did not match any files", actual);

        actual = repo.commit("After removal");
        assertSuccess("2 files changed", actual);
    }

    @Test
    public void testCommitDoesNothingWhenNothingChanges() {
        Result actual = repo.commit("Empty commit");
        assertEquals("nothing to commit, working tree clean", actual.getMessage());
    }

    @Test
    public void testCheckoutBranchCanSwitchBranches() {
        repo.add("src/Main.java");
        repo.commit("Add Main.java");

        repo.createBranch("dev");
        Result actual = repo.checkoutBranch("dev");
        assertSuccess("switched to branch dev", actual);

        repo.remove("src/Main.java");
        repo.commit("Remove Main.java");
        assertEquals("Remove Main.java", repo.getHead().getMessage());

        actual = repo.checkoutBranch("master");
        assertSuccess("switched to branch master", actual);
        assertEquals("Add Main.java", repo.getHead().getMessage());
    }

    private static void assertFail(String expected, Result actual) {
        assertFalse(actual.isSuccessful());
        assertEquals(expected, actual.getMessage());
    }

    private static void assertSuccess(String expected, Result actual) {
        assertTrue(actual.isSuccessful());
        assertEquals(expected, actual.getMessage());
    }

    @Test
    public void testCheckoutBranchWithInvalidBranchName() {
        Result actual = repo.checkoutBranch("Invalid name");
        assertEquals("branch Invalid name does not exist", actual.getMessage());
    }

    @Test
    public void testCheckoutCommitWithInvalidHash() {
        Result actual = repo.checkoutCommit("Invalid hash");
        assertEquals("commit Invalid hash does not exist", actual.getMessage());
    }
}
