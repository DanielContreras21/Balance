const JWT = localStorage.getItem('jwt')

const NUMBERFORMATTER = new Intl.NumberFormat('es-CO', {
    style: 'currency',
    currency: 'COP',
    minimumFractionDigits: 2,
    maximumFractionDigits: 2
});

const DATEFORMATTER = new Intl.DateTimeFormat('es-CO', {
    year:'numeric',
    month: 'numeric',
    day: 'numeric',
    hour: '2-digit',
    minute: '2-digit',
    second: '2-digit',
    hour12: false
});

const openModal = (modalId) =>{
    const modal = document.getElementById(modalId)
    modal.showModal()
}

const closeModal = (modalId) =>{    
    const modal = document.getElementById(modalId)
    modal.close()
}

function formatDateToString(dateInMilliseconds) {
    const date = new Date(dateInMilliseconds);

    const day = String(date.getDate()).padStart(2, '0');
    const month = String(date.getMonth() + 1).padStart(2, '0');
    const year = date.getFullYear();

    const hours = String(date.getHours()).padStart(2, '0');
    const minutes = String(date.getMinutes()).padStart(2, '0');

    return `${day}-${month}-${year} ${hours}:${minutes}`;
}