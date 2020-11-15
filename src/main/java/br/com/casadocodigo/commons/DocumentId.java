package br.com.casadocodigo.commons;

import org.springframework.util.Assert;

public abstract class DocumentId {

    protected final String docId;

    public DocumentId(String docId) {
        this.docId = clearMasks(docId);
    }

    private String clearMasks(String docId) {
        return docId.trim().replace(".", "").replace("-", "");
    }

    protected int computeDigit(String str) {
        final Integer[] weight = weight();
        Assert.notEmpty(weight, "weight can't be empty here");

        int sum = 0;
        int digit = 0;
        for (int index = str.length() - 1; index >= 0; index--) {
            digit = Integer.parseInt(str.substring(index, index + 1));
            sum += digit * weight[weight.length - str.length() + index];
        }
        sum = 11 - sum % 11;
        return sum > 9 ? 0 : sum;
    }

    protected boolean hasValidLength(int length) {
        return (docId != null && docId.length() == length);
    }

    protected abstract Integer[] weight();
}
