# API Product Management with Authentication

## Deskripsi
Proyek ini adalah sebuah API untuk manajemen produk dengan fitur CRUD (Create, Read, Update, Delete). API ini menggunakan **Spring Boot** dan dilengkapi dengan sistem autentikasi sederhana untuk memastikan keamanan akses. Setiap operasi API memberikan respons yang informatif dengan pesan status yang jelas. API ini juga mendukung tampilan web dengan Spring MVC & Thymeleaf.

## Fitur Utama
1. **Create Product**: Menambah produk baru ke sistem dengan data seperti nama, deskripsi, dan harga.
2. **Get All Products**: Mengambil daftar semua produk yang tersedia di sistem.
3. **Get Product by ID**: Mengambil detail produk berdasarkan ID yang diberikan.
4. **Update Product**: Memperbarui detail produk yang ada, termasuk nama, deskripsi, dan harga.
5. **Delete Product**: Menghapus produk berdasarkan ID.
6. **Autentikasi Sederhana**: API ini menggunakan DTO untuk memberikan pesan respons yang jelas tentang hasil setiap operasi.

## Teknologi yang Digunakan
- **Spring Boot**: Untuk membangun REST API.
- **Hibernate (JPA)**: Untuk mengelola interaksi dengan database.
- **DTO (Data Transfer Object)**: Untuk pengelolaan data dan transfer informasi antar layer.
- **Validation**: Penggunaan anotasi seperti `@NotBlank` untuk memastikan data yang valid.

## Endpoint API


### 0. Register User
- **Method:** `POST`
- **Endpoint:** `/api/auth/register`
- **Request Body:**
  ```json
  {
    "username": "john_doe",
    "password": "password123"
  }

### 1. **Create Product**
   - **Method:** `POST`
   - **Endpoint:** `/api/products`
   - **Request Body:**
     ```json
     {
       "name": "Laptop",
       "description": "Laptop gaming dengan spesifikasi tinggi",
       "price": 15000000
     }
     ```
   - **Response:**
     ```json
     {
       "message": "Product created successfully",
       "data": {
         "id": 1,
         "name": "Laptop",
         "description": "Laptop gaming dengan spesifikasi tinggi",
         "price": 15000000
       }
     }
     ```

### 2. **Get All Products**
   - **Method:** `GET`
   - **Endpoint:** `/api/products`
   - **Response:**
     ```json
     {
       "message": "Successfully fetched all products",
       "data": [
         {
           "id": 1,
           "name": "Laptop",
           "description": "Laptop gaming dengan spesifikasi tinggi",
           "price": 15000000
         },
         {
           "id": 2,
           "name": "Smartphone",
           "description": "Smartphone dengan kamera terbaik",
           "price": 5000000
         }
       ]
     }
     ```

### 3. **Get Product by ID**
   - **Method:** `GET`
   - **Endpoint:** `/api/products/{id}`
   - **Response:**
     - **Success (Product Found):**
       ```json
       {
         "message": "Product found",
         "data": {
           "id": 1,
           "name": "Laptop",
           "description": "Laptop gaming dengan spesifikasi tinggi",
           "price": 15000000
         }
       }
       ```
     - **Error (Product Not Found):**
       ```json
       {
         "message": "Product not found",
         "data": null
       }
       ```

### 4. **Update Product**
   - **Method:** `PUT`
   - **Endpoint:** `/api/products/{id}`
   - **Request Body:**
     ```json
     {
       "name": "Laptop",
       "description": "Laptop gaming dengan spesifikasi tinggi, upgrade terbaru",
       "price": 16000000
     }
     ```
   - **Response:**
     - **Success (Product Updated):**
       ```json
       {
         "message": "Product updated successfully",
         "data": {
           "id": 1,
           "name": "Laptop",
           "description": "Laptop gaming dengan spesifikasi tinggi, upgrade terbaru",
           "price": 16000000
         }
       }
       ```
     - **Error (Product Not Found):**
       ```json
       {
         "message": "Product not found",
         "data": null
       }
       ```

### 5. **Delete Product**
   - **Method:** `DELETE`
   - **Endpoint:** `/api/products/{id}`
   - **Response:**
     - **Success (Product Deleted):**
       ```json
       {
         "message": "Product successfully deleted",
         "data": null
       }
       ```
     - **Error (Product Not Found):**
       ```json
       {
         "message": "Product not found",
         "data": null
       }
       ```

## Instalasi dan Pengaturan

### Prerequisites
Pastikan Anda telah menginstal:
- **Java 17** atau lebih tinggi
- **Maven**
- **MySQL** (atau database yang kompatibel)

### Langkah-langkah Instalasi
1. **Clone repository ini:**
   ```bash
   git clone <URL-repository>
   cd <folder-proyek>

## Directory Hierarchy
```

|—— HELP.md
|—— mvnw
|—— mvnw.cmd
|—— pom.xml
|—— src
|    |—— .DS_Store
|    |—— main
|        |—— .DS_Store
|        |—— java
|            |—— com
|                |—— example
|                    |—— apiauthcrud
|                        |—— Apiauthcrudapplication.java
|                        |—— config
|                            |—— SecurityConfig.java
|                        |—— controller
|                            |—— AuthController.java
|                            |—— ProductController.java
|                        |—— dto
|                            |—— ApiResponseDTO.java
|                            |—— AuthResponseDTO.java
|                            |—— ProductDTO.java
|                            |—— UserDTO.java
|                        |—— model
|                            |—— Product.java
|                            |—— User.java
|                        |—— repository
|                            |—— ProductRepository.java
|                            |—— UserRepository.java
|                        |—— security
|                            |—— JwtAuthFilter.java
|                            |—— JwtUtil.java
|                        |—— service
|                            |—— AuthService.java
|                            |—— CustomUserDetailsService.java
|                            |—— ProductService.java
|        |—— resources
|            |—— application.properties
|    |—— test
|        |—— java
|            |—— com
|                |—— example
|                    |—— apiauthcrud
|                        |—— ApiauthcrudApplicationTests.java
|—— 
```
## Code Details
### Tested Platform
- software
  ```
  OS: Debian unstable (May 2021), Ubuntu LTS
  Python: 3.8.5 (anaconda)
  PyTorch: 1.7.1, 1.8.1
  ```
- hardware
  ```
  CPU: Intel Xeon 6226R
  GPU: Nvidia RTX3090 (24GB)
  ```
### Hyper parameters
```
```
## References
- [paper-1]()
- [paper-2]()
- [code-1](https://github.com)
- [code-2](https://github.com)
  
## License

## Citing
If you use xxx,please use the following BibTeX entry.
```
```
