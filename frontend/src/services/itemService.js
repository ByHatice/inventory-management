/* import axios from 'axios'

const API_BASE_URL = 'http://localhost:8080'

const api = axios.create({
    baseURL: API_BASE_URL,
    headers: {
        'Content-Type': 'application/json',
    },
})

export const itemService = {
    getAllItems() {
        return api.get('/items')
    },

    getItemById(id) {
        return api.get(`/items/${id}`)
    },

    createItem(item) {
        return api.post('/items', item)
    },

    updateItem(id, item) {
        return api.put(`/items/${id}`, item)
    },

    // Uppdatera endast kvantitet
    updateQuantity(id, quantity) {
        return api.put(`/items/${id}/quantity?quantity=${quantity}`)
    },

    // Ta bort artikel
    deleteItem(id) {
        return api.delete(`/items/${id}`)
    },

    // Hämta artiklar med lågt lager
    getLowStockItems() {
        return api.get('/items/low-stock')
    },

    // Sök artiklar
    searchItems(searchTerm) {
        return api.get(`/items/search?searchTerm=${searchTerm}`)
    }
}

 */