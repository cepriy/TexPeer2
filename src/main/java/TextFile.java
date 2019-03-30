package main.java;

public class TextFile {
    private String name;
    private String path;
    private String content;
    private int foundInFile;

    public int getFoundInFile() {
        return foundInFile;
    }

    public void setFoundInFile(int foundInFile) {
        this.foundInFile = foundInFile;
    }

    public TextFile(String path, String name, String content) {
        this.path = path;
        this.name = name;
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
