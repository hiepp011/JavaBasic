package quanlyphim;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.text.NumberFormat;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Phim[] movie = null;
		do {
			int chon = menu();
			if (chon == 0) {
				System.out.println("Bạn đã thoát chương trình");
				break;
			}
			switch (chon) {
			case 1: {
				movie = nhap();
				break;
			}
			case 2: {
				inPhim(movie);
				break;
			}
			case 3: {
				break;
			}
			case 4: {
				capNhatPhim(movie);
				break;
			}
			case 5: {
				if (movie != null) {
					movie = themPhim(movie);
				} else {
					System.out.println("Chưa có phim nào trong danh sách");
				}
				break;
			}
			case 6: {
				if (movie != null) {
					movie = xoaPhim(movie);
				} else {
					System.out.println("Chưa có phim nào trong danh sách");
				}
				break;
			}
			case 7: {
				 danhGiaPhim(movie);
				 break;
			}
			case 8: {
				String tenFileDoc = "danhsachphim.txt";
                movie = DocFile.docFile(tenFileDoc);
                System.out.println("Đã đọc dữ liệu phim từ file thành công");
				break;
			}
			case 9: {
				 if (movie != null) {
                     String tenFileXuat = "danhsachphim.txt";
                     XuatFile.xuatFile(tenFileXuat, movie);
                     System.out.println("Đã xuất dữ liệu phim ra file thành công");
                 } else {
                     System.out.println("Chưa có phim nào để xuất");
                 }
				break;
			}
			case 10: {
				if (movie != null) {
			        sapXepTheoDiemTrungBinh(movie);
			        System.out.println("Đã sắp xếp danh sách phim theo điểm đánh giá trung bình.");
			        inPhim(movie); 
			    } else {
			        System.out.println("Chưa có phim nào trong danh sách.");
			    }
			    break;

			}
			case 11: {
				break;
			}

			default:
				System.out.println("Nhập không đúng Vui lòng nhập lại");
			}
		} while (true);
	}

	static int menu() {
		int chon = 0;
		System.out.println("=====================================");
		System.out.println("|         QUẢN LÝ PHIM          |");
		System.out.println("=====================================");
		System.out.println("| Options:                         |");
		System.out.println("|        1. Nhập Danh Sách Phim    |");
		System.out.println("|        2. Xem Danh Sách Phim     |");
		System.out.println("|        3. Tìm Kiếm Phim          |");
		System.out.println("|        4. Cập Nhật Thông Tin Phim|");
		System.out.println("|        5. Thêm Phim Mới          |");
		System.out.println("|        6. Xóa Phim               |");
		System.out.println("|        7. Đánh giá Phim            |");
		System.out.println("|        8. Nhập file              |");
		System.out.println("|        9. Xuất File              |");
		System.out.println("|        10. Sắp Xếp                |");
		System.out.println("|        11. Thống Kê               |");
		System.out.println("|        0. Thoát                  |");
		System.out.println("=====================================");
		System.out.print("Nhập lựa chọn của bạn: ");
		Scanner sc = new Scanner(System.in);
		chon = kiemTraDauVao(0, 11);
		return chon;
	}

	static int kiemTraDauVao(int a, int b) {
		Scanner sc = new Scanner(System.in);
		while (true) {
			try {
				String input = sc.next();
				int chon = Integer.parseInt(input);
				if (chon >= a && chon <= b) {
					return chon;
				} else {
					System.out.println("Bạn đã nhập sai vui lòng nhập lại");
				}
			} catch (NumberFormatException e) {
				System.out.println("Nhập không hợp lệ vui lòng nhập lại");
			}
		}
	}

	static Phim[] nhap() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Số lượng phim cần nhập: ");
		int n = kiemTraDauVao(0, 1000);
		Phim[] movie = new Phim[n];
		for (int i = 0; i < n; i++) {
			System.out.println("Nhập thông tin cho phim thứ " + (i + 1) + ":");
			System.out.print("Tên Phim: ");
			String tenPhim = sc.nextLine();
			System.out.print("Tên Đạo Diễn: ");
			String daoDien = sc.nextLine();
			System.out.print("Năm Sản Xuất: ");
			int namSanXuat = kiemTraDauVao(0, 2024);
			System.out.print("Thể Loại: ");
			String theLoai = sc.nextLine();
			System.out.print("Số lượng đánh giá: ");
			int soLuongDanhGia = kiemTraDauVao(0, 1000);
			double tongDiem = 0;
			for (int j = 0; j < soLuongDanhGia; j++) {
				System.out.print("Nhập điểm đánh giá  của người thứ " + (j + 1) + ": ");
				double diemDanhGia = sc.nextDouble();
				tongDiem += diemDanhGia;
			}
			double diemTrungBinh = (double) tongDiem / soLuongDanhGia;
			movie[i] = new Phim(tenPhim, daoDien, namSanXuat, theLoai, diemTrungBinh, soLuongDanhGia);
		}
		return movie;
	}

	static void inPhim(Phim[] movie) {
		if (movie != null && movie.length > 0) {
			System.out.println("Danh Sách Phim:");
			System.out.println("-------------------------------------------------");
			System.out.printf("| %3s | %-25s | %-20s | %-15s | %-15s | %-15s | %-15s |\n", "STT", "Tên Phim",
					"Đạo Diễn", "Năm Sản Xuất", "Thể Loại", "Số Lượng Đánh Giá", "Điểm Trung Bình");
			System.out.println("-------------------------------------------------");
			for (int i = 0; i < movie.length; i++) {
				System.out.printf("| %3d | %-25s | %-20s | %-15d | %-15s | %-15d | %-15.2f |\n", (i + 1),
						movie[i].tenPhim, movie[i].daoDien, movie[i].namSanXuat, movie[i].theLoai,
						movie[i].soLuongDanhGia, movie[i].diemTrungBinh);
			}
			System.out.println("-------------------------------------------------");
		} else {
			System.out.println("Không có phim nào để hiển thị.");
		}
	}

	static Phim[] themPhim(Phim[] danhSachPhim) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Nhập số lượng phim muốn thêm: ");
		int soLuongThem = kiemTraDauVao(0, 1000);

		// Tạo mảng mới có kích thước lớn hơn để chứa cả danh sách phim cũ và phim mới
		Phim[] newDanhSachPhim = new Phim[danhSachPhim.length + soLuongThem];

		// Copy danh sách phim cũ vào mảng mới
		for (int i = 0; i < danhSachPhim.length; i++) {
			newDanhSachPhim[i] = danhSachPhim[i];
		}

		// Thêm các phim mới
		for (int i = danhSachPhim.length; i < danhSachPhim.length + soLuongThem; i++) {
			System.out.println("Nhập thông tin phim thứ " + (i + 1) + ":");
			System.out.print("Tên Phim: ");
			String tenPhim = sc.nextLine();
			System.out.print("Tên Đạo Diễn: ");
			String daoDien = sc.nextLine();
			System.out.print("Năm Sản Xuất: ");
			int namSanXuat = kiemTraDauVao(0, 2024);
			System.out.print("Thể Loại: ");
			String theLoai = sc.nextLine();
			System.out.print("Số lượng đánh giá: ");
			int soLuongDanhGia = kiemTraDauVao(0, 1000);

			// Nhập điểm đánh giá
			int tongDiem = 0;
			for (int j = 0; j < soLuongDanhGia; j++) {
				System.out.printf("Nhập điểm đánh giá %d/%d: ", (j + 1), soLuongDanhGia);
				double diemDanhGia = sc.nextDouble();
				tongDiem += diemDanhGia;
			}
			double diemTrungBinh = (double) tongDiem / soLuongDanhGia;

			// Tạo đối tượng Phim mới và thêm vào mảng
			newDanhSachPhim[i] = new Phim(tenPhim, daoDien, namSanXuat, theLoai, diemTrungBinh, soLuongDanhGia);
		}

		System.out.println("Đã thêm " + soLuongThem + " phim mới.");
		return newDanhSachPhim;
	}

	static Phim[] xoaPhim(Phim[] danhSachPhim) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Nhập tên phim cần xóa: ");
		String tenPhimCanXoa = sc.nextLine();

		// Tìm chỉ số của phim cần xóa trong danh sách
		int index = -1;
		for (int i = 0; i < danhSachPhim.length; i++) {
			if (danhSachPhim[i].tenPhim.equalsIgnoreCase(tenPhimCanXoa)) {
				index = i;
				break;
			}
		}

		// Nếu phim không tồn tại trong danh sách
		if (index == -1) {
			System.out.println("Không tìm thấy phim với tên: " + tenPhimCanXoa);
			return danhSachPhim; // Trả lại danh sách cũ nếu không tìm thấy phim
		}

		// Tạo một mảng mới với kích thước nhỏ hơn để chứa các phim sau khi xóa
		Phim[] newDanhSachPhim = new Phim[danhSachPhim.length - 1];

		// Copy các phim trước vị trí phim cần xóa
		for (int i = 0; i < index; i++) {
			newDanhSachPhim[i] = danhSachPhim[i];
		}

		// Copy các phim sau vị trí phim cần xóa
		for (int i = index + 1; i < danhSachPhim.length; i++) {
			newDanhSachPhim[i - 1] = danhSachPhim[i];
		}

		System.out.println("Phim " + tenPhimCanXoa + " đã được xóa.");
		return newDanhSachPhim;
	}

	static void capNhatPhim(Phim[] danhSachPhim) {
		if (danhSachPhim == null || danhSachPhim.length == 0) {
			System.out.println("Không có phim nào trong danh sách.");
			return;
		}

		Scanner sc = new Scanner(System.in);
		System.out.print("Nhập tên phim cần cập nhật: ");
		String tenPhimCanCapNhat = sc.nextLine();

		int viTriPhim = -1;
		for (int i = 0; i < danhSachPhim.length; i++) {
			if (danhSachPhim[i].tenPhim.equalsIgnoreCase(tenPhimCanCapNhat)) {
				viTriPhim = i;
				break;
			}
		}

		if (viTriPhim == -1) {
			System.out.println("Không tìm thấy phim: " + tenPhimCanCapNhat);
			return;
		}

		System.out.println("Nhập thông tin mới cho phim: " + danhSachPhim[viTriPhim].tenPhim);
		System.out.print("Tên Đạo Diễn mới: ");
		danhSachPhim[viTriPhim].daoDien = sc.nextLine();
		System.out.print("Năm Sản Xuất mới: ");
		danhSachPhim[viTriPhim].namSanXuat = kiemTraDauVao(0, 2024);
		System.out.print("Thể Loại mới: ");
		danhSachPhim[viTriPhim].theLoai = sc.nextLine();
		System.out.print("Số lượng đánh giá mới: ");
		int soLuongDanhGiaMoi = kiemTraDauVao(0, 1000);

		// Nhập điểm đánh giá mới
		double tongDiem = 0;
		for (int j = 0; j < soLuongDanhGiaMoi; j++) {
			System.out.printf("Nhập điểm đánh giá %d/%d: ", (j + 1), soLuongDanhGiaMoi);
			double diemDanhGiaMoi = sc.nextDouble();
			tongDiem += diemDanhGiaMoi;
		}
		double diemTrungBinhMoi = (double) tongDiem / soLuongDanhGiaMoi;

		// Cập nhật thông tin số lượng đánh giá và điểm trung bình
		danhSachPhim[viTriPhim].soLuongDanhGia = soLuongDanhGiaMoi;
		danhSachPhim[viTriPhim].diemTrungBinh = diemTrungBinhMoi;

		System.out.println("Cập nhật thông tin phim thành công.");
	}

	
	public static Phim[] docFile(String tenFile) throws IOException {
		
	    List<Phim> danhSachPhim = new ArrayList<>();
	    BufferedReader br = new BufferedReader(new FileReader(tenFile));
	    String line;
	    while ((line = br.readLine()) != null) {
	        String[] data = line.split(","); // Dữ liệu được phân cách bằng dấu phẩy
	        String tenPhim = data[0];
	        String daoDien = data[1];
	        int namSanXuat = Integer.parseInt(data[2]);
	        String theLoai = data[3];
	        double diemTrungBinh = Double.parseDouble(data[4]);
	        int soLuongDanhGia = Integer.parseInt(data[5]);
	        Phim phim = new Phim(tenPhim, daoDien, namSanXuat, theLoai, diemTrungBinh, soLuongDanhGia);
	        danhSachPhim.add(phim);
	    }
	    br.close();
	    return danhSachPhim.toArray(new Phim[danhSachPhim.size()]);
	}

	


	public class DocFile {

	    public static Phim[] docFile(String tenFile) throws IOException {
	        List<Phim> danhSachPhim = new ArrayList<>();
	        BufferedReader br = new BufferedReader(new FileReader(tenFile));
	        String line;
	        while ((line = br.readLine()) != null) {
	            String[] data = line.split(","); // Dữ liệu được phân cách bằng dấu phẩy
	            String tenPhim = data[0];
	            String daoDien = data[1];
	            int namSanXuat = Integer.parseInt(data[2]);
	            String theLoai = data[3];
	            double diemTrungBinh = Double.parseDouble(data[4]);
	            int soLuongDanhGia = Integer.parseInt(data[5]);
	            Phim phim = new Phim(tenPhim, daoDien, namSanXuat, theLoai, diemTrungBinh, soLuongDanhGia);
	            danhSachPhim.add(phim);
	        }
	        br.close();
	        return danhSachPhim.toArray(new Phim[danhSachPhim.size()]);
	    }
	}


	public class XuatFile {

	    public static void xuatFile(String tenFile, Phim[] danhSachPhim) throws IOException {
	        BufferedWriter bw = new BufferedWriter(new FileWriter(tenFile));
	        for (Phim phim : danhSachPhim) {
	            bw.write(phim.tenPhim + "," + phim.daoDien + "," + phim.namSanXuat + "," + phim.theLoai + "," + phim.diemTrungBinh + "," + phim.soLuongDanhGia);
	            bw.newLine();
	        }
	        bw.close();
	    }
	}

	static void danhGiaPhim(Phim[] danhSachPhim) {
	    Scanner scanner = new Scanner(System.in);
	    System.out.println("Danh sách phim:");
	    inPhim(danhSachPhim); // Hiển thị danh sách phim để người dùng chọn phim cần đánh giá

	    System.out.print("Nhập số thứ tự của phim bạn muốn đánh giá: ");
	    int index = kiemTraDauVao(1, danhSachPhim.length) - 1; // -1 để chuyển từ số thứ tự thành chỉ số mảng

	    Phim phim = danhSachPhim[index];
	    System.out.println("Bạn đang đánh giá phim: " + phim.tenPhim);

	    System.out.print("Nhập số lượng đánh giá mới: ");
	    int soLuongDanhGiaMoi = kiemTraDauVao(0, 1000);

	    double tongDiem = phim.diemTrungBinh * phim.soLuongDanhGia; // Tính tổng điểm từ đánh giá trước
	    for (int i = 0; i < soLuongDanhGiaMoi; i++) {
	        System.out.printf("Nhập điểm đánh giá %d/%d: ", (i + 1), soLuongDanhGiaMoi);
	        double diemDanhGiaMoi = scanner.nextDouble();
	        tongDiem += diemDanhGiaMoi;
	    }

	    int tongSoLuongDanhGiaMoi = phim.soLuongDanhGia + soLuongDanhGiaMoi;
	    double diemTrungBinhMoi = tongDiem / tongSoLuongDanhGiaMoi;

	    // Cập nhật số lượng đánh giá và điểm trung bình mới cho phim
	    phim.soLuongDanhGia = tongSoLuongDanhGiaMoi;
	    phim.diemTrungBinh = diemTrungBinhMoi;

	    System.out.println("Đã cập nhật đánh giá cho phim " + phim.tenPhim + ".");
	}
	// Phương thức sắp xếp theo điểm đánh giá trung bình
	static void sapXepTheoDiemTrungBinh(Phim[] danhSachPhim) {
	    int n = danhSachPhim.length;
	    boolean swapped;
	    do {
	        swapped = false;
	        for (int i = 1; i < n; i++) {
	            if (danhSachPhim[i - 1].diemTrungBinh < danhSachPhim[i].diemTrungBinh) {
	                // Hoán đổi vị trí của hai phần tử
	                Phim temp = danhSachPhim[i - 1];
	                danhSachPhim[i - 1] = danhSachPhim[i];
	                danhSachPhim[i] = temp;
	                swapped = true;
	            }
	        }
	        n--;
	    } while (swapped);
	}
	
}
