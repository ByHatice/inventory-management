const API_BASE_URL = 'http://localhost:8080'

class ApiService {
    async getAllItems() {
        const response = await fetch(`${API_BASE_URL}/all`)
        if (!response.ok) {
            throw new Error('Misslyckades att ladda artiklar')
        }
        return await response.json()
    }

    async createItem(item) {
        const response = await fetch(`${API_BASE_URL}/create`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(item)
        })
        if (!response.ok) {
            throw new Error('Misslyckades med att lägga till artikel')
        }
        return await response.json()
    }

    async updateQuantity(id, quantity) {
        const response = await fetch(`${API_BASE_URL}/update?id=${id}&quantity=${quantity}`, {
            method: 'PUT',
        })
        if (!response.ok) {
            throw new Error('Misslyckades med att uppdatera lagersaldo')
        }
        return await response.json()
    }

    async deleteItem(id) {
        const response = await fetch(`${API_BASE_URL}/delete?id=${id}`, {
            method: 'DELETE',
        })
        if (!response.ok) {
            throw new Error('Misslyckades med att radera artikel')
        }
    }

    async searchItems(searchTerm) {
        const response = await fetch(`${API_BASE_URL}/search?searchTerm=${encodeURIComponent(searchTerm)}`)
        if (!response.ok) {
            throw new Error('Kunde inte hämta sökresultat')
        }
        return await response.json()
    }

    async getLowStockItems(threshold = 10) {
        const response = await fetch(`${API_BASE_URL}/low-stock?threshold=${threshold}`)
        if (!response.ok) {
            throw new Error('Det gick inte att hämta artiklar med lågt saldo')
        }
        return await response.json()
    }
}

export default new ApiService()