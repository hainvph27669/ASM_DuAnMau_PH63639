package fpoly.hainvph63639.nguyenvanhai_ph63639_duanmau.Model;

public class Sach {
    private int masach;
    private String tensach;
    private int giathue;
    private int maloai;
    private int sldamuon;
    private String tenloai;

    public Sach(int masach, String tensach, int giathue, int maloai, String tenloai) {
        this.masach = masach;
        this.tensach = tensach;
        this.giathue = giathue;
        this.maloai = maloai;
        this.tenloai = tenloai;
    }

    public String getTenloai() {
        return tenloai;
    }

    public void setTenloai(String tenloai) {
        this.tenloai = tenloai;
    }

    public Sach(int masach, String tensach, int giathue, int maloai) {
        this.masach = masach;
        this.tensach = tensach;
        this.giathue = giathue;
        this.maloai = maloai;
    }

    public int getSldamuon() {
        return sldamuon;
    }

    public void setSldamuon(int sldamuon) {
        this.sldamuon = sldamuon;
    }

    public Sach(int masach, String tensach, int sldamuon) {
        this.masach = masach;
        this.tensach = tensach;
        this.sldamuon = sldamuon;
    }

    public int getMasach() {
        return masach;
    }

    public void setMasach(int masach) {
        this.masach = masach;
    }

    public String getTensach() {
        return tensach;
    }

    public void setTensach(String tensach) {
        this.tensach = tensach;
    }

    public int getGiathue() {
        return giathue;
    }

    public void setGiathue(int giathue) {
        this.giathue = giathue;
    }

    public int getMaloai() {
        return maloai;
    }

    public void setMaloai(int maloai) {
        this.maloai = maloai;
    }
}
