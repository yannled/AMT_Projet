package ch.heigvd.amt.amtproject.model;

public class Pagination {
    private int currentPage;
    private int recordsPerPage;

    public Pagination(int currentPage, int recordsPerPage) {
        this.recordsPerPage = recordsPerPage;
        this.currentPage = currentPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getRecordsPerPage() {
        return recordsPerPage;
    }

    public void setRecordsPerPage(int recordsPerPage, int nbrListElements) {
        if(recordsPerPage > nbrListElements) {
            this.recordsPerPage = nbrListElements;
        }
        else {
            this.recordsPerPage = recordsPerPage;
        }
    }

    public int getFirstElement(){
        return (this.getCurrentPage()-1)*this.getRecordsPerPage();
    }

    public int getLastElement(int nbrListElements){
        int lastElement = this.getRecordsPerPage()*this.getCurrentPage();
        if(lastElement > nbrListElements)
            return nbrListElements;
        return lastElement;
    }
}