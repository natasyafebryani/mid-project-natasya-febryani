import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

public class Main {
	
	Scanner scan = new Scanner(System.in);
	Random rand = new Random();
	
	Vector<Karyawan> vKaryawan = new Vector<>();
	
	public Main() {
		mainMenu();
	}

	private void mainMenu() {
		
		int menu;
		do {
			clear();
			System.out.println("  PT. Mentol Application  ");
			System.out.println("==========================");
			System.out.println("1. Insert Data Karyawan");
			System.out.println("2. View Data Karyawan");
			System.out.println("3. Update Data Karyawan");
			System.out.println("4. Delete Data Karyawan");
			System.out.println("5. Exit");
			System.out.print("Choose >> ");
			menu = catchNumber();
			
			switch (menu) {
			case 1:
				inputKaryawan();
				break;
			case 2:
				viewKaryawan();
				break;
			case 3:
				updateKaryawan();
				break;
			case 4:
				deleteKaryawan();
				break;
			case 5:
				System.exit(0);
				break;
			}
			
		} while (menu != 5);
		
	}

	private void deleteKaryawan() {
		
		if(vKaryawan.size() == 0) {
			System.out.println("No Data!");
			System.out.println("Press enter to continue..");
			scan.nextLine();
			return;
		}else {
			viewKaryawan();
			int index;
			do {
				System.out.print("Masukkan nomor yang ingin dihapus : ");
				index = catchNumber();
			} while (index < 1 || index > vKaryawan.size());
			
			index--;
			
			String kode = vKaryawan.get(index).getKode();
			
			vKaryawan.remove(index);
			System.out.println("Karyawan dengan kode " + kode + " berhasil dihapus");
			
		}
		
		
	}

	private void updateKaryawan() {
		
		if(vKaryawan.size() == 0) {
			System.out.println("No Data!");
			System.out.println("Press enter to continue..");
			scan.nextLine();
			return;
		}else {
			viewKaryawan();
			int index;
			do {
				System.out.print("Masukkan nomor yang ingin diupdate : ");
				index = catchNumber();
				index--;
			} while (index < 0 || index > vKaryawan.size());
			
			boolean validKode = true;
			String kode;
			do {
				System.out.print("Input kode karyawan [MM-XXXX]: ");
				kode = scan.nextLine();
				
				for (int i = 0; i < 2; i++) {
					if(!Character.isAlphabetic(kode.charAt(i))){
						validKode = false;
					} else {
						validKode = true;
					}
				}
				
				if(!kode.contains("-")) {
					validKode = false;
				} else {
					validKode = true;
				}
				
				for (int i = 3; i < 7; i++) {
					if(!Character.isDigit(kode.charAt(i))){
						validKode = false;
					} else {
						validKode = true;
					}
				}
				
			} while (!validKode || kode.length() != 7);
			
			String nama, jenisKelamin, jabatan;
			int gaji;
			
			boolean validNama = true;
			do {
				System.out.print("Input nama karyawan [>= 3]: ");
				nama = scan.nextLine();
				
				for (int i = 0; i < nama.length(); i++) {
					if(!Character.isAlphabetic(nama.charAt(i))) {
						validNama = false;
					} else {
						validNama = true;
					}
				}
				
			} while (!validNama || nama.length() < 3);
			
			do {
				System.out.print("Input jenis kelamin [Laki-laki | Perempuan] (Case Sensitive): ");
				jenisKelamin = scan.nextLine();
			} while (!jenisKelamin.equals("Laki-laki") && !jenisKelamin.equals("Perempuan"));

			do {
				System.out.print("Input jabatan [Manager | Supervisor | Admin] (Case Sensitive): ");
				jabatan = scan.nextLine();
			} while (!jabatan.equals("Manager") && !jabatan.equals("Supervisor") && !jabatan.equals("Admin"));
			
			if(jabatan.equals("Manager")) {
				gaji = 8000000;
			} else if(jabatan.equals("Supervisor")) {
				gaji = 6000000;
			} else {
				gaji = 4000000;
			}
			
			vKaryawan.get(index).setGaji(gaji);
			vKaryawan.get(index).setJabatan(jabatan);
			vKaryawan.get(index).setJenisKelamin(jenisKelamin);
			vKaryawan.get(index).setKode(kode);
			vKaryawan.get(index).setNama(nama);
		}
		
		
	}

