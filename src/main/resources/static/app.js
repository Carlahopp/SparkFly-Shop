const API_URL = '/api/products';
const productGrid = document.getElementById('product-grid');
const productForm = document.getElementById('product-form');
const statusMessage = document.getElementById('status-message');
const btnAll = document.getElementById('btn-all');
const btnAvailable = document.getElementById('btn-available');

async function loadProducts(endpoint = API_URL) {
    try {
        const response = await fetch(endpoint);
        const products = await response.json();
        renderProducts(products);
    } catch (error) {
        productGrid.innerHTML = '<p>Error al cargar los productos desde la API.</p>';
    }
}

function renderProducts(products) {
    productGrid.innerHTML = '';

    if (products.length === 0) {
        productGrid.innerHTML = '<p>No hay productos para mostrar.</p>';
        return;
    }

    products.forEach(product => {
        const card = document.createElement('div');
        card.className = 'card';

        const isOutOfStock = product.stock === 0;
        const stockText = isOutOfStock ? 'Agotado' : `Stock: ${product.stock} unidades`;
        const categoryName = product.category ? product.category.nombreCategoria : 'Sin categoría';
        const imgSrc = product.imageUrl || 'img/default.jpg';
        const descText = product.description || 'Sin descripción disponible.';

        card.innerHTML = `
            <div>
                <img src="${imgSrc}" alt="${product.name}" style="width: 100%; height: 160px; object-fit: contain; border-radius: 8px; margin-bottom: 10px;">
                <span class="badge">${categoryName}</span>
                <h3>${product.name}</h3>
                <p style="font-size: 0.9rem; color: #555;">${descText}</p>
            </div>
            <div>
                <p class="price">$${product.price.toFixed(2)}</p>
                <p class="stock ${isOutOfStock ? 'out' : ''}">${stockText}</p>
            </div>
        `;
        productGrid.appendChild(card);
    });
}

productForm.addEventListener('submit', async (event) => {
    event.preventDefault();

    const categoryInput = document.getElementById('category').value.trim();

    const newProduct = {
        name: document.getElementById('name').value.trim(),
        description: document.getElementById('description').value.trim(),
        category: {
            nombreCategoria: categoryInput
        },
        price: parseFloat(document.getElementById('price').value),
        stock: parseInt(document.getElementById('stock').value, 10),
        imageUrl: document.getElementById('imageUrl').value.trim() || 'img/default.jpg'
    };

    try {
        const response = await fetch(API_URL, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(newProduct)
        });

        if (!response.ok) {
            const errorData = await response.json();
            throw new Error(errorData.message || 'No se pudo guardar el producto.');
        }

        statusMessage.textContent = '¡Producto agregado correctamente!';
        statusMessage.style.color = '#16a34a';
        productForm.reset();
        loadProducts();
    } catch (error) {
        statusMessage.textContent = `Error: ${error.message}`;
        statusMessage.style.color = '#dc2626';
    }
});

btnAll.addEventListener('click', () => {
    btnAll.classList.add('active');
    btnAvailable.classList.remove('active');
    loadProducts(API_URL);
});

btnAvailable.addEventListener('click', () => {
    btnAvailable.classList.add('active');
    btnAll.classList.remove('active');
    loadProducts(`${API_URL}/available`);
});

loadProducts();
