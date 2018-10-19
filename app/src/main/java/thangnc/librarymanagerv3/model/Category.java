package thangnc.librarymanagerv3.model;

public class Category {
    public String id;
    public String name;
    public String desc;
    public String postion;

    public Category(String id, String name, String desc, String postion) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.postion = postion;
    }

    public Category(){

    }
}
