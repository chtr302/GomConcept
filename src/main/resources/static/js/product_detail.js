let productData = {};
let selectedSize = null;

document.addEventListener('DOMContentLoaded', function() {
    if (!document.body.hasAttribute('data-page') || 
        document.body.getAttribute('data-page') !== 'product-detail') {
        return;
    }
    
    loadProductDataFromHTML();
    initializeEventListeners();
    selectFirstAvailableSize();
});

function loadProductDataFromHTML() {
    const productElement = document.getElementById('productData');
    
    if (!productElement) {
        return;
    }

    productData = {
        id: productElement.dataset.id,
        name: productElement.dataset.name,
        slug: productElement.dataset.slug,
        description: productElement.dataset.description,
        images: {
            main: productElement.dataset.mainImage,
            image2: productElement.dataset.image2,
            image3: productElement.dataset.image3,
            image4: productElement.dataset.image4
        },
        details: []
    };

    const detailElements = document.querySelectorAll('.product-detail-item');
    detailElements.forEach(element => {
        productData.details.push({
            id: element.dataset.id,
            size: element.dataset.size,
            price: parseFloat(element.dataset.price),
            height: parseFloat(element.dataset.height),
            width: parseFloat(element.dataset.width),
            stock: parseInt(element.dataset.stock)
        });
    });
}

function initializeEventListeners() {
    // Image gallery
    document.querySelectorAll('[data-action="change-image"]').forEach(img => {
        img.addEventListener('click', handleImageChange);
        img.addEventListener('keydown', handleImageKeydown);
    });

    // Size selection - chỉ gắn vào radio button để tránh xung đột
    document.querySelectorAll('.size-option input[type="radio"]').forEach(radio => {
        radio.addEventListener('change', handleSizeChange);
    });

    // Action buttons
    document.querySelectorAll('[data-action="contact-order"]').forEach(btn => {
        btn.addEventListener('click', handleContactOrder);
    });

    document.querySelectorAll('[data-action="share-product"]').forEach(btn => {
        btn.addEventListener('click', handleShareProduct);
    });
}

function handleImageChange(event) {
    const clickedImg = event.target;
    const mainImage = document.getElementById('mainImage');
    
    if (mainImage && clickedImg.src) {
        mainImage.src = clickedImg.src;
        
        document.querySelectorAll('.thumbnail-wrapper').forEach(wrapper => {
            wrapper.classList.remove('active');
        });
        clickedImg.closest('.thumbnail-wrapper').classList.add('active');
    }
}

function handleImageKeydown(event) {
    if (event.key === 'Enter' || event.key === ' ') {
        event.preventDefault();
        handleImageChange(event);
    }
}

function handleSizeChange(event) {
    const radio = event.target;
    const sizeOption = radio.closest('.size-option');
    
    if (!sizeOption) return;
    
    const size = sizeOption.dataset.size;
    const price = parseFloat(sizeOption.dataset.price);
    const height = parseFloat(sizeOption.dataset.height);
    const width = parseFloat(sizeOption.dataset.width);
    const stock = parseInt(sizeOption.dataset.stock);

    selectedSize = { size, price, height, width, stock };
}

function selectFirstAvailableSize() {
    const firstRadio = document.querySelector('.size-option input[type="radio"]:not(:disabled)');
    if (firstRadio) {
        firstRadio.checked = true;
        firstRadio.dispatchEvent(new Event('change'));
    }
}

function handleContactOrder() {
    const selectedSizeText = selectedSize ? selectedSize.size : 'Chưa chọn';
    
    const message = `Xin chào! Tôi muốn tham khảo sản phẩm:
Tên: ${productData.name || 'Sản phẩm gốm'}
Size: ${selectedSizeText}`;

    const facebookPageId = 'gomconcept98';

    const messengerUrl = `https://m.me/${facebookPageId}?text=${encodeURIComponent(message)}`;

    window.open(messengerUrl, '_blank');
}

function handleShareProduct() {
    const shareData = {
        title: productData.name || 'Sản phẩm gốm thủ công',
        text: 'Xem sản phẩm gốm thủ công tuyệt đẹp này!',
        url: window.location.href
    };
    
    if (navigator.share) {
        navigator.share(shareData).catch(() => {
            fallbackShare();
        });
    } else {
        fallbackShare();
    }
}

