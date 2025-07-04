document.addEventListener('DOMContentLoaded', function() {
    // Đợi auth.js khởi tạo xong
    setTimeout(() => {
        initProductsPage();
    }, 200);
});

function initProductsPage() {
    // Elements
    const mobileFilterToggle = document.getElementById('mobileFilterToggle');
    const filtersSidebar = document.querySelector('.filters-sidebar');
    const productsGrid = document.getElementById('productsGrid');
    const sortOptions = document.getElementById('sortOptions');
    const clearFiltersBtn = document.querySelector('.clear-filters-btn');

    // Mobile filter toggle
    if (mobileFilterToggle) {
        mobileFilterToggle.addEventListener('click', function(e) {
            e.stopPropagation(); // Ngăn conflict với auth.js
            filtersSidebar.classList.toggle('active');
        });
    }

    // Close filters when clicking outside on mobile - CẢI THIỆN
    document.addEventListener('click', function(e) {
        // Chỉ xử lý nếu không phải navbar dropdown
        if (e.target.closest('#userDropdownBtn') || e.target.closest('#userDropdownMenu')) {
            return; // Để auth.js xử lý
        }
        
        if (window.innerWidth <= 768) {
            if (!filtersSidebar.contains(e.target) && !mobileFilterToggle.contains(e.target)) {
                filtersSidebar.classList.remove('active');
            }
        }
    });

    // Sort options
    if (sortOptions) {
        sortOptions.addEventListener('change', function(e) {
            e.stopPropagation();
            const sortValue = this.value;
            console.log('Sort by:', sortValue);
        });
    }

    // Clear filters
    if (clearFiltersBtn) {
        clearFiltersBtn.addEventListener('click', function(e) {
            e.stopPropagation();
            // Reset all filters
            document.querySelectorAll('input[type="checkbox"]').forEach(cb => {
                cb.checked = cb.value === 'all';
            });
            
            if (sortOptions) {
                sortOptions.value = 'default';
            }
            
            console.log('Filters cleared');
        });
    }

    // Category filters
    document.querySelectorAll('.filter-option input[type="checkbox"]').forEach(checkbox => {
        checkbox.addEventListener('change', function(e) {
            e.stopPropagation();
            console.log('Filter changed:', this.value, this.checked);
        });
    });

    // Add to cart functionality - CẢI THIỆN
    document.addEventListener('click', function(e) {
        // Chỉ xử lý nếu click vào product card, không phải navbar
        if (e.target.closest('#userDropdownBtn') || e.target.closest('#userDropdownMenu')) {
            return;
        }
        
        if (e.target.closest('.add-to-cart-btn') && !e.target.closest('.add-to-cart-btn').disabled) {
            e.preventDefault();
            e.stopPropagation();
            const productCard = e.target.closest('.product-card');
            addToCart(productCard);
        }
    });

    function addToCart(productCard) {
        console.log('Add to cart:', productCard);
        showProductNotification('Đã thêm sản phẩm vào giỏ hàng!', 'success');
    }

    // Đổi tên function để tránh conflict với auth.js
    function showProductNotification(message, type = 'success') {
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
            z-index: 9999;
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
        
        notification.querySelector('.notification-close').addEventListener('click', (e) => {
            e.stopPropagation();
            notification.style.opacity = '0';
            notification.style.transform = 'translateX(100%)';
            setTimeout(() => {
                if (document.body.contains(notification)) {
                    document.body.removeChild(notification);
                }
            }, 300);
        });
        
        setTimeout(() => {
            if (document.body.contains(notification)) {
                notification.style.opacity = '0';
                notification.style.transform = 'translateX(100%)';
                setTimeout(() => {
                    if (document.body.contains(notification)) {
                        document.body.removeChild(notification);
                    }
                }, 300);
            }
        }, 3000);
    }
}