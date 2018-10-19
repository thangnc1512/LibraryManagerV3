package thangnc.librarymanagerv3.model;

import java.util.Date;

public class Invoice {
    public String invoiceID;
    public long invoiceDate;

    public Invoice(String invoiceID, long invoiceDate) {
        this.invoiceID = invoiceID;
        this.invoiceDate = invoiceDate;
    }

    @Override
    public String toString() {
        String dateConvert = new Date(invoiceDate).toString();
        return invoiceID + "\n" +dateConvert;
    }
}


