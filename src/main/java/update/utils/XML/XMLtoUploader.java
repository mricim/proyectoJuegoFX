package main.java.update.utils.XML;

public class XMLtoUploader implements Comparable<XMLtoUploader>{
    private String url;
    private String versionOld;
    private String versionNew;
    private boolean installer;
    private String fileName;

    public XMLtoUploader(String url, String versionOld, String versionNew, boolean installer, String fileName) {
        this.url = url;
        this.versionOld = versionOld;
        this.versionNew = versionNew;
        this.installer = installer;
        this.fileName = fileName;
    }

    public String getUrl() {
        return url;
    }

    public String getVersionOld() {
        return versionOld;
    }

    public String getVersionNew() {
        return versionNew;
    }

    public boolean isInstaller() {
        return installer;
    }

    public String getFileName() {
        return fileName;
    }

    @Override
    public String toString() {
        return "aXML{" +
                "url='" + url + '\'' +
                ", versionOld='" + versionOld + '\'' +
                ", versionNew='" + versionNew + '\'' +
                ", installer=" + installer +
                ", fileName='" + fileName + '\'' +
                '}';
    }

    @Override
    public int compareTo(XMLtoUploader o) {
        return o.getVersionNew().compareTo(this.getVersionNew());
    }
}
