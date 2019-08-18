import axios from 'axios'

const SERVER_URL = 'http://localhost:8080';

const instance = axios.create({
    baseURL: SERVER_URL,
    timeout: 1000
});

export default {
    getAll: () => instance.get('equipo', {
        transformResponse: [function (data) {
            console.log('data', data);
            return JSON.parse(data);
        }]
    })
}
