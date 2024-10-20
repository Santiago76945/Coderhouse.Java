CREATE TABLE clients (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL
);

CREATE TABLE products (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    price DOUBLE NOT NULL,
    stock INT NOT NULL
);

CREATE TABLE sales (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    client_id INT,
    total DOUBLE NOT NULL,
    total_quantity INT NOT NULL,
    sale_date DATE,
    FOREIGN KEY (client_id) REFERENCES clients(id)
);

CREATE TABLE sale_lines (
    id INT AUTO_INCREMENT PRIMARY KEY,
    sale_id BIGINT,
    product_id BIGINT,
    quantity INT NOT NULL,
    price_at_sale DOUBLE NOT NULL,
    FOREIGN KEY (sale_id) REFERENCES sales(id),
    FOREIGN KEY (product_id) REFERENCES products(id)
);
