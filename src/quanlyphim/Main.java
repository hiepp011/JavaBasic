package quanlyphim;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Phim[] movie = null;
		do {
			int chon = menu();
			if (chon == 0) {
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
				int viTriPhim = timKiem(movie);
				int i = 0;
				System.out.printf("| %3d | %-10s | %-10s | %10d | %-15s | %8.1f |\n", (i + 1), movie[viTriPhim].tenPhim,
						movie[viTriPhim].daoDien, movie[viTriPhim].namSanXuat, movie[viTriPhim].theLoai,
						movie[viTriPhim].danhGia);
				break;
			}
			case 4: {

				break;
			}
			case 5: {
				movie = themPhim(movie);
				break;
			}
			case 6: {
				movie = xoaPhim(movie);
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
		System.out.println("|        7. Lập Hóa Đơn            |");
		System.out.println("|        8. Thống Kê               |");
		System.out.println("|        0. Thoát                  |");
		System.out.println("=====================================");
		System.out.print("Nhập lựa chọn của bạn: ");
		Scanner sc = new Scanner(System.in);
		chon = sc.nextInt();
		return chon;
	}

	static Phim[] nhap() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Số lượng phim cần nhập ");
		int n = sc.nextInt();
		sc.nextLine();
		Phim[] movie = new Phim[n];
		for (int i = 0; i < n; i++) {
//			movie[i] = new Phim();
//			System.out.println("Nhập thông tin phim thứ "+ (i+1) +":");
//			System.out.print("Tên Phim: ");
//			movie[i].tenPhim = sc.nextLine();
//			System.out.print("Tên Đạo Diễn: ");
//			movie[i].daoDien = sc.nextLine();
//			System.out.print("Năm Sản Xuất: ");
//			movie[i].namSanXuat = sc.nextInt();
			System.out.print("Tên Phim: ");
			String tenPhim = sc.nextLine();
			System.out.print("Tên Đạo Diễn: ");
			String daoDien = sc.nextLine();
			System.out.print("Năm Sản Xuất: ");
			int namSanXuat = Integer.parseInt(sc.nextLine());
			System.out.print("Thể Loại: ");
			String theLoai = sc.nextLine();
			System.out.print("Đánh Giá : ");
			double danhGia = Double.parseDouble(sc.nextLine());
			movie[i] = new Phim(tenPhim, daoDien, namSanXuat, theLoai, danhGia);
		}
		return movie;
	}

	static void inPhim(Phim[] movie) {
		if (movie != null && movie.length > 0) {
			System.out.println("Danh Sách Phim:");
			System.out.println("-------------------------------------------------");
			System.out.printf("| %3s | %-10s | %-10s | %-10s | %-15s | %-10s |\n", "STT", "Tên Phim", "Đạo Diễn",
					"Năm Sản Xuất", "Thể Loại", "Đánh Giá");
			System.out.println("-------------------------------------------------");
			for (int i = 0; i < movie.length; i++) {
				System.out.printf("| %3d | %-10s | %-10s | %10d | %-15s | %8.1f |\n", (i + 1), movie[i].tenPhim,
						movie[i].daoDien, movie[i].namSanXuat, movie[i].theLoai, movie[i].danhGia);
			}
			System.out.println("-------------------------------------------------");
		} else {
			System.out.println("Không có phim nào để hiển thị.");
		}
	}

	static int timKiem(Phim[] movie) {
		System.out.println("Nhập tên Phim cần tìm");
		Scanner sc = new Scanner(System.in);

		String tenPhimCanTim = sc.nextLine();
		if (movie != null && movie.length > 0) {
			for (int i = 0; i < movie.length; i++) {
				if (movie[i].tenPhim.equalsIgnoreCase(tenPhimCanTim)) {
					// Nếu tìm thấy phim cần tìm, trả về chỉ số của phim trong mảng
					return i;
				}
			}
		}
		// Nếu không tìm thấy phim cần tìm, trả về -1
		return -1;
	}

	static Phim[] themPhim(Phim[] danhSachPhim) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Nhập số lượng phim muốn thêm:");
		int soLuongThem = sc.nextInt();
		sc.nextLine(); // Đọc kí tự '\n' sau khi nhập số lượng

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
			int namSanXuat = sc.nextInt();
			sc.nextLine(); // Đọc kí tự '\n' sau khi nhập số năm sản xuất
			System.out.print("Thể Loại: ");
			String theLoai = sc.nextLine();
			System.out.print("Đánh Giá: ");
			double danhGia = sc.nextDouble();
			sc.nextLine(); // Đọc kí tự '\n' sau khi nhập đánh giá

			// Tạo đối tượng Phim mới và thêm vào mảng
			newDanhSachPhim[i] = new Phim(tenPhim, daoDien, namSanXuat, theLoai, danhGia);
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

}
