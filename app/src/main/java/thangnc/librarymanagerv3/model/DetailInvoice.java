package thangnc.librarymanagerv3.model;

public class DetailInvoice {
    public String DetailID;
    public String DetailBookID;
    public String InvoiceID;
    public int Detailquality;

    public DetailInvoice(String detailID, String detailBookID, String invoiceID, int detailquality) {
        DetailID = detailID;
        DetailBookID = detailBookID;
        InvoiceID = invoiceID;
        Detailquality = detailquality;
    }

    public DetailInvoice(){

    }

    public String getDetailID() {
        return DetailID;
    }

    public void setDetailID(String detailID) {
        DetailID = detailID;
    }

    public String getDetailBookID() {
        return DetailBookID;
    }

    public void setDetailBookID(String detailBookID) {
        DetailBookID = detailBookID;
    }

    public String getInvoiceID() {
        return InvoiceID;
    }

    public void setInvoiceID(String invoiceID) {
        InvoiceID = invoiceID;
    }

    public int getDetailquality() {
        return Detailquality;
    }

    public void setDetailquality(int detailquality) {
        Detailquality = detailquality;
    }
}
