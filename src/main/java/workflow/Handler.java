package workflow;

public interface Handler extends Task {

	boolean postprocess(Context context, Exception exception);

}
