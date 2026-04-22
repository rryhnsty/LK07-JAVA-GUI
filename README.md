# Aplikasi Data Siswa - Java GUI

## Deskripsi

Aplikasi Data Siswa merupakan program berbasis Java GUI yang digunakan untuk mengelola data siswa secara sederhana. Data yang dikelola meliputi Nomor Induk Siswa (NIS), nama, dan alamat. Aplikasi ini dibuat untuk memenuhi tugas praktikum pemrograman dengan mengimplementasikan konsep Graphical User Interface (GUI), File I/O, serta operasi CRUD (Create, Read, Update, Delete).

Data disimpan dalam file berformat CSV (siswa.csv), sehingga setiap perubahan data akan tersimpan secara permanen dan dapat ditampilkan kembali saat program dijalankan.

---

## Fitur Sistem

Aplikasi ini memiliki beberapa fitur utama, yaitu:

* Menambahkan data siswa (Create)
* Menampilkan data siswa dalam bentuk tabel (Read)
* Mengubah data siswa (Update)
* Menghapus data siswa (Delete)
* Mengosongkan form input (Clear)
* Validasi untuk mencegah duplikasi NIS

---

## Teknologi yang Digunakan

* Java
* Java Swing (GUI)
* File I/O (BufferedReader & BufferedWriter)

---

## Struktur Program

Program terdiri dari satu kelas utama, yaitu:

**SiswaApp.java**

* Mengatur tampilan GUI (JFrame, JPanel, JButton, JTable)
* Mengelola input dan event handling
* Mengimplementasikan operasi CRUD
* Mengelola penyimpanan data menggunakan file CSV

---

## Struktur File

```id="c9u3qk"
SiswaApp.java
siswa.csv
README.md
```

---

## Cara Menjalankan

1. Pastikan Java sudah terinstall
2. Compile program:

   ```
   javac SiswaApp.java
   ```
3. Jalankan program:

   ```
   java SiswaApp
   ```

---

## Cara Kerja Sistem

Saat program dijalankan, data dari file siswa.csv akan dibaca dan ditampilkan pada tabel. Pengguna dapat menambahkan, mengubah, atau menghapus data melalui tombol yang tersedia. Setiap perubahan data akan langsung disimpan kembali ke dalam file agar tetap sinkron dengan tampilan tabel.

---

## Anggota Kelompok

| Nama                             | NIM             | Peran                                        |
| -------------------------------- | --------------- | -------------------------------------------- |
| Lungga Elyas Yahya               | 255150201111028 | Analisis Hasil & membuat program dan laporan |
| Rafi Ananta Adi                  | 255150201111032 | Analisis Hasil & membuat program dan laporan |
| Muhammad Reyhan Setya Ardiansyah | 255150207111052 | Analisis Hasil & membuat program dan laporan |
| Ariq Dwi Anugrah                 | 255150207111053 | Analisis Hasil & membuat program dan laporan |
| Faeyza Safa Izz Deyardi          | 255150207111060 | Analisis Hasil & membuat program dan laporan |

---

## Catatan

* File siswa.csv akan dibuat otomatis jika belum tersedia
* Aplikasi ini masih sederhana dan dapat dikembangkan lebih lanjut sesuai kebutuhan

---
