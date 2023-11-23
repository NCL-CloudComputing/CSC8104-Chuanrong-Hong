package uk.ac.newcastle.enterprisemiddleware.contact.hotel.entity;

public class ServiceReturn {
    private Boolean status;

    private Object content;

    public ServiceReturn( Object content , Boolean status) {
        this.status = status;
        this.content = content;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }
}
