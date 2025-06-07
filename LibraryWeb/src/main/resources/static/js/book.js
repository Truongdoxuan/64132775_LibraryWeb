function datTruocSach(id, soLuongTon) {
    console.log("datTruocSach called with:", id, soLuongTon);
    const soLuong = prompt("Nhập số lượng sách muốn đặt:");

    if (soLuong === null) {
        // Người dùng nhấn Hủy, không làm gì cả
        return;
    }

    const sl = parseInt(soLuong);
    if (isNaN(sl) || sl <= 0) {
        alert("Vui lòng nhập số lượng hợp lệ.");
        return;
    }

    if (sl > soLuongTon) {
        alert("Số lượng đặt vượt quá số lượng sách còn lại (" + soLuongTon + ").");
        return;
    }

    const confirmed = confirm("Bạn có chắc muốn đặt " + sl + " cuốn sách?");
    if (confirmed) {
        window.location.href = `/user/dat_truoc_sach?id=${id}&soLuong=${sl}`;
    }
}
