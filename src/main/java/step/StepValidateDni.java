package step;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import context.ContextVar;
import workflow.Context;

public class StepValidateDni extends Step {

	private ContextVar contextVariableName = null;
	private UUID nextStepIsTrue = null;
	private UUID nextStepIsFalse = null;

	public StepValidateDni(UUID tmpid) {
		this.id = tmpid;
		this.StepType = StepType.ValidateDni;
	}

	private boolean validarDNI(String DNI) {

		boolean res = false;
		if (DNI == null || DNI.isEmpty()) {
			res = false;
		} else {
			Pattern r = Pattern.compile("^[0-9]{5,8}$");
			Matcher m = r.matcher(DNI.trim());
			if (m.find()) {
				res = true;
			} else {
				res = false;
			}
		}

		return res;
	}

	@Override
	public boolean execute(Context context) throws Exception {
		if (contextVariableName == null) {
			throw new Exception("INVALID CONTEXT VARIABLENAME");
		}
		if (context.containsKey(contextVariableName.getId())) {
			ContextVar ctv = (ContextVar) context.get(contextVariableName
					.getId());
			boolean esValido = validarDNI(ctv.getVarValue());
			if (esValido)
				this.setNextstep(nextStepIsTrue);
			else
				this.setNextstep(nextStepIsFalse);
		} else {
			throw new Exception("VARIABLE CONTEXTY NOT EXIST");
		}
		return false;
	}

	public void setContextVariableName(ContextVar contextVariableName) {
		this.contextVariableName = contextVariableName;
	}

	public UUID getNextStepIsTrue() {
		return nextStepIsTrue;
	}

	public void setNextStepIsTrue(UUID nextStepIsTrue) {
		this.nextStepIsTrue = nextStepIsTrue;
	}

	public UUID getNextStepIsFalse() {
		return nextStepIsFalse;
	}

	public void setNextStepIsFalse(UUID nextStepIsFalse) {
		this.nextStepIsFalse = nextStepIsFalse;
	}

}
