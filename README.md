## Reservation API Tempat Olahraga dengan pattern BFF (Backend for Frontend)

### 0. Login & Register
* `POST /api/web/auth/signin`: Login admin web.
* `POST /api/mobile/auth/signin`: Login user mobile.
* `POST /api/mobile/auth/signup`: Registrasi user mobile.
* `POST /api/mobile/auth/refresh`: Refresh autentikasi token.
  
### 1. API Pengguna

**Web API**

* `GET /api/web/users`: Mengambil daftar pengguna.
* `GET /api/web/users/{id}`: Mengambil detail pengguna berdasarkan ID.
* `POST /api/web/users`: Menambahkan pengguna baru.
* `PUT /api/web/users/{id}`: Memperbarui data pengguna berdasarkan ID.
* `DELETE /api/web/users/{id}`: Menghapus pengguna berdasarkan ID.

**Mobile API**

* `GET /api/mobile/users/profile`: Mengambil profil pengguna yang sedang login.
* `PUT /api/mobile/users/profile`: Memperbarui profil pengguna yang sedang login.

**Third-Party API**

* `POST /api/thirdparty/users`: Integrasi pengguna baru dari pihak ketiga.

### 2. API Tempat Olahraga

**Web API**

* `GET /api/web/venues`: Mengambil daftar tempat olahraga.
* `GET /api/web/venues/{id}`: Mengambil detail tempat olahraga berdasarkan ID.
* `POST /api/web/venues`: Menambahkan tempat olahraga baru.
* `PUT /api/web/venues/{id}`: Memperbarui data tempat olahraga berdasarkan ID.
* `DELETE /api/web/venues/{id}`: Menghapus tempat olahraga berdasarkan ID.

**Mobile API**

* `GET /api/mobile/venues`: Mengambil daftar tempat olahraga.
* `GET /api/mobile/venues/{id}`: Mengambil detail tempat olahraga berdasarkan ID.

**Third-Party API**

* `GET /api/thirdparty/venues`: Mengambil daftar tempat olahraga untuk integrasi.

### 3. API Reservasi

**Web API**

* `GET /api/web/reservations`: Mengambil daftar reservasi.
* `GET /api/web/reservations/{id}`: Mengambil detail reservasi berdasarkan ID.
* `POST /api/web/reservations`: Membuat reservasi baru.
* `PUT /api/web/reservations/{id}`: Memperbarui data reservasi berdasarkan ID.
* `DELETE /api/web/reservations/{id}`: Menghapus reservasi berdasarkan ID.

**Mobile API**

* `GET /api/mobile/reservations`: Mengambil daftar reservasi pengguna yang sedang login.
* `POST /api/mobile/reservations`: Membuat reservasi baru.
* `PUT /api/mobile/reservations/{id}`: Memperbarui data reservasi pengguna yang sedang login berdasarkan ID.
* `DELETE /api/mobile/reservations/{id}`: Membatalkan reservasi pengguna yang sedang login berdasarkan ID.

**Third-Party API**

* `POST /api/thirdparty/reservations`: Membuat reservasi baru melalui integrasi pihak ketiga.

### 4. API Pembayaran

**Web API**

* `GET /api/web/payments`: Mengambil daftar pembayaran.
* `GET /api/web/payments/{id}`: Mengambil detail pembayaran berdasarkan ID.
* `POST /api/web/payments`: Membuat pembayaran baru.
* `PUT /api/web/payments/{id}`: Memperbarui data pembayaran berdasarkan ID.
* `DELETE /api/web/payments/{id}`: Menghapus pembayaran berdasarkan ID.

**Mobile API**

* `GET /api/mobile/payments`: Mengambil daftar pembayaran pengguna yang sedang login.
* `POST /api/mobile/payments`: Membuat pembayaran baru.
* `PUT /api/mobile/payments/{id}`: Memperbarui data pembayaran pengguna yang sedang login berdasarkan ID.

**Third-Party API**

* `POST /api/thirdparty/payments`: Membuat pembayaran baru melalui integrasi pihak ketiga.

### 5. API Notifikasi

**Web API**

