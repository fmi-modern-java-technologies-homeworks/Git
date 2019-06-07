package bg.sofia.uni.fmi.mjt.git;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Set;

public class Commit {

	private String message;
	private Set<String> files;
	private LocalDateTime timeOfCreation;

	static private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE MMM d HH:mm Y", Locale.ENGLISH);

	public Commit(String message, Set<String> files) {
		this.message = message;
		this.files = files;
		timeOfCreation = LocalDateTime.now();
	}

	public String getLogMessage() {
		return new String("commit " + getHash() + "\n" + "Date: " + formattedDate() + "\n\n\t" + getMessage());
	}

	public String getMessage() {
		return message;
	}

	public String formattedDate() {
		return timeOfCreation.format(formatter);
	}

	public String hexDigest(String input) {
		MessageDigest digest = null;
		try {
			digest = MessageDigest.getInstance("SHA-1");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		byte[] bytes = digest.digest(input.getBytes(StandardCharsets.UTF_8));
		return convertBytesToHex(bytes);
	}

	private String convertBytesToHex(byte[] bytes) {
		StringBuilder hex = new StringBuilder();
		for (byte current : bytes) {
			hex.append(String.format("%02x", current));
		}

		return hex.toString();
	}

	public String getHash() {

		return hexDigest(formattedDate() + message);
	}

	public Set<String> getFiles() {
		return files;
	}
}
