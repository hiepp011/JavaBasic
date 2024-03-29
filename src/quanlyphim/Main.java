package quanlyphim;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.text.NumberFormat;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
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
				if(movie != null) {
					 timKiem(movie);
				}else {
					System.out.println("Chưa có phim nào trong danh sách");
				}
				break;
			}
			case 4: {
				capNhatPhim(movie);
				break;
			}
			case 5: {
				if(movie != null) {
				movie = themPhim(movie);
				}else {
					System.out.println("Chưa có phim nào trong danh sách");
				}
				break;
			}
			case 6: {
				if(movie != null) {
					movie = xoaPhim(movie);
				}else {
					System.out.println("Chưa có phim nào trong danh sách");
				}
				break;
			}
			case 7: {
				movie = docFile();
				break;
			}
			case 8: {
				xuatFile(movie);
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
		System.out.println("|        7. Đọc File               |");
		System.out.println("|        8. Xuất File              |");
		System.out.println("|        0. Thoát                  |");
		System.out.println("=====================================");
		System.out.print("Nhập lựa chọn của bạn: ");
		Scanner sc = new Scanner(System.in);
		chon = kiemTraDauVao(0, 8);
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
		System.out.print("Số lượng phim cần nhập ");
		int n = kiemTraDauVao(0, 1000);
		Phim[] movie = new Phim[n];
		for (int i = 0; i < n; i++) {
			System.out.print("Tên Phim: ");
			String tenPhim = sc.nextLine();
			System.out.print("Tên Đạo Diễn: ");
			String daoDien = sc.nextLine();
			System.out.print("Năm Sản Xuất: ");
			int namSanXuat = kiemTraDauVao(0, 2024);
			System.out.print("Thể Loại: ");
			String theLoai = sc.nextLine();
			System.out.print("Đánh Giá : ");
			int danhGia = kiemTraDauVao(0, 100);
			movie[i] = new Phim(tenPhim, daoDien, namSanXuat, theLoai, danhGia);
		}
		return movie;
	}

	static void inPhim(Phim[] movie) {
		if (movie != null && movie.length > 0) {
			System.out.println("Danh Sách Phim:");
			System.out.println("-------------------------------------------------");
			System.out.printf("| %3s | %-25s | %-20s | %-15s | %-15s | %-10s |\n", "STT", "Tên Phim", "Đạo Diễn",
					"Năm Sản Xuất", "Thể Loại", "Đánh Giá");
			System.out.println("-------------------------------------------------");
			for (int i = 0; i < movie.length; i++) {
				System.out.printf("| %3d | %-25s | %-20s | %-15d | %-15s | %-10d |\n", (i + 1), movie[i].tenPhim,
						movie[i].daoDien, movie[i].namSanXuat, movie[i].theLoai, movie[i].danhGia);
			}
			System.out.println("-------------------------------------------------");
		} else {
			System.out.println("Không có phim nào để hiển thị.");
		}
	}

	static int timKiem(Phim[] movie) {
	    Scanner sc = new Scanner(System.in);
	    System.out.println("Nhập thông tin cần tìm kiếm:");
	    System.out.println("1. Tên Phim");
	    System.out.println("2. Thể Loại");
	    System.out.println("3. Năm Sản Xuất");
	    System.out.println("4. Đạo Diễn");
	    System.out.println("5. Đánh Giá theo khoảng");
	    int luaChon = kiemTraDauVao(1, 5);

	    switch (luaChon) {
	        case 1: {
	            System.out.print("Nhập tên phim cần tìm: ");
	            String tenPhimCanTim = sc.nextLine();
	            timTheoTen(movie, tenPhimCanTim);
	            break;
	        }
	        case 2: {
	            System.out.print("Nhập thể loại cần tìm: ");
	            String theLoaiCanTim = sc.nextLine();
	            timTheoTheLoai(movie, theLoaiCanTim);
	            break;
	        }
	        case 3: {
	            System.out.print("Nhập năm sản xuất cần tìm: ");
	            int namSanXuatCanTim = kiemTraDauVao(0, 2024);
	            timTheoNamSanXuat(movie, namSanXuatCanTim);
	            break;
	        }
	        case 4: {
	            System.out.print("Nhập tên đạo diễn cần tìm: ");
	            String daoDienCanTim = sc.nextLine();
	            timTheoDaoDien(movie, daoDienCanTim);
	            break;
	        }
	        case 5: {
	            System.out.print("Nhập đánh giá bắt đầu của phim: ");
	            int danhGiaBatDau = kiemTraDauVao(0, 100);
	            System.out.print("Nhập đánh giá kết thúc của phim: ");
	            int danhGiaKetThuc = kiemTraDauVao(0, 100);
	            timTheoDanhGia(movie, danhGiaBatDau, danhGiaKetThuc);
	            break;
	        }
	        default:
	            System.out.println("Lựa chọn không hợp lệ.");
	    }
	    return luaChon;
	}

	static void timTheoTen(Phim[] movie, String tenPhimCanTim) {
	    boolean timThay = false;
	    for (Phim phim : movie) {
	        if (phim.tenPhim.equalsIgnoreCase(tenPhimCanTim)) {
	            Phim[] phimTimThay = {phim}; // Tạo một mảng chứa phim cần tìm thấy
	            inPhim(phimTimThay); // Gọi inPhim với mảng chứa phim cần tìm thấy
	            timThay = true;
	            break; // Kết thúc vòng lặp khi tìm thấy phim
	        }
	    }
	    if (!timThay) {
	        System.out.println("Không tìm thấy phim với tên: " + tenPhimCanTim);
	    }
	}

	static void timTheoTheLoai(Phim[] movie, String theLoaiCanTim) {
	    boolean timThay = false;
	    for (Phim phim : movie) {
	        if (phim.theLoai.equalsIgnoreCase(theLoaiCanTim)) {
	        	 Phim[] phimTimThay = {phim}; // Tạo một mảng chứa phim cần tìm thấy 
	        	inPhim(phimTimThay);
	            timThay = true;
	        }
	    }
	    if (!timThay) {
	        System.out.println("Không tìm thấy phim với thể loại: " + theLoaiCanTim);
	    }
	}

	static void timTheoNamSanXuat(Phim[] movie, int namSanXuatCanTim) {
	    boolean timThay = false;
	    for (Phim phim : movie) {
	        if (phim.namSanXuat == namSanXuatCanTim) {
	        	 Phim[] phimTimThay = {phim}; // Tạo một mảng chứa phim cần tìm thấy
	        	inPhim(phimTimThay);
	            timThay = true;
	        }
	    }
	    if (!timThay) {
	        System.out.println("Không tìm thấy phim sản xuất năm: " + namSanXuatCanTim);
	    }
	}

	static void timTheoDaoDien(Phim[] movie, String daoDienCanTim) {
	    boolean timThay = false;
	    for (Phim phim : movie) {
	        if (phim.daoDien.equalsIgnoreCase(daoDienCanTim)) {
	        	 Phim[] phimTimThay = {phim}; // Tạo một mảng chứa phim cần tìm thấy 
	       
	        	 inPhim(phimTimThay);
	            timThay = true;
	        }
	    }
	    if (!timThay) {
	        System.out.println("Không tìm thấy phim của đạo diễn: " + daoDienCanTim);
	    }
	}

	static void timTheoDanhGia(Phim[] movie, int danhGiaBatDau, int danhGiaKetThuc) {
	    boolean timThay = false;
	    for (Phim phim : movie) {
	        if (phim.danhGia >= danhGiaBatDau && phim.danhGia <= danhGiaKetThuc) {
	            Phim[] phimTimThay = {phim}; // Tạo một mảng chứa đối tượng Phim đủ điều kiện
	            inPhim(phimTimThay); // Truyền mảng vào thay vì đối tượng
	            timThay = true;
	            // Thêm 'break;' ở đây nếu bạn chỉ muốn in phim đầu tiên thỏa mãn điều kiện
	            // Nếu bạn muốn in tất cả phim thỏa mãn, không thêm 'break;'
	        }
	    }
	    if (!timThay) {
	        System.out.println("Không tìm thấy phim nào thỏa mãn điều kiện.");
	    }
	}



	static Phim[] themPhim(Phim[] danhSachPhim) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Nhập số lượng phim muốn thêm:");
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
			System.out.print("Đánh Giá: ");
			int danhGia = kiemTraDauVao(0, 100);

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
		System.out.print("Đánh Giá mới: ");
		danhSachPhim[viTriPhim].danhGia = kiemTraDauVao(0, 100);

		System.out.println("Cập nhật thông tin phim thành công.");
	}
	static Phim[] docFile() {
	    try {
	        FileReader fr = new FileReader("danhsachphim.txt");
	        BufferedReader br = new BufferedReader(fr);
	        String line;
	        ArrayList<Phim> phimList = new ArrayList<>();
	        while ((line = br.readLine()) != null) {
	            String[] phimInfo = line.split(", ");
	            if (phimInfo.length == 5) {
	                Phim phim = new Phim(phimInfo[0], phimInfo[1], Integer.parseInt(phimInfo[2]), phimInfo[3], Integer.parseInt(phimInfo[4]));
	                phimList.add(phim);
	            }
	        }
	        br.close();
	        fr.close();
	        System.out.println("Đọc file thành công.");
	        return phimList.toArray(new Phim[0]);
	    } catch (IOException e) {
	        System.out.println("Có lỗi khi đọc file: " + e.getMessage());
	        return null;
	    }
	}
	static void xuatFile(Phim[] danhSachPhim) {
	    try {
	        FileWriter fw = new FileWriter("danhsachphim.txt");
	        BufferedWriter bw = new BufferedWriter(fw);
	        for (Phim phim : danhSachPhim) {
	            bw.write(phim.tenPhim + ", " + phim.daoDien + ", " + phim.namSanXuat + ", " + phim.theLoai + ", " + phim.danhGia);
	            bw.newLine();
	        }
	        bw.close();
	        fw.close();
	        System.out.println("Xuất file thành công.");
	    } catch (IOException e) {
	        System.out.println("Có lỗi khi xuất file: " + e.getMessage());
	    }
	}


}
