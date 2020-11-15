package br.com.casadocodigo.commons;

public class CNPJ extends DocumentId {

    private static final int DOC_LENGTH = 14;

    public CNPJ(String docId) {
        super(docId);
    }

    @Override
    protected Integer[] weight() {
        return new Integer[]{6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
    }

    public boolean isValid() {
        if (!hasValidLength(DOC_LENGTH)) {
            return false;
        }

        final int firstDigit = computeDigit(docId.substring(0, 12));
        final int secondDigit = computeDigit(docId.substring(0, 12) + firstDigit);

        return docId.equals(docId.substring(0, 12) + firstDigit + secondDigit);
    }

}
