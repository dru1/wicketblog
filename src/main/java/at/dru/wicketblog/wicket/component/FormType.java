package at.dru.wicketblog.wicket.component;

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
