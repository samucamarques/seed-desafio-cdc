package br.com.casadocodigo.commons;

public class CPF extends DocumentId {

    private static final int DOC_LENGTH = 11;

    public CPF(String docId) {
        super(docId);
    }

    @Override
    protected Integer[] weight() {
        return new Integer[]{11, 10, 9, 8, 7, 6, 5, 4, 3, 2};
    }

    public boolean isValid() {
        if (!hasValidLength(DOC_LENGTH)) {
            return false;
        }

        for (int j = 0; j < 10; j++) {
            if (padLeft(Integer.toString(j), Character.forDigit(j, 10)).equals(docId)) {
                return false;
            }
        }

        final int firstDigit = computeDigit(docId.substring(0, 9));
        final int secondDigit = computeDigit(docId.substring(0, 9) + firstDigit);

        return docId.equals(docId.substring(0, 9) + firstDigit + secondDigit);
    }

    private String padLeft(String text, char character) {
        return String.format("%11s", text).replace(' ', character);
    }
}
