package bg.sofia.uni.fmi.mjt.git;

public class Result {

	private String message;
	private boolean status;

	public Result(String message, boolean status) {

		this.message = message;
		this.status = status;
	}

	public boolean isSuccessful() {
		return status;
	}

	public String getMessage() {
		return message;
	}
}