	private void viewKaryawan() {
		
		if(vKaryawan.size() == 0) {
			System.out.println("No Data!");
			System.out.println("Press enter to continue..");
			scan.nextLine();
			return;
		} else {
			bubbleSort();
			System.out.println("|----|-----------------|------------------------------|---------------|--------------|-------------|");
			System.out.println("|No  |Kode Karyawan    |Nama Karyawan                 |Jenis Kelamin  |Jabatan       |Gaji Karyawan|");
			System.out.println("|----|-----------------|------------------------------|---------------|--------------|-------------|");
			for (int i = 0; i < vKaryawan.size(); i++) {
				System.out.printf("|%4d|%17s|%30s|%15s|%14s|%13d|", (i+1), vKaryawan.get(i).getKode(), vKaryawan.get(i).getNama(), vKaryawan.get(i).getJenisKelamin(), vKaryawan.get(i).getJabatan(), vKaryawan.get(i).getGaji());
				System.out.println();
			}
			
			System.out.println("|----|-----------------|------------------------------|---------------|--------------|-------------|");
			
		}
		
	}

	private void inputKaryawan() {
		
		String kode = "", nama, jenisKelamin, jabatan;
		int gaji;
		
		for (int i = 0; i < 2; i++) {
			kode += (char)(rand.nextInt(26) + 65);
		}
		
		kode += "-";
		
		for (int i = 0; i < 4; i++) {
			kode += rand.nextInt(10);
		}
		
		boolean validNama = true;
		do {
			System.out.print("Input nama karyawan [>= 3]: ");
			nama = scan.nextLine();
			
			for (int i = 0; i < nama.length(); i++) {
				if(!Character.isAlphabetic(nama.charAt(i))) {
					validNama = false;
				} else {
					validNama = true;
				}
			}
			
		} while (!validNama || nama.length() < 3);
		
		do {
			System.out.print("Input jenis kelamin [Laki-laki | Perempuan] (Case Sensitive): ");
			jenisKelamin = scan.nextLine();
		} while (!jenisKelamin.equals("Laki-laki") && !jenisKelamin.equals("Perempuan"));

		do {
			System.out.print("Input jabatan [Manager | Supervisor | Admin] (Case Sensitive): ");
			jabatan = scan.nextLine();
		} while (!jabatan.equals("Manager") && !jabatan.equals("Supervisor") && !jabatan.equals("Admin"));
		
		if(jabatan.equals("Manager")) {
			gaji = 8000000;
			vKaryawan.add(new Manager(kode, nama, jenisKelamin, jabatan, gaji));
		} else if(jabatan.equals("Supervisor")) {
			gaji = 6000000;
			vKaryawan.add(new Supervisor(kode, nama, jenisKelamin, jabatan, gaji));
		} else {
			gaji = 4000000;
			vKaryawan.add(new Admin(kode, nama, jenisKelamin, jabatan, gaji));
		}
		
		System.out.println("Berhasil menambahkan karyawan dengan id " + kode);
		generateBonus();
		System.out.println();
		
		System.out.println("ENTER to return");
		scan.nextLine();
		
	}

