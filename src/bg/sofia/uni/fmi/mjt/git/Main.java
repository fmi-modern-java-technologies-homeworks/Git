package bg.sofia.uni.fmi.mjt.git;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Main {

	public static void main(String[] args) {


	

		//Repository repo = new Repository();

		// Result actual = repo.add("foo.txt", "bar.txt", "baz.txt");
//		
		// System.out.println(actual.getMessage());

		//repo.remove("sss");
		//repo.add("todor.txt");
		//repo.remove("todor.txt");

		//Result result = repo.commit("sss");
		// actual = repo.remove("foo.txt");

//		System.out.println(result.getMessage());

//		repo.add("foo.txt");
//		repo.commit("First commit");
//
//		repo.add("foo2.txt");
//		repo.commit("Second commit");
//
//		repo.add("foo3.txt");
//		repo.commit("Third commit");

//		repo.checkoutCommit(repo.currentBranch.allCommits().get(0).getHash());

		// System.out.println();
		// Result r = repo.checkoutCommit("ssss");

		// System.out.println(repo.checkoutCommit(repo.currentBranch.allCommits().get(0).getHash()).getMessage());

		// System.out.println(repo.log().getMessage());
		/*
		 * repo.add("foo.txt"); repo.commit("First commit");
		 * 
		 * repo.add("bar.txt"); repo.commit("Second commit");
		 * 
		 * repo.createBranch("Desi"); repo.checkoutBranch("Desi");
		 * 
		 * repo.add("todor.txt"); repo.commit("First commit of the feature");
		 * 
		 * repo.add("ssss/tsada.tas");
		 * 
		 * repo.commit("Second commit of the feature");
		 * 
		 * repo.remove("ssss/tsada.tas");
		 * 
		 * repo.commit("Without the last bullshit");
		 * 
		 * System.out.println(repo.log().getMessage());
		 * 
		 * System.out.println(repo.checkoutCommit(repo.currentBranch.allCommits().get(3)
		 * .getHash()).getMessage());
		 * 
		 * System.out.println(repo.log().getMessage()); repo.checkoutBranch("master");
		 */
		// Result result = repo.log();
		// System.out.println(result.getMessage());
		/*
		 * Repository repo = new Repository();
		 * 
		 * System.out.println(repo.add("foo.txt").getMessage());
		 * 
		 * repo.commit("SSSS");
		 * 
		 * Result res = repo.add("bar.txt", "foo.txt", "dir2.txt");
		 * 
		 * System.out.println(res.getMessage());
		 */

		// System.out.println(repo.add("bar.txt").getMessage());

		// System.out.println(repo.add("foo.txt").getMessage());

		// System.out.println();

		// System.out.println(repo.add("foo.txt").getMessage());

		// System.out.println(repo.remove("foo.txt").getMessage());
		// ystem.out.println();

		/*
		 * 
		 * System.out.println(repo.add("todor.txt", "kiro.txt", "desi.txt",
		 * "rali.txt").getMessage());
		 * 
		 * System.out.println(repo.remove("todor.txt", "dani.tzt").getMessage());
		 * 
		 * repo.remove("todor.txt");
		 * 
		 * System.out.println(repo.commit("ss").getMessage());
		 * 
		 * repo.commit("Initial commit");
		 * 
		 * System.out.println(repo.log().getMessage()); System.out.println();
		 * System.out.println();
		 * 
		 * System.out.println(repo.add("Sashko.txt", "gesha.txt", "valkata.txt",
		 * "kiro.txt", "ganicha.txt").getMessage()); Result res =
		 * repo.commit("Second commit");
		 * 
		 * //System.out.println(res.getMessage());
		 * 
		 * System.out.println();
		 * 
		 * for (String file : repo.currentFiles) { System.out.println(file); }
		 * 
		 * // System.out.println(repo.log().getMessage());
		 * 
		 */

//		repo.add("src/Main.java");
//		repo.commit("Add Main.java");
//
//		repo.createBranch("dev");
//		Result actual = repo.checkoutBranch("dev");
//		// assertSuccess("switched to branch dev", actual);
//		System.out.println(actual.getMessage());
//
//		repo.remove("src/Main.java");
//		repo.commit("Remove Main.java");
//		// assertEquals("Remove Main.java", repo.getHead().getMessage());
//		System.out.println(repo.getHead().getMessage());
//
//		actual = repo.checkoutBranch("master");
//		// assertSuccess("switched to branch master", actual);
//		System.out.println(actual.getMessage());
//
//		// assertEquals("Add Main.java", repo.getHead().getMessage());
//		System.out.println(repo.getHead().getMessage());
//
//		System.out.println();

//		// make a repo
//		Repository repo = new Repository();
//		// two adds
//		Result res = repo.add("todor.txt", "tanya.txt");
//		System.out.println(res.getMessage());
//		Result result = repo.add("todor.txt");
//		System.out.println(result.getMessage());
//
//		// if commit getHead working right
//		Commit com = repo.getHead();
//		if (com == null) {
//			System.out.println("sss");
//		} else {
//			System.out.println(com.getMessage());
//		}
//
//		// make first commit
//		result = repo.commit("Initial commmit");
//		System.out.println(result.getMessage());
//
//		// try to remove when the return is false
//		result = repo.remove("acho.txt");
//		System.out.println(result.getMessage());
//
//		// remove when the return is true
//		result = repo.remove("todor.txt");
//		System.out.println(result.getMessage());
//
//		Commit com1 = repo.getHead();
//		// second commit
//		result = repo.commit("Second commmit");
//		System.out.println(result.getMessage());
//
//		Commit com2 = repo.getHead();
//		if (com2 == null) {
//			System.out.println("sss");
//		} else {
//			System.out.println(com2.getMessage());
//		}
//
//		Result logResult = repo.log();
//
//		System.out.println();
//		//System.out.println(logResult.getMessage());
//
//		System.out.println(repo.log().getMessage());
//		System.out.println();
//		
//		System.out.println(repo.checkoutCommit(com1.hashOfTheCommit()).getMessage());
//		
//		
//		System.out.println(repo.log().getMessage());
//		
//		LocalDateTime now = LocalDateTime.now();
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE MMM d H:m Y");

		// String text = now.format(formatter);
		// LocalDate parsedDate = LocalDate.parse(text, formatter);

		// ow.format(formatter);
		// System.out.println(now.format(formatter));

		// System.out.println(now.format(formatter));

//		System.out.println(repo.getBranch());
//
//		repo.createBranch("secondBranch");
//		System.out.println(repo.checkoutBranch("secondBranch").getMessage());
//
//		repo.add("Mire.txt");
//		repo.commit("Mire first commit");
//		
//		System.out.println("ssss");
	}

}