* `GET /api/web/notifications`: Mengambil daftar notifikasi.
* `GET /api/web/notifications/{id}`: Mengambil detail notifikasi berdasarkan ID.
* `POST /api/web/notifications`: Menambahkan notifikasi baru.
* `PUT /api/web/notifications/{id}`: Memperbarui data notifikasi berdasarkan ID.
* `DELETE /api/web/notifications/{id}`: Menghapus notifikasi berdasarkan ID.

**Mobile API**

* `GET /api/mobile/notifications`: Mengambil daftar notifikasi pengguna yang sedang login.
* `PUT /api/mobile/notifications/{id}/read`: Menandai notifikasi sebagai telah dibaca.

**Third-Party API**

* `POST /api/thirdparty/notifications`: Menambahkan notifikasi baru melalui integrasi

## Alur Autentikasi dan Otorisasi

Berikut adalah skema autentikasi dan otorisasi yang digunakan untuk mengakses API:

**Jenis Klien** | **Metode Autentikasi** | **Akses yang Diizinkan**
---|---|---|
Web | JWT | Admin (hanya role admin)
Mobile | JWT | User (hanya role user)
Third-Party | API Key | Integrasi pihak ketiga (dengan API key yang valid)

**Detail:**

* **JWT (JSON Web Token):** Digunakan untuk autentikasi Web dan Mobile API. Token JWT harus disertakan dalam header `Authorization` dengan format `Bearer <token>`.
* **API Key:** Digunakan untuk autentikasi Third-Party API. API key harus disertakan dalam header `X-API-KEY` dengan nilai yang valid.

**Contoh Header Request:**

**Web API (dengan JWT):**

```
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9eyJzdWIiOiIxMjM0NTY3ODkwIiwianJhbCI6WyJyb2xlX19hZG1pbiJdfQ==
```

**Mobile API (dengan JWT):**

```
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9eyJzdWIiOiIxMjM0NTY3ODkwIiwianJhbCI6WyJyb2xlX191c2VyIl19
```

**Third-Party API (dengan API Key):**

```
X-API-KEY: be521d5c-0b62-48f9-999c-e1a2367a023f
```

**Catatan:**

* Pastikan untuk mengganti `<token>` dengan JWT Anda yang valid.
* API Key `be521d5c-0b62-48f9-999c-e1a2367a023f` adalah key default yang valid.

## Format Request JSON

Berikut adalah format request JSON untuk berbagai operasi yang tersedia di aplikasi:

**1. Mobile**

* **MobileUserProfileRequest (Memperbarui profil pengguna)**

```json
{
  "name": "John Doe",
  "noTelp": "+6281234567890",
  "email": "john.doe@example.com"
}
```

* **MobileReservationRequest (Membuat reservasi)**

```json
{
  "venueId": 1,
  "reservationDate": "2022-07-25T00:00:00.000+00:00",
  "startTime": "2022-07-25T08:00:00.000+00:00",
  "endTime": "2022-07-25T12:00:00.000+00:00",
 }
```

* **MobilePaymentRequest (Melakukan pembayaran)**

```json
{
  "reservationId": 1,
  "amount": 100.00,
  "paymentMethod": "credit_card"
}
```

**2. Web**

* **WebUserRequest (Membuat pengguna baru)**

```json
{
  "name": "John Doe",
  "noTelp": "+6281234567890",
  "email": "john.doe@example.com",
  "password": "your_password"
}
```

* **WebVenueRequest (Membuat tempat baru)**

```json
{
  "name": "Racquet Club",
  "address": "400 Tennis Ct, Racketville, USA",
  "facilities": "Indoor Tennis Court",
  "pricePerHour": 200000
}
```

* **WebReservationRequest (Membuat reservasi)**

```json
{
  "userIds": [102],
  "venueIds": [52],
  "reservationDate": "2022-07-25T00:00:00.000+00:00",
  "startTime": "2022-07-25T08:00:00.000+00:00",
  "endTime": "2022-07-25T12:00:00.000+00:00",
  "status": "PENDING"
}
```

* **WebPaymentRequest (Melakukan pembayaran)**

