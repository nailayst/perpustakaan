document.addEventListener('DOMContentLoaded', function() {
    // Initialize tooltips
    const tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'));
    tooltipTriggerList.map(function (tooltipTriggerEl) {
        return new bootstrap.Tooltip(tooltipTriggerEl);
    });

    // Sidebar active link management
    const currentPath = window.location.pathname;
    const navLinks = document.querySelectorAll('.sidebar-item');
    
    navLinks.forEach(link => {
        if (link.getAttribute('href') === currentPath) {
            link.classList.add('active');
        }
        
        link.addEventListener('click', function(e) {
            navLinks.forEach(item => item.classList.remove('active'));
            this.classList.add('active');
        });
    });

    // Dynamic stats update (example)
    function updateStats() {
        // In a real app, you would fetch this from an API
        const stats = {
            books: 1254,
            members: 586,
            transactions: 124
        };
        
        document.getElementById('stats-books').textContent = stats.books.toLocaleString();
        document.getElementById('stats-members').textContent = stats.members.toLocaleString();
        document.getElementById('stats-transactions').textContent = stats.transactions.toLocaleString();
    }
    
    updateStats();
    
    // Recent activities (example)
    function loadRecentActivities() {
        // In a real app, you would fetch this from an API
        const activities = [
            {
                icon: 'book',
                color: 'primary',
                time: '10 menit lalu',
                description: 'Buku baru "Machine Learning Fundamentals" ditambahkan'
            },
            {
                icon: 'user-plus',
                color: 'success',
                time: '1 jam lalu',
                description: 'Anggota baru "Rina Wijaya" terdaftar'
            }
        ];
        
        const activityContainer = document.getElementById('activity-feed');
        
        activities.forEach(activity => {
            const activityItem = document.createElement('div');
            activityItem.className = 'activity-item';
            activityItem.innerHTML = `
                <div class="d-flex">
                    <div class="me-3 text-${activity.color}">
                        <i class="fas fa-${activity.icon}"></i>
                    </div>
                    <div>
                        <small class="text-muted">${activity.time}</small>
                        <p class="mb-0">${activity.description}</p>
                    </div>
                </div>
            `;
            activityContainer.appendChild(activityItem);
        });
    }
    
    loadRecentActivities();
});