	private void bubbleSort() {
		for (int i = 0; i < vKaryawan.size(); i++) {
			for (int j = i+1; j < vKaryawan.size(); j++) {
				if (vKaryawan.get(i).getNama().compareTo(vKaryawan.get(j).getNama()) > 0) {
					// kode
					String temp = vKaryawan.get(i).getKode();
					vKaryawan.get(i).setKode(vKaryawan.get(j).getKode());
					vKaryawan.get(j).setKode(temp);
					
					// nama
					temp = vKaryawan.get(i).getNama();
					vKaryawan.get(i).setNama(vKaryawan.get(j).getNama());
					vKaryawan.get(j).setNama(temp);
					
					// jenis kelamin
					temp = vKaryawan.get(i).getJenisKelamin();
					vKaryawan.get(i).setJenisKelamin(vKaryawan.get(j).getJenisKelamin());
					vKaryawan.get(j).setJenisKelamin(temp);
					
					// jabatan
					temp = vKaryawan.get(i).getJabatan();
					vKaryawan.get(i).setJabatan(vKaryawan.get(j).getJabatan());
					vKaryawan.get(j).setJabatan(temp);
					
					// gaji
					int t = vKaryawan.get(i).getGaji();
					vKaryawan.get(i).setGaji(vKaryawan.get(j).getGaji());
					vKaryawan.get(j).setGaji(t);
				}
			}
		}
	}

	private void generateBonus() {
		
		int countManager = 0;
		int countSupervisor = 0;
		int countAdmin = 0;
		
		for (int i = 0; i < vKaryawan.size(); i++) {
			if(vKaryawan.get(i) instanceof Manager) {
				countManager++;
				
			} else if (vKaryawan.get(i) instanceof Supervisor) {
				countSupervisor++;
				
			} else if (vKaryawan.get(i) instanceof Admin) {
				countAdmin++;
			}	
		}
		
		
		
		double bonus;
		int gaji;
		if(countManager % 3 == 0 && countManager != 0) {
			
			System.out.print("Bonus sebesar 10% telah diberikan kepada karyawan dengan id ");
			
			for (int i = 0; i < vKaryawan.size(); i++) {
				if(vKaryawan.get(i).getJabatan().equals("Manager")) {
					bonus = 0.1 * vKaryawan.get(i).getGaji();
					gaji = (int) (vKaryawan.get(i).getGaji() + bonus);
					vKaryawan.get(i).setGaji(gaji);
					
					System.out.print(vKaryawan.get(i).getKode() + ", ");
				}
			}
			
		} else if(countSupervisor % 3 == 0 && countSupervisor != 0) {
			
			System.out.print("Bonus sebesar 7,5% telah diberikan kepada karyawan dengan id ");
			
			for (int i = 0; i < vKaryawan.size(); i++) {
				if(vKaryawan.get(i).getJabatan().equals("Supervisor")) {
					bonus = 0.075 * vKaryawan.get(i).getGaji();
					gaji = (int) (vKaryawan.get(i).getGaji() + bonus);
					vKaryawan.get(i).setGaji(gaji);
					
					System.out.print(vKaryawan.get(i).getKode() + ", ");
				}
			}
			
		} else if(countAdmin % 3 == 0 && countAdmin != 0) {
			
			System.out.print("Bonus sebesar 5% telah diberikan kepada karyawan dengan id ");
			
			for (int i = 0; i < vKaryawan.size(); i++) {
				if(vKaryawan.get(i).getJabatan().equals("Admin")) {
					bonus = 0.05 * vKaryawan.get(i).getGaji();
					gaji = (int) (vKaryawan.get(i).getGaji() + bonus);
					vKaryawan.get(i).setGaji(gaji);
					
					System.out.print(vKaryawan.get(i).getKode() + ", ");
				}
			}
		}
		
		
	}
	

	private int catchNumber() {
		int menu;
		try {
			menu = scan.nextInt();
		} catch (Exception e) {
			menu = -1;
		}
		scan.nextLine();
		return menu;
	}
	
	private void clear() {
		for (int i = 0; i < 10; i++) {
			System.out.println();
		}
	}

	public static void main(String[] args) {
		new Main();

	}

}
