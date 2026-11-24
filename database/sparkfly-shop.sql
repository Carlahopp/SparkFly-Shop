-- 1. Crear la base de datos
CREATE DATABASE IF NOT EXISTS sparkfly_shop;
USE sparkfly_shop;

-- 2. Crear un usuario específico para que tu app en Java se conecte (opcional pero recomendado)
CREATE USER 'user_sparkfly'@'localhost' IDENTIFIED BY 'TuPasswordSegura123!';
GRANT ALL PRIVILEGES ON sparkfly_shop.* TO 'user_sparkfly'@'localhost';
FLUSH PRIVILEGES;

-- 3. Crear la tabla para los usuarios/clientes de la tienda
CREATE TABLE usuarios (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    correo VARCHAR(100) NOT NULL UNIQUE,
    contrasena VARCHAR(255) NOT NULL, -- Espacio amplio para guardar el hash encriptado
    rol VARCHAR(20) DEFAULT 'CLIENTE', -- Útil para diferenciar entre 'CLIENTE' y 'ADMIN'
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
USE sparkfly_shop;

-- Tabla de categorías
CREATE TABLE categorias (
    id_categoria INT AUTO_INCREMENT PRIMARY KEY,
    nombre_categoria VARCHAR(100) NOT NULL,
    estado TINYINT(1) DEFAULT 1
);

-- Tabla de productos (relacionada con categorías)
CREATE TABLE productos (
    id_producto INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(150) NOT NULL,
    descripcion TEXT,
    precio DECIMAL(10, 2) NOT NULL,
    stock INT NOT NULL DEFAULT 0,
    imagen_url VARCHAR(255),
    id_categoria INT,
    FOREIGN KEY (id_categoria) REFERENCES categorias(id_categoria)
);

-- Tabla de pedidos (relacionada con usuarios)
CREATE TABLE pedidos (
    id_pedido INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT NOT NULL,
    fecha_pedido TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    total DECIMAL(10, 2) NOT NULL,
    estado_pedido VARCHAR(50) DEFAULT 'PENDIENTE',
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario)
);

-- Tabla intermedia detalle_pedido (conecta pedidos con productos)
CREATE TABLE detalle_pedido (
    id_detalle INT AUTO_INCREMENT PRIMARY KEY,
    id_pedido INT NOT NULL,
    id_producto INT NOT NULL,
    cantidad INT NOT NULL,
    precio_unitario DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (id_pedido) REFERENCES pedidos(id_pedido),
    FOREIGN KEY (id_producto) REFERENCES productos(id_producto)
);
