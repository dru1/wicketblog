package at.dru.wicketblog.components;

public enum FormType {

    DEFAULT("form"),

    HORIZONTAL("form-horizontal"),

    INLINE("form-inline");

    private final String cssClass;

    FormType(String cssClass) {
        this.cssClass = cssClass;
    }

    public String getCssClass() {
        return cssClass;
    }
}
