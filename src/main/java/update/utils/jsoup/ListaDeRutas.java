package main.java.update.utils.jsoup;

public class ListaDeRutas {
    private String href;
    private String md5;
    private String folder;
    private String file;

    public ListaDeRutas(String href, String md5, String folder, String file) {
        this.href=href;
        this.md5=md5;
        this.folder=folder;
        this.file=file;
    }

    public String getHref() {
        return href;
    }

    public String getMd5() {
        return md5;
    }

    public String getFolder() {
        return folder;
    }

    public String getFile() {
        return file;
    }
}
