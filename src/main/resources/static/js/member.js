document.addEventListener('DOMContentLoaded', function() {
    // Initialize tooltips
    const tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'));
    tooltipTriggerList.map(function (tooltipTriggerEl) {
        return new bootstrap.Tooltip(tooltipTriggerEl);
    });

    // Sidebar active link management
    const currentPath = window.location.pathname;
    const navLinks = document.querySelectorAll('.member-sidebar-item');
    
    navLinks.forEach(link => {
        if (link.getAttribute('href') === currentPath) {
            link.classList.add('active');
        }
        
        link.addEventListener('click', function(e) {
            navLinks.forEach(item => item.classList.remove('active'));
            this.classList.add('active');
        });
    });

    // Sample book data - in real app this would come from an API
    const sampleBooks = [
        {
            title: "Pemrograman Java",
            author: "Budi Raharjo",
            cover: "/images/book1.jpg",
            available: true
        },
        {
            title: "Machine Learning Dasar",
            author: "Widodo Budiharto",
            cover: "/images/book2.jpg",
            available: false
        }
    ];

    // Load featured books
    function loadFeaturedBooks() {
        const bookContainer = document.getElementById('featured-books');
        
        sampleBooks.forEach(book => {
            const bookCol = document.createElement('div');
            bookCol.className = 'col-md-4 mb-4';
            
            bookCol.innerHTML = `
                <div class="card book-card h-100">
                    <img src="${book.cover}" class="card-img-top book-cover" alt="${book.title}">
                    <div class="card-body">
                        <h5 class="card-title">${book.title}</h5>
                        <p class="card-text text-muted">${book.author}</p>
                    </div>
                    <div class="card-footer bg-white">
                        <button class="btn btn-sm ${book.available ? 'btn-success' : 'btn-secondary'} w-100" 
                                ${!book.available ? 'disabled' : ''}>
                            ${book.available ? 'Pinjam' : 'Dipinjam'}
                        </button>
                    </div>
                </div>
            `;
            
            bookContainer.appendChild(bookCol);
        });
    }
    
    loadFeaturedBooks();
});