```json
{
  "reservationId": 1,
  "amount": 100500.00,
  "paymentDate": "2022-07-25T14:30:00.000+00:00",
  "paymentMethod": "Credit Card",
  "status": "unpaid"
}
```

* **WebNotificationsRequest (Mengirim notifikasi)**

```json
{
  "userId": 1,
  "message": "Reservasi Anda telah dikonfirmasi!",
  "notificationDate": "2022-07-25T14:30:00.000+00:00",
  "status": "UNREAD"
}
```

**3. Thirdparty (Tpt)**

* **TPtUserRequest (Membuat pengguna pihak ketiga)**

```json
{
  "name": "John Doe",
  "noTelp": "+6281234567890",
  "email": "john.doe@example.com",
  "password": "your_password"
}
```

* **TptReservationRequest (Membuat reservasi pihak ketiga)**

```json
{
  "reservationDate": "2024-07-10",
  "startTime": "10:00:00",
  "endTime": "12:00:00",
  "userIds": [1],
  "venueIds": [3]    
}

```

# Skema Database Aplikasi

Skema database ini dirancang untuk aplikasi yang memungkinkan pengguna memesan tempat di berbagai tempat. Sistem ini terdiri dari beberapa tabel yang saling terkait:

## Tabel Pengguna

| Kolom | Tipe Data | Keterangan |
|---|---|---|
| user_id | BIGINT(20) | ID pengguna (primary key) |
| name | VARCHAR(255) | Nama pengguna |
| no_telp | VARCHAR(255) | Nomor telepon pengguna |
| email | VARCHAR(255) | Alamat email pengguna |
| password | VARCHAR(255) | Kata sandi pengguna |
| created_at | DATETIME(6) | Tanggal dan waktu pembuatan akun pengguna |
| updated_at | DATETIME(6) | Tanggal dan waktu terakhir kali akun pengguna diperbarui |

## Tabel Tempat

| Kolom | Tipe Data | Keterangan |
|---|---|---|
| venue_id | BIGINT(20) | ID tempat (primary key) |
| name | VARCHAR(255) | Nama tempat |
| address | VARCHAR(255) | Alamat tempat |
| facilities | VARCHAR(255) | Fasilitas yang tersedia di tempat |
| price_per_hour | DOUBLE | Harga per jam tempat |
| created_at | DATETIME(6) | Tanggal dan waktu pembuatan tempat |
| updated_at | DATETIME(6) | Tanggal dan waktu terakhir kali tempat diperbarui |

## Tabel Reservasi

| Kolom | Tipe Data | Keterangan |
|---|---|---|
| reservation_id | BIGINT(20) | ID reservasi (primary key) |
| user_id | BIGINT(20) | ID pengguna yang memesan tempat (foreign key) |
| venue_id | BIGINT(20) | ID tempat yang dipesan (foreign key) |
| reservation_date | DATETIME(6) | Tanggal reservasi |
| start_time | DATETIME(6) | Waktu mulai reservasi |
| end_time | DATETIME(6) | Waktu akhir reservasi |
| status | ENUM('PENDING', 'CONFIRMED', 'CANCELLED') | Status reservasi |

## Tabel Pembayaran

| Kolom | Tipe Data | Keterangan |
|---|---|---|
| payment_id | BIGINT(20) | ID pembayaran (primary key) |
| reservation_id | BIGINT(20) | ID reservasi yang terkait dengan pembayaran (foreign key) |
| amount | DOUBLE | Jumlah pembayaran |
| payment_date | DATETIME(6) | Tanggal pembayaran |
| payment_method | VARCHAR(255) | Metode pembayaran |

## Tabel Notifikasi

| Kolom | Tipe Data | Keterangan |
|---|---|---|
| notification_id | BIGINT(20) | ID notifikasi (primary key) |
| user_id | BIGINT(20 | ID pengguna yang menerima notifikasi (foreign key) |
| message | VARCHAR(255) | Isi notifikasi |
| notification_date | DATETIME | Tanggal dan waktu notifikasi dikirim |
| status | ENUM('unread', 'read') | Status notifikasi |