function fallbackShare() {
    copyToClipboard(window.location.href);
    showNotification('Đã copy link sản phẩm!');
}

function formatPrice(price) {
    return new Intl.NumberFormat('vi-VN', {
        style: 'currency',
        currency: 'VND',
        minimumFractionDigits: 0
    }).format(price);
}

function isMobile() {
    return /Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini/i.test(navigator.userAgent);
}

function copyToClipboard(text) {
    if (navigator.clipboard) {
        navigator.clipboard.writeText(text);
    } else {
        const textArea = document.createElement('textarea');
        textArea.value = text;
        document.body.appendChild(textArea);
        textArea.select();
        document.execCommand('copy');
        document.body.removeChild(textArea);
    }
}

function showNotification(message) {
    const notification = document.createElement('div');
    notification.className = 'notification';
    notification.textContent = message;
    notification.style.cssText = `
        position: fixed;
        top: 20px;
        right: 20px;
        background: var(--primary-brown, #4c3523);
        color: white;
        padding: 15px 25px;
        border-radius: 8px;
        box-shadow: 0 4px 15px rgba(0,0,0,0.2);
        z-index: 1001;
        font-family: 'Lora', serif;
    `;
    
    document.body.appendChild(notification);
    
    setTimeout(() => {
        notification.remove();
    }, 3000);
}

function showContactModal(message) {
    const modal = document.createElement('div');
    modal.className = 'contact-modal';
    modal.innerHTML = `
        <div class="modal-content" style="
            background: white;
            border-radius: 12px;
            padding: 30px;
            max-width: 400px;
            width: 90%;
            border: 2px solid var(--primary-brown, #4c3523);
        ">
            <div class="modal-header" style="
                display: flex;
                justify-content: space-between;
                align-items: center;
                margin-bottom: 20px;
                border-bottom: 2px solid var(--primary-brown, #4c3523);
                padding-bottom: 15px;
            ">
                <h3 style="
                    margin: 0;
                    color: var(--primary-brown, #4c3523);
                    font-family: 'Crimson Text', serif;
                ">Liên hệ đặt hàng</h3>
                <button class="close-modal" onclick="this.closest('.contact-modal').remove()" style="
                    background: none;
                    border: none;
                    font-size: 24px;
                    cursor: pointer;
                    color: var(--primary-brown, #4c3523);
                ">&times;</button>
            </div>
            <div class="modal-body">
                <p style="margin-bottom: 20px; color: var(--text-dark, #2c1810);">Chọn phương thức liên hệ:</p>
                <div class="contact-options" style="display: flex; flex-direction: column; gap: 15px;">
                    <button onclick="window.open('tel:0123456789')" style="
                        padding: 12px 20px;
                        background: var(--primary-brown, #4c3523);
                        color: white;
                        border: none;
                        border-radius: 8px;
                        cursor: pointer;
                        font-family: 'Lora', serif;
                        display: flex;
                        align-items: center;
                        gap: 10px;
                    ">
                        <i class="fas fa-phone"></i> Gọi điện: 0123 456 789
                    </button>
                    <button onclick="window.open('mailto:info@gomconcept.com?subject=Đặt hàng sản phẩm&body=${encodeURIComponent(message)}')" style="
                        padding: 12px 20px;
                        background: var(--secondary-beige, #fffcef);
                        color: var(--primary-brown, #4c3523);
                        border: 2px solid var(--primary-brown, #4c3523);
                        border-radius: 8px;
                        cursor: pointer;
                        font-family: 'Lora', serif;
                        display: flex;
                        align-items: center;
                        gap: 10px;
                    ">
                        <i class="fas fa-envelope"></i> Gửi Email
                    </button>
                </div>
            </div>
        </div>
    `;
    
    modal.style.cssText = `
        position: fixed;
        top: 0;
        left: 0;
        width: 100%;
        height: 100%;
        background: rgba(0,0,0,0.5);
        display: flex;
        justify-content: center;
        align-items: center;
        z-index: 1000;
    `;
    
    document.body.appendChild(modal);
    
    // Đóng modal khi click bên ngoài
    modal.addEventListener('click', function(e) {
        if (e.target === modal) {
            modal.remove();
        }
    });
}