document.addEventListener('DOMContentLoaded', function() {    
    setTimeout(function() {
        const loginBtn = document.getElementById('loginBtn');
        const loginWidget = document.getElementById('loginWidget');
        const registerWidget = document.getElementById('registerWidget');
        const closeLoginBtn = document.getElementById('closeLogin');
        const closeRegisterBtn = document.getElementById('closeRegister');
        const switchToRegisterBtn = document.getElementById('switchToRegister');
        const switchToLoginBtn = document.getElementById('switchToLogin');

        checkLoginStatus();

        if (loginBtn) {
            loginBtn.addEventListener('click', function(e) {
                e.preventDefault();
                if (loginWidget) {
                    loginWidget.style.display = 'flex';
                    setTimeout(() => {
                        loginWidget.classList.add('active');
                    }, 10);
                    document.body.style.overflow = 'hidden';
                }
            });
        }

        if (closeLoginBtn) {
            closeLoginBtn.addEventListener('click', function() {
                closeModal(loginWidget);
            });
        }

        if (closeRegisterBtn) {
            closeRegisterBtn.addEventListener('click', function() {
                closeModal(registerWidget);
            });
        }

        if (switchToRegisterBtn) {
            switchToRegisterBtn.addEventListener('click', function(e) {
                e.preventDefault();
                loginWidget.classList.remove('active');
                setTimeout(() => {
                    loginWidget.style.display = 'none';
                    registerWidget.style.display = 'flex';
                    setTimeout(() => {
                        registerWidget.classList.add('active');
                    }, 10);
                }, 300);
            });
        }

        if (switchToLoginBtn) {
            switchToLoginBtn.addEventListener('click', function(e) {
                e.preventDefault();
                registerWidget.classList.remove('active');
                setTimeout(() => {
                    registerWidget.style.display = 'none';
                    loginWidget.style.display = 'flex';
                    setTimeout(() => {
                        loginWidget.classList.add('active');
                    }, 10);
                }, 300);
            });
        }

        if (loginWidget) {
            loginWidget.addEventListener('click', function(e) {
                if (e.target === loginWidget) {
                    closeModal(loginWidget);
                }
            });
        }

        if (registerWidget) {
            registerWidget.addEventListener('click', function(e) {
                if (e.target === registerWidget) {
                    closeModal(registerWidget);
                }
            });
        }

        function closeModal(modal) {
            modal.classList.remove('active');
            setTimeout(() => {
                modal.style.display = 'none';
            }, 300);
            document.body.style.overflow = 'auto';
        }

        function showNotification(message, type = 'success') {
            const notification = document.createElement('div');
            notification.className = `notification ${type}`;
            notification.innerHTML = `
                <div class="notification-content">
                    <span>${message}</span>
                    <button class="notification-close">&times;</button>
                </div>
            `;
            
            notification.style.cssText = `
                position: fixed;
                top: 20px;
                right: 20px;
                background: ${type === 'success' ? '#4CAF50' : '#f44336'};
                color: white;
                padding: 15px 20px;
                border-radius: 5px;
                box-shadow: 0 4px 6px rgba(0,0,0,0.1);
                z-index: 10000;
                min-width: 300px;
                opacity: 0;
                transform: translateX(100%);
                transition: all 0.3s ease;
            `;
            
            document.body.appendChild(notification);
            
            setTimeout(() => {
                notification.style.opacity = '1';
                notification.style.transform = 'translateX(0)';
            }, 100);
            
            notification.querySelector('.notification-close').addEventListener('click', () => {
                notification.style.opacity = '0';
                notification.style.transform = 'translateX(100%)';
                setTimeout(() => {
                    document.body.removeChild(notification);
                }, 300);
            });
            
            setTimeout(() => {
                if (document.body.contains(notification)) {
                    notification.style.opacity = '0';
                    notification.style.transform = 'translateX(100%)';
                    setTimeout(() => {
                        document.body.removeChild(notification);
                    }, 300);
                }
            }, 5000);
        }

        async function checkLoginStatus() {
            try {
                const response = await fetch('/auth/me', {
                    method: 'GET',
                    headers: {
                        'Content-Type': 'application/json',
                    }
                });

                if (response.ok) {
                    const data = await response.json();
                    if (data.success && data.user) {
                        updateNavbarForUser(data.user);
                    } else {
                        updateNavbarForGuest();
                    }
                } else {
                    updateNavbarForGuest();
                }
            } catch (error) {
                updateNavbarForGuest();
            }
        }

        function updateNavbarForUser(user) {
            const navbarGuest = document.getElementById('navbar-guest');
            const navbarUser = document.getElementById('navbar-user');
            const userName = document.getElementById('userName');
            const adminMenu = document.getElementById('adminMenu');

            if (navbarGuest) navbarGuest.style.display = 'none';
            if (navbarUser) navbarUser.style.display = 'flex';
            if (userName) userName.textContent = user.hoVaTen;
            
            if (adminMenu && user.isAdmin) {
                adminMenu.style.display = 'block';
            } else if (adminMenu) {
                adminMenu.style.display = 'none';
            }

            setTimeout(() => {
                initUserDropdownAndLogout();
            }, 50);
        }

        function updateNavbarForGuest() {
            const navbarGuest = document.getElementById('navbar-guest');
            const navbarUser = document.getElementById('navbar-user');

            if (navbarGuest) navbarGuest.style.display = 'block';
            if (navbarUser) navbarUser.style.display = 'none';
        }

        function initUserDropdownAndLogout() {
            setTimeout(() => {
                const btn = document.getElementById('userDropdownBtn');
                const menu = document.getElementById('userDropdownMenu');
                
                if (btn && menu) {
                    btn.removeEventListener('click', handleDropdownClick);
                    btn.addEventListener('click', handleDropdownClick, true);
                    
                    btn.onclick = function(e) {
                        handleDropdownClick(e);
                    };
                    
                    function handleDropdownClick(e) {
                        e.preventDefault();
                        e.stopPropagation();
                        
                        const isShowing = menu.classList.contains('show');
                        
                        if (isShowing) {
                            menu.classList.remove('show');
                        } else {
                            menu.classList.add('show');
                        }
                    }
                    
                    document.addEventListener('click', function(e) {
                        if (!btn.contains(e.target) && !menu.contains(e.target)) {
                            menu.classList.remove('show');
                        }
                    });
                }
                
                setupLogout();
            }, 100);
        }

        function setupLogout() {
            const logoutBtn = document.getElementById('logoutBtn');
            
            if (logoutBtn) {
                logoutBtn.addEventListener('click', async function(e) {
                    e.preventDefault();
                    
                    try {
                        const response = await fetch('/auth/logout', {
                            method: 'POST',
                            headers: {
                                'Content-Type': 'application/json',
                            }
                        });

                        const data = await response.json();
                        
                        if (data.success) {
                            updateNavbarForGuest();
                            window.location.href = '/';
                        } else {
                            showNotification('Không thể đăng xuất. Vui lòng thử lại!', 'error');
                        }
                    } catch (error) {
                        showNotification('Có lỗi xảy ra khi đăng xuất!', 'error');
                    }
                });
            }
        }

        const loginForm = document.getElementById('loginForm');
        if (loginForm) {
            loginForm.addEventListener('submit', async function(e) {
                e.preventDefault();
                
                const email = document.getElementById('loginEmail').value;
                const password = document.getElementById('loginPassword').value;
                const submitBtn = loginForm.querySelector('button[type="submit"]');

                if (!email || !password) {
                    showNotification('Vui lòng điền đầy đủ thông tin', 'error');
                    return;
                }

                const originalText = submitBtn.textContent;
                submitBtn.disabled = true;
                submitBtn.textContent = 'Đang đăng nhập...';

                try {
                    const response = await fetch('/auth/login', {
                        method: 'POST',
                        headers: {
                            'Content-Type': 'application/json',
                        },
                        body: JSON.stringify({
                            email: email,
                            matKhau: password
                        })
                    });

                    const data = await response.json();

                    if (data.success) {
                        updateNavbarForUser(data.user);
                        closeModal(loginWidget);
                        loginForm.reset();
                    } else {
                        showNotification(data.message, 'error');
                    }

                } catch (error) {
                    showNotification('Có lỗi xảy ra. Vui lòng thử lại!', 'error');
                } finally {
                    submitBtn.disabled = false;
                    submitBtn.textContent = originalText;
                }
            });
        }

        const registerForm = document.getElementById('registerForm');
        if (registerForm) {
            registerForm.addEventListener('submit', async function(e) {
                e.preventDefault();
                
                const name = document.getElementById('registerName').value;
                const email = document.getElementById('registerEmail').value;
                const password = document.getElementById('registerPassword').value;
                const confirmPassword = document.getElementById('confirmPassword').value;
                const submitBtn = registerForm.querySelector('button[type="submit"]');

                if (!name || !email || !password || !confirmPassword) {
                    showNotification('Vui lòng điền đầy đủ thông tin', 'error');
                    return;
                }

                if (password !== confirmPassword) {
                    showNotification('Mật khẩu xác nhận không khớp', 'error');
                    return;
                }

                if (password.length < 6) {
                    showNotification('Mật khẩu phải có ít nhất 6 ký tự', 'error');
                    return;
                }

                const originalText = submitBtn.textContent;
                submitBtn.disabled = true;
                submitBtn.textContent = 'Đang đăng ký...';

                try {
                    const response = await fetch('/auth/register', {
                        method: 'POST',
                        headers: {
                            'Content-Type': 'application/json',
                        },
                        body: JSON.stringify({
                            hoVaTen: name,
                            email: email,
                            matKhau: password
                        })
                    });

                    const data = await response.json();

                    if (data.success) {
                        showNotification(data.message + ' Chuyển sang đăng nhập...', 'success');
                        registerForm.reset();
                        
                        setTimeout(() => {
                            registerWidget.classList.remove('active');
                            setTimeout(() => {
                                registerWidget.style.display = 'none';
                                loginWidget.style.display = 'flex';
                                setTimeout(() => {
                                    loginWidget.classList.add('active');
                                }, 10);
                            }, 300);
                        }, 2000);
                    } else {
                        showNotification(data.message, 'error');
                    }

                } catch (error) {
                    showNotification('Có lỗi xảy ra. Vui lòng thử lại!', 'error');
                } finally {
                    submitBtn.disabled = false;
                    submitBtn.textContent = originalText;
                }
            });
        }
    }, 100);
});