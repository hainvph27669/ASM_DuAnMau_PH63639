package fpoly.hainvph63639.nguyenvanhai_ph63639_duanmau.Model;

public class LoaiSach {
    private int id;
    private String tenLoai;

    public LoaiSach(int id, String tenLoai) {
        this.id = id;
        this.tenLoai = tenLoai;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }
}
