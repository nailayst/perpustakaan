document.addEventListener('DOMContentLoaded', function() {
    // Password validation for registration form
    const registerForm = document.querySelector('form[th\\:action="@{/auth/register}"]');
    if (registerForm) {
        registerForm.addEventListener('submit', function(e) {
            const password = document.getElementById('password').value;
            const confirmPassword = document.getElementById('confirmPassword').value;
            
            if (password !== confirmPassword) {
                e.preventDefault();
                showAlert('Password dan konfirmasi password tidak sama!', 'danger');
            }
        });
    }

    // Function to show alert messages
    function showAlert(message, type) {
        const alertDiv = document.createElement('div');
        alertDiv.className = `alert alert-${type} auth-alert alert-dismissible fade show`;
        alertDiv.role = 'alert';
        alertDiv.innerHTML = `
            ${message}
            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
        `;
        
        const form = document.querySelector('.auth-form') || document.querySelector('form');
        form.parentNode.insertBefore(alertDiv, form);
        
        // Auto dismiss after 5 seconds
        setTimeout(() => {
            const alert = bootstrap.Alert.getOrCreateInstance(alertDiv);
            alert.close();
        }, 5000);
    }

    // Add icons to form inputs dynamically
    function addInputIcons() {
        const inputs = {
            '#username': 'fa-user',
            '#password': 'fa-lock',
            '#email': 'fa-envelope',
            '#fullName': 'fa-id-card'
        };
        
        for (const [selector, icon] of Object.entries(inputs)) {
            const input = document.querySelector(selector);
            if (input) {
                const wrapper = document.createElement('div');
                wrapper.className = 'input-group mb-3';
                
                const span = document.createElement('span');
                span.className = 'input-group-text';
                span.innerHTML = `<i class="fas ${icon}"></i>`;
                
                input.parentNode.insertBefore(wrapper, input);
                wrapper.appendChild(span);
                wrapper.appendChild(input);
            }
        }
    }
    
    addInputIcons();
});