// Portfolio JavaScript Functions

// Smooth scroll to section
function scrollToSection(sectionId) {
    const section = document.getElementById(sectionId);
    if (section) {
        section.scrollIntoView({ behavior: 'smooth' });
    }
}

// Handle contact form submission
function handleSubmit(event) {
    event.preventDefault();
    const formData = new FormData(event.target);
    const name = formData.get('name');
    
    // Show success message
    alert(`Thank you, ${name}! Your message has been sent successfully. I'll get back to you soon.`);
    event.target.reset();
    
    // Optionally, send to server
    // sendContactForm(formData);
}

// Send contact form data to server (optional backend integration)
function sendContactForm(formData) {
    fetch('/api/contact', {
        method: 'POST',
        body: formData
    })
    .then(response => response.json())
    .then(data => {
        console.log('Success:', data);
        alert('Message sent successfully!');
    })
    .catch(error => {
        console.error('Error:', error);
        alert('Failed to send message. Please try again.');
    });
}

// Toggle user menu dropdown
function toggleUserMenu() {
    const userMenu = document.getElementById('userMenu');
    if (userMenu) {
        userMenu.classList.toggle('active');
        
        // Close menu when clicking outside
        document.addEventListener('click', function closeMenu(e) {
            if (!e.target.closest('.user-profile-dropdown')) {
                userMenu.classList.remove('active');
                document.removeEventListener('click', closeMenu);
            }
        });
    }
}

// Smooth scrolling for navigation links
function initNavigation() {
    document.querySelectorAll('.nav-tab').forEach(link => {
        link.addEventListener('click', (e) => {
            e.preventDefault();
            const target = link.getAttribute('href');
            const section = document.querySelector(target);
            if (section) {
                section.scrollIntoView({ behavior: 'smooth' });
                
                // Update active link
                document.querySelectorAll('.nav-tab').forEach(tab => {
                    tab.classList.remove('active');
                });
                link.classList.add('active');
            }
        });
    });
}

// Update active nav link on scroll
function initScrollActiveLink() {
    window.addEventListener('scroll', () => {
        const sections = document.querySelectorAll('section');
        const navLinks = document.querySelectorAll('.nav-tab');
        
        let current = '';
        sections.forEach(section => {
            const sectionTop = section.offsetTop;
            if (window.pageYOffset >= sectionTop - 200) {
                current = section.getAttribute('id');
            }
        });

        navLinks.forEach(link => {
            link.classList.remove('active');
            if (link.getAttribute('href') === '#' + current) {
                link.classList.add('active');
            }
        });
    });
}

// Filter projects by technology
function filterProjects(tech) {
    const projectCards = document.querySelectorAll('.project-card');
    
    projectCards.forEach(card => {
        const tags = card.querySelectorAll('.tag');
        const hasTech = Array.from(tags).some(tag => 
            tag.textContent.toLowerCase().includes(tech.toLowerCase())
        );
        
        if (tech === 'all' || hasTech) {
            card.style.display = 'block';
            card.style.animation = 'fadeIn 0.3s ease';
        } else {
            card.style.display = 'none';
        }
    });
}

// Animate elements on scroll
function initScrollAnimation() {
    const observer = new IntersectionObserver((entries) => {
        entries.forEach(entry => {
            if (entry.isIntersecting) {
                entry.target.style.animation = 'slideInUp 0.6s ease forwards';
            }
        });
    }, {
        threshold: 0.1
    });

    document.querySelectorAll('.project-card, .skill-item, .service-card').forEach(el => {
        observer.observe(el);
    });
}

// Load user profile from localStorage (if using authentication)
function loadUserProfile() {
    const user = JSON.parse(localStorage.getItem('user'));
    if (user && user.name) {
        const userNameElement = document.getElementById('userName');
        if (userNameElement) {
            userNameElement.textContent = user.name;
        }
    }
}

// Logout user
function logout() {
    localStorage.removeItem('user');
    window.location.href = 'login.html';
}

// Copy email to clipboard
function copyToClipboard(text) {
    navigator.clipboard.writeText(text).then(() => {
        alert('Copied to clipboard: ' + text);
    }).catch(err => {
        console.error('Failed to copy:', err);
    });
}

// Download resume
function downloadResume() {
    const link = document.createElement('a');
    link.href = 'files/resume.pdf';
    link.download = 'resume.pdf';
    document.body.appendChild(link);
    link.click();
    document.body.removeChild(link);
}

// Initialize portfolio on page load
document.addEventListener('DOMContentLoaded', function() {
    console.log('Portfolio initialized');
    
    // Initialize navigation
    initNavigation();
    initScrollActiveLink();
    
    // Load user profile if exists
    loadUserProfile();
    
    // Initialize animations
    if ('IntersectionObserver' in window) {
        initScrollAnimation();
    }
    
    // Add event listener to contact form if exists
    const contactForm = document.querySelector('.contact-form');
    if (contactForm) {
        contactForm.addEventListener('submit', handleSubmit);
    }
});

// Mobile menu toggle
function toggleMobileMenu() {
    const navLinks = document.querySelector('.nav-links');
    if (navLinks) {
        navLinks.classList.toggle('active');
    }
}

// Smooth page load
window.addEventListener('load', () => {
    console.log('Portfolio fully loaded');
    document.body.style.opacity = '1';
});

// Export functions for external use
if (typeof module !== 'undefined' && module.exports) {
    module.exports = {
        scrollToSection,
        handleSubmit,
        toggleUserMenu,
        filterProjects,
        downloadResume,
        copyToClipboard
    };
}
