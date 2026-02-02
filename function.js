// Extracted login functions
document.getElementById('loginForm').addEventListener('submit', function(e) {
    e.preventDefault();
    
    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;
    
    // Demo validation
    if (username && password) {
        // Store user session
        localStorage.setItem('user', JSON.stringify({
            username: username,
            loginTime: new Date().toISOString()
        }));
        
        // Redirect to dashboard
        window.location.href = 'index.html';
    } else {
        showError('Please enter both username and password');
    }
});

function showError(msg) {
    const errorDiv = document.getElementById('errorMessage');
    errorDiv.textContent = msg;
    errorDiv.style.display = 'block';
}

// Check if user is already logged in
window.addEventListener('load', () => {
    const user = localStorage.getItem('user');
    if (user) {
        window.location.href = 'index.html';
    }
});
