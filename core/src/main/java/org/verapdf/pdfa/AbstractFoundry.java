/**
 *
 */
package org.verapdf.pdfa;

import org.verapdf.pdfa.flavours.PDFAFlavour;
import org.verapdf.pdfa.validation.ValidationProfile;
import org.verapdf.pdfa.validators.ValidatorFactory;

/**
 * @author <a href="mailto:carl@openpreservation.org">Carl Wilson</a>
 *         <a href="https://github.com/carlwilson">carlwilson AT github</a>
 * @version 0.1 Created 26 Oct 2016:21:25:17
 */

abstract class AbstractFoundry implements VeraPDFFoundry {

	@Override
	public PDFAValidator newValidator(PDFAFlavour flavour, boolean logSuccess) {
		return ValidatorFactory.createValidator(flavour, logSuccess);
	}

	@Override
	public PDFAValidator newValidator(ValidationProfile profile, boolean logSuccess) {
		return ValidatorFactory.createValidator(profile, logSuccess);
	}

	@Override
	public PDFAValidator newFailFastValidator(PDFAFlavour flavour, int maxFailures) {
		return ValidatorFactory.createValidator(flavour, maxFailures);
	}

	@Override
	public PDFAValidator newFailFastValidator(ValidationProfile profile, int maxFailures) {
		return ValidatorFactory.createValidator(profile, maxFailures);
	}
}
