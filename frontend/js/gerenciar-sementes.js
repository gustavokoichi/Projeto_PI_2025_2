// Configuração da API
const API_URL = 'http://localhost:8080/api/sementes';

let sementes = [];
let editingId = null;
let deleteId = null;

const form = document.getElementById('sementeForm');
const formTitle = document.getElementById('formTitle');
const submitBtn = document.getElementById('submitBtn');
const cancelBtn = document.getElementById('cancelBtn');
const sementesContainer = document.getElementById('sementesContainer');
const searchInput = document.getElementById('searchInput');
const deleteModal = document.getElementById('deleteModal');
const confirmDeleteBtn = document.getElementById('confirmDelete');
const cancelDeleteBtn = document.getElementById('cancelDelete');
const notification = document.getElementById('notification');

document.addEventListener('DOMContentLoaded', function() {
    loadSementes();
    
    form.addEventListener('submit', handleFormSubmit);
    cancelBtn.addEventListener('click', cancelEdit);
    searchInput.addEventListener('input', filterSementes);
    confirmDeleteBtn.addEventListener('click', confirmDelete);
    cancelDeleteBtn.addEventListener('click', closeDeleteModal);
});

async function loadSementes() {
    try {
        showLoading();
        const response = await fetch(API_URL);
        
        if (!response.ok) {
            throw new Error('Erro ao carregar sementes');
        }
        
        sementes = await response.json();
        renderSementes();
    } catch (error) {
        console.error('Erro:', error);
        showNotification('Erro ao carregar sementes: ' + error.message, 'error');
        sementesContainer.innerHTML = '<div class="empty-state">Erro ao carregar sementes</div>';
    }
}

function renderSementes(filteredSementes = null) {
    const sementesToRender = filteredSementes || sementes;
    
    if (sementesToRender.length === 0) {
        sementesContainer.innerHTML = '<div class="empty-state">Nenhuma semente cadastrada</div>';
        return;
    }
    
    sementesContainer.innerHTML = sementesToRender.map(semente => `
        <div class="semente-card">
            <div class="semente-card-header">
                <div class="semente-nome">${escapeHtml(semente.nome)}</div>
                <div class="semente-actions">
                    <button class="edit-btn" onclick="editSemente(${semente.idSemente})" title="Editar">
                        <i class="fa-solid fa-pen"></i>
                    </button>
                    <button class="delete-btn" onclick="openDeleteModal(${semente.idSemente})" title="Excluir">
                        <i class="fa-solid fa-trash"></i>
                    </button>
                </div>
            </div>
            <div class="semente-cientifico">${escapeHtml(semente.nomeCientifico)}</div>
            <div class="semente-descricao">${escapeHtml(semente.descricao)}</div>
        </div>
    `).join('');
}

function filterSementes() {
    const searchTerm = searchInput.value.toLowerCase();
    const filtered = sementes.filter(semente => 
        semente.nome.toLowerCase().includes(searchTerm) ||
        semente.nomeCientifico.toLowerCase().includes(searchTerm) ||
        semente.descricao.toLowerCase().includes(searchTerm)
    );
    renderSementes(filtered);
}

async function handleFormSubmit(e) {
    e.preventDefault();
    
    const formData = new FormData(form);
    const sementeData = {
        nome: formData.get('nome'),
        nomeCientifico: formData.get('nomeCientifico'),
        descricao: formData.get('descricao')
    };
    
    try {
        if (editingId) {
            await updateSemente(editingId, sementeData);
        } else {
            await createSemente(sementeData);
        }
    } catch (error) {
        console.error('Erro:', error);
        showNotification('Erro ao salvar semente: ' + error.message, 'error');
    }
}

async function createSemente(sementeData) {
    const response = await fetch(API_URL, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(sementeData)
    });
    
    if (!response.ok) {
        const errorText = await response.text();
        throw new Error(errorText || 'Erro ao criar semente');
    }
    
    showNotification('Semente criada com sucesso!', 'success');
    resetForm();
    loadSementes();
}

async function updateSemente(id, sementeData) {
    const response = await fetch(`${API_URL}/${id}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(sementeData)
    });
    
    if (!response.ok) {
        const errorText = await response.text();
        throw new Error(errorText || 'Erro ao atualizar semente');
    }
    
    showNotification('Semente atualizada com sucesso!', 'success');
    resetForm();
    loadSementes();
}

function editSemente(id) {
    const semente = sementes.find(s => s.idSemente === id);
    if (!semente) return;
    
    document.getElementById('nome').value = semente.nome;
    document.getElementById('nomeCientifico').value = semente.nomeCientifico;
    document.getElementById('descricao').value = semente.descricao;
    
    editingId = id;
    formTitle.textContent = 'Editar Semente';
    submitBtn.innerHTML = '<i class="fa-solid fa-check"></i> Atualizar Semente';
    submitBtn.style.backgroundColor = '#E07B4F';
    cancelBtn.style.display = 'flex';
    
    form.scrollIntoView({ behavior: 'smooth' });
}

function cancelEdit() {
    resetForm();
}

function resetForm() {
    form.reset();
    editingId = null;
    formTitle.textContent = 'Nova Semente';
    submitBtn.innerHTML = '<i class="fa-solid fa-plus"></i> Adicionar Semente';
    submitBtn.style.backgroundColor = '#4CAF50';
    cancelBtn.style.display = 'none';
}

function openDeleteModal(id) {
    deleteId = id;
    deleteModal.style.display = 'flex';
}

function closeDeleteModal() {
    deleteModal.style.display = 'none';
    deleteId = null;
}

async function confirmDelete() {
    if (!deleteId) return;
    
    try {
        const response = await fetch(`${API_URL}/${deleteId}`, {
            method: 'DELETE'
        });
        
        if (!response.ok) {
            if (response.status === 409) {
                throw new Error('Não é possível excluir: esta semente está vinculada a lotes existentes.');
            }
            throw new Error('Erro ao excluir semente');
        }
        
        showNotification('Semente excluída com sucesso!', 'success');
        closeDeleteModal();
        loadSementes();
        
    } catch (error) {
        showNotification('Erro ao excluir semente: ' + error.message, 'error');
        console.error('Erro:', error);
        closeDeleteModal();
    }
}

function showLoading() {
    sementesContainer.innerHTML = '<div class="loading">Carregando sementes...</div>';
}

function showNotification(message, type = 'info') {
    notification.textContent = message;
    notification.className = `notification ${type} show`;
    
    setTimeout(() => {
        notification.classList.remove('show');
    }, 4000);
}

function escapeHtml(unsafe) {
    return unsafe
        .replace(/&/g, "&amp;")
        .replace(/</g, "&lt;")
        .replace(/>/g, "&gt;")
        .replace(/"/g, "&quot;")
        .replace(/'/g, "&#039;");